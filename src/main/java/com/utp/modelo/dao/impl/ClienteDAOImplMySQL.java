package com.utp.modelo.dao.impl;

import com.utp.modelo.dao.ClienteDAO;
import com.utp.modelo.dto.ClienteDTO;
import com.utp.modelo.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImplMySQL implements ClienteDAO {

    private static final String SQL_LOGIN = "SELECT id, nombre, apellido, telefono, direccion, email, password, rol FROM cliente WHERE email = ? AND password = ?";

    @Override
    public ClienteDTO validarCredenciales(String email, String password) {
        ClienteDTO clienteLogeado = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Obtener la conexión (Usa la clase Singleton de utilidad)
            conn = Conexion.obtenerConexion();
            // 2. Preparar la consulta
            ps = conn.prepareStatement(SQL_LOGIN);            
            ps.setString(1, email);    // Parámetro 1: email
            ps.setString(2, password); // Parámetro 2: password
            // 3. Ejecutar la consulta
            rs = ps.executeQuery();
            
            // 4. Mapear resultados a la lista de objetos Producto (DTO)
            if (rs.next()) {
                clienteLogeado = new ClienteDTO();
                clienteLogeado.setId(rs.getInt("id"));
                clienteLogeado.setNombre(rs.getString("nombre"));
                clienteLogeado.setApellido(rs.getString("apellido"));
                clienteLogeado.setTelefono(rs.getString("telefono"));
                clienteLogeado.setDireccion(rs.getString("direccion"));
                clienteLogeado.setEmail(rs.getString("email"));
                clienteLogeado.setPassword(rs.getString("password"));
                clienteLogeado.setRol(rs.getString("rol"));
            }

        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos: " + e.getMessage());
            }
        }
        return clienteLogeado;
    }
}