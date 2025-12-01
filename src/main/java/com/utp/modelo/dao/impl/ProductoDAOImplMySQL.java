package com.utp.modelo.dao.impl;

import com.utp.modelo.dao.ProductoDAO;
import com.utp.modelo.dto.ProductoDTO;
import com.utp.modelo.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementación concreta del contrato ProductoDAO usando JDBC.
public class ProductoDAOImplMySQL implements ProductoDAO {

    // Consulta SQL para obtener todos los productos activos
    private static final String SQL_SELECT_ALL_ACTIVOS = "SELECT id, nombre, descripcion, precio, stock, es_activo FROM producto WHERE es_activo = 1";
    
    private static final String SQL_SELECT_BY_ID = "SELECT id, nombre, descripcion, precio, stock, es_activo FROM producto WHERE id = ? AND es_activo = 1";
    
    /**
     * Helper para mapear el ResultSet a un objeto ProductoDTO.
     * @param rs El ResultSet de la consulta.
     * @return ProductoDTO mapeado.
     * @throws SQLException Si ocurre un error de SQL.
     */
    private ProductoDTO mapearProducto(ResultSet rs) throws SQLException {
        ProductoDTO producto = new ProductoDTO();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getBigDecimal("precio")); // Usar getBigDecimal para precisión monetaria
        producto.setStock(rs.getInt("stock"));
        producto.setEsActivo(rs.getBoolean("es_activo")); 
        return producto;
    }
    
    // 2. IMPLEMENTACIÓN CRÍTICA PARA EL CARRITO (Buscar producto por ID)
    @Override
    public ProductoDTO obtenerPorId(long id) throws Exception {
        ProductoDTO producto = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            ps = conn.prepareStatement(SQL_SELECT_BY_ID);
            
            // 1. CRÍTICO: Establecer el parámetro del ID (Previene SQL Injection)
            ps.setLong(1, id); 
            
            rs = ps.executeQuery();

            // Solo esperamos UNA fila (o ninguna)
            if (rs.next()) {
                producto = mapearProducto(rs);
            }

        } catch (SQLException e) {
            // Propagamos la excepción para que sea manejada por el Facade/Servlet
            throw new Exception("Error de SQL al buscar producto con ID " + id, e);
        } finally {
            // Cierre de recursos (CRÍTICO)
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                 System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        // Retorna null si no se encuentra el producto
        return producto;
    }
    
    @Override
    public List<ProductoDTO> obtenerTodosActivos() {
        List<ProductoDTO> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Obtener la conexión (Usa la clase Singleton de utilidad)
            conn = Conexion.obtenerConexion();
            
            // 2. Preparar la consulta
            ps = conn.prepareStatement(SQL_SELECT_ALL_ACTIVOS);
            
            // 3. Ejecutar la consulta
            rs = ps.executeQuery();

            // 4. Mapear resultados a la lista de objetos Producto (DTO)
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setStock(rs.getInt("stock"));
                // Mapeo de TINYINT(1) a boolean
                producto.setEsActivo(rs.getBoolean("es_activo")); 
                
                productos.add(producto);
            }

        } catch (SQLException e) {
            // Manejo de la excepción de SQL
            System.err.println("Error al obtener productos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Cierre de recursos (CRÍTICO para evitar fugas de memoria y conexiones)
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                 System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return productos;
    }
}
