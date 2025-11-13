package com.utp.modelo.conexion;

import com.utp.util.Constantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;

    // Constructor privado (sin cambios)
    private Conexion() {
        try {
            // Carga del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el Driver JDBC: " + e.getMessage());
        }
    }
    
    // Método para asegurar que la instancia exista (Llamado implícitamente)
    private static Conexion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Método ESTÁTICO y principal para obtener la conexión
    /**
     * Retorna una nueva conexión JDBC. Es estático para fácil acceso.
     * @return Objeto Connection de JDBC.
     * @throws SQLException Si ocurre un error de conexión.
     */
    public static Connection obtenerConexion() throws SQLException {
        // Aseguramos que el Singleton esté inicializado (para la carga del Driver)
        obtenerInstancia(); 
        
        // Retornamos la conexión física
        return DriverManager.getConnection(
            Constantes.MYSQL_DB_URL,
            Constantes.MYSQL_DB_USER,
            Constantes.MYSQL_DB_PASS
        );
    }
    
    // Método estático de utilidad para cerrar la conexión (sin cambios)
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    
    //Prueba tu conexion a base de datos con este main 
    //Descoméntalo y dale a run (Solo al file) Te mostrará
    // En el output si la base de datos esta conctada o no
//public static void main(String[] args) {
//        Connection testConn = null;
//        try {
//            // 1. Uso correcto: Llamar al método estático getInstancia() 
//            //    para obtener el objeto, y luego llamar al método de instancia obtenerConexion().
//            testConn = Conexion.obtenerInstancia().obtenerConexion();
//            
//            // 2. Comprobar la conexión
//            if (testConn != null) {
//                System.out.println("TEST EXITOSO: ¡Conexión establecida correctamente!");
//            } else {
//                System.out.println("TEST FALLIDO: La conexión no se pudo establecer.");
//            }
//            
//        } catch (SQLException e) {
//            System.err.println("TEST FALLIDO con Excepción: Revise su URL, usuario o contraseña en Constantes.");
//            e.printStackTrace();
//        } finally {
//            // 3. Cerrar la conexión
//            cerrarConexion(testConn);
//        }
//    }
    
}
