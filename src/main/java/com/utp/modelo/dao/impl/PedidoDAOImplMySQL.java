//package com.utp.modelo.dao.impl;
//
//import com.utp.modelo.dao.PedidoDAO;
//import com.utp.modelo.dto.PedidoDTO;
//import com.utp.modelo.dto.DetallePedidoDTO;
//import com.utp.modelo.conexion.Conexion;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class PedidoDAOImplMySQL implements PedidoDAO {
//
//    @Override
//    public PedidoDTO crearPedido(PedidoDTO pedido) {
//        String SQL_INSERT_PEDIDO = "INSERT INTO pedidos (fecha_hora, estado) VALUES (?, ?)";
//        String SQL_INSERT_DETALLE = "INSERT INTO detalles_pedido (id_pedido, id_producto, nombre_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?)";
//        
//        Connection conn = null;
//        try {
//            conn = Conexion.obtenerConexion();
//            conn.setAutoCommit(false); // Inicia la transacción
//
//            // 1. Insertar el encabezado del Pedido
//            try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS)) {
//                ps.setTimestamp(1, Timestamp.valueOf(pedido.getFechaHora()));
//                ps.setString(2, pedido.getEstado());
//                
//                if (ps.executeUpdate() == 0) {
//                    throw new SQLException("Error al crear el pedido (encabezado).");
//                }
//                
//                // Obtener el ID generado
//                try (ResultSet rs = ps.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        long idGenerado = rs.getLong(1);
//                        pedido.setIdPedido(idGenerado);
//                    }
//                }
//            }
//            
//            // 2. Persistir los detalles (misma transacción)
//            try (PreparedStatement psDetalle = conn.prepareStatement(SQL_INSERT_DETALLE)) {
//                for (DetallePedidoDTO detalle : pedido.getDetalles()) {
//                    psDetalle.setLong(1, pedido.getIdPedido()); // FK al Pedido
//                    psDetalle.setLong(2, detalle.getIdProducto());
//                    psDetalle.setString(3, detalle.getNombreProducto());
//                    psDetalle.setInt(4, detalle.getCantidad());
//                    psDetalle.setDouble(5, detalle.getPrecioUnitario());
//                    psDetalle.addBatch();
//                }
//                psDetalle.executeBatch();
//            }
//
//            conn.commit(); // Confirma la transacción
//            return pedido;
//
//        } catch (SQLException e) {
//            System.err.println("Error transaccional en la creación del pedido: " + e.getMessage());
//            if (conn != null) {
//                try {
//                    conn.rollback(); // Deshace si algo falla
//                } catch (SQLException ex) {
//                    System.err.println("Rollback fallido: " + ex.getMessage());
//                }
//            }
//            return null;
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.setAutoCommit(true); // Restaura el modo
//                    conn.close(); // Cierra la conexión si no está gestionada por el Singleton
//                } catch (SQLException e) { 
//                    System.err.println("Error al cerrar conexión: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//    @Override
//    public List<PedidoDTO> obtenerPedidosPorEstado(String estado) {
//        Map<Long, PedidoDTO> pedidoMap = new HashMap<>();
//        // Consulta que trae cabecera y todos sus detalles con un JOIN
//        String SQL_SELECT = "SELECT p.id_pedido, p.fecha_hora, p.estado, d.id_detalle, d.id_producto, d.nombre_producto, d.cantidad, d.precio_unitario " +
//                            "FROM pedidos p JOIN detalles_pedido d ON p.id_pedido = d.id_pedido WHERE p.estado = ? ORDER BY p.fecha_hora ASC";
//
//        try (Connection conn = Conexion.obtenerConexion();
//             PreparedStatement ps = conn.prepareStatement(SQL_SELECT)) {
//             
//            ps.setString(1, estado);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Long idPedido = rs.getLong("id_pedido");
//                    
//                    // Si el pedido no está en el mapa, créalo (Mapeo de la cabecera)
//                    PedidoDTO pedido = pedidoMap.get(idPedido);
//                    if (pedido == null) {
//                        pedido = new PedidoDTO();
//                        pedido.setIdPedido(idPedido);
//                        pedido.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
//                        pedido.setEstado(rs.getString("estado"));
//                        pedido.setDetalles(new ArrayList<>());
//                        pedidoMap.put(idPedido, pedido);
//                    }
//                    
//                    // Mapeo del Detalle
//                    DetallePedidoDTO detalle = new DetallePedidoDTO();
//                    detalle.setIdDetalle(rs.getLong("id_detalle"));
//                    detalle.setIdPedido(idPedido);
//                    detalle.setIdProducto(rs.getLong("id_producto"));
//                    detalle.setNombreProducto(rs.getString("nombre_producto"));
//                    detalle.setCantidad(rs.getInt("cantidad"));
//                    detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
//                    
//                    pedido.getDetalles().add(detalle);
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al obtener pedidos por estado: " + e.getMessage());
//        }
//        return new ArrayList<>(pedidoMap.values());
//    }
//
//    @Override
//    public boolean actualizarEstadoPedido(long idPedido, String nuevoEstado) {
//        String SQL_UPDATE = "UPDATE pedidos SET estado = ? WHERE id_pedido = ?";
//        try (Connection conn = Conexion.obtenerConexion();
//             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
//            
//            ps.setString(1, nuevoEstado);
//            ps.setLong(2, idPedido);
//            
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) {
//            System.err.println("Error al actualizar estado del pedido: " + e.getMessage());
//            return false;
//        }
//    }
//}