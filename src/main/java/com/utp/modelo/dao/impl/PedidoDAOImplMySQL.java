package com.utp.modelo.dao.impl;

import com.utp.modelo.dao.PedidoDAO;
import com.utp.modelo.dto.PedidoDTO;
import com.utp.modelo.dto.DetallePedidoDTO;
import com.utp.modelo.conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoDAOImplMySQL implements PedidoDAO {

    @Override
    public PedidoDTO crearPedido(PedidoDTO pedido) {
        String SQL_INSERT_PEDIDO = "INSERT INTO pedido (cliente_id, total, estado) VALUES (?, ?, ?)";
        String SQL_INSERT_DETALLE = "INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";        
        
        Connection conn = null;
        
        try {
            conn = Conexion.obtenerConexion();
            conn.setAutoCommit(false); // Inicia la transacción

            // 1. Insertar el encabezado del Pedido
            try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS)) {
                
                // NOTA: Asumiendo que PedidoDTO tiene getClienteId() y getTotal()
                ps.setLong(1, pedido.getClienteId()); 
                ps.setBigDecimal(2, pedido.getTotal()); 
                ps.setString(3, pedido.getEstado()); // Estado 'PENDIENTE'
                
                if (ps.executeUpdate() == 0) {
                    throw new SQLException("Error al crear el pedido (encabezado).");
                }
                
                // Obtener el ID generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        long idGenerado = rs.getLong(1);
                        pedido.setId(idGenerado); // Usar setId()
                    } else {
                           throw new SQLException("No se pudo obtener el ID del pedido.");
                    }
                }
            }
            
            // 2. Persistir los detalles (misma transacción)
            try (PreparedStatement psDetalle = conn.prepareStatement(SQL_INSERT_DETALLE)) {
                for (DetallePedidoDTO detalle : pedido.getDetalles()) {
                    psDetalle.setLong(1, pedido.getId()); // FK al Pedido
                    psDetalle.setLong(2, detalle.getProductoId());
                    psDetalle.setInt(3, detalle.getCantidad());
                    psDetalle.setBigDecimal(4, detalle.getPrecioUnitario()); // Usar BigDecimal para precisión
                    psDetalle.addBatch();
                }
                psDetalle.executeBatch();
            }

            conn.commit(); // Confirma la transacción
            return pedido;

        } catch (SQLException e) {
            System.err.println("Error transaccional en la creación del pedido: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback(); // Deshace si algo falla
                } catch (SQLException ex) {
                    System.err.println("Rollback fallido: " + ex.getMessage());
                }
            }
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura el modo
                    conn.close(); // Cierra la conexión si no está gestionada por el Singleton
                } catch (SQLException e) { 
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorEstado(String estado) {
        Map<Long, PedidoDTO> pedidoMap = new HashMap<>();
        
        // Consulta SQL MEJORADA: Se añade JOIN a la tabla 'producto' (pr) para obtener el nombre del producto
        String SQL_SELECT = "SELECT p.id, p.cliente_id, p.fecha_pedido, p.total, p.estado, " +
                            "d.id AS detalle_id, d.producto_id, d.cantidad, d.precio_unitario, " +
                            "pr.nombre AS nombre_producto " + // <--- NUEVO CAMPO SELECCIONADO
                            "FROM pedido p " +
                            "JOIN detalle_pedido d ON p.id = d.pedido_id " +
                            "JOIN producto pr ON d.producto_id = pr.id " + // <--- NUEVO JOIN
                            "WHERE p.estado = ? ORDER BY p.fecha_pedido ASC";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT)) {
            
            ps.setString(1, estado);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // 1. Obtener ID del Pedido (Cabecera)
                    Long idPedido = rs.getLong("id"); // Usamos "id" de la tabla pedido
                    
                    // Si el pedido no está en el mapa, créalo (Mapeo de la cabecera)
                    PedidoDTO pedido = pedidoMap.get(idPedido);
                    if (pedido == null) {
                        pedido = new PedidoDTO();
                        
                        // Mapeo de campos de PedidoDTO (Corregido)
                        pedido.setId(idPedido); 
                        pedido.setClienteId(rs.getLong("cliente_id")); // Agregado
                        
                        Timestamp ts = rs.getTimestamp("fecha_pedido");
                        if (ts != null) {
                            pedido.setFechaPedido(ts.toLocalDateTime()); 
                        }
                        
                        // Usar getBigDecimal para el total (mayor precisión)
                        pedido.setTotal(rs.getBigDecimal("total")); 
                        
                        pedido.setEstado(rs.getString("estado"));
                        pedido.setDetalles(new ArrayList<>());
                        pedidoMap.put(idPedido, pedido);
                    }
                    
                    // 2. Mapeo del Detalle (Línea del pedido)
                    DetallePedidoDTO detalle = new DetallePedidoDTO();
                    
                    // Mapeo de campos de DetallePedidoDTO (Corregido)
                    detalle.setId(rs.getLong("detalle_id")); // Usamos el alias de la columna
                    detalle.setPedidoId(idPedido);
                    detalle.setProductoId(rs.getLong("producto_id"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    
                    // Mapear el nombre del producto para la vista de cocina
                    detalle.setNombreProducto(rs.getString("nombre_producto")); // <--- NUEVO MAPEO
                    
                    // USAR getBigDecimal para precio_unitario (CRÍTICO)
                    detalle.setPrecioUnitario(rs.getBigDecimal("precio_unitario")); 
                    
                    // 3. Agregar el detalle a la lista del pedido
                    pedido.getDetalles().add(detalle);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos por estado: " + e.getMessage());
        }
        return new ArrayList<>(pedidoMap.values());
    }

    @Override
    public boolean actualizarEstadoPedido(long idPedido, String nuevoEstado) {
        // SQL CORREGIDO: Usar 'pedido' y 'id'
        String SQL_UPDATE = "UPDATE pedido SET estado = ? WHERE id = ?";
        
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            
            ps.setString(1, nuevoEstado);
            // idPedido es el valor que recibes como parámetro
            ps.setLong(2, idPedido); 
            
            // Devuelve true si al menos una fila fue actualizada
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado del pedido: " + e.getMessage());
            return false;
        }
    }
}