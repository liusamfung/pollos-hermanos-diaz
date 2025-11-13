package com.utp.util;

public final class Constantes {

    // --- Configuración de la Base de Datos (usada en modelo/conexion/Conexion.java) ---
    public static final String MYSQL_DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/pollos_hermanos_diaz"; //Link que funcionaba en TomCat
// Glassfish o diferencia do TomCat tiene mecanismos de seguridad como keystroks y SSL. Vamos a desactivarlas porque no requimos eso
    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/pollos_hermanos_diaz?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String MYSQL_DB_USER = "root";
    public static final String MYSQL_DB_PASS = "adminroot";

//    // --- Nombres de Parámetros/Atributos (usados en Servlets y JSPs) ---
//    public static final String PARAM_ACCION = "accion"; // Nombre del parámetro común para manejar el Front Controller
//    public static final String ATTR_LISTA_PRODUCTOS = "listaProductos"; // Nombre del atributo usado para el Request Dispatcher
//    public static final String ATTR_MENSAJE_ERROR = "mensajeError";

    // --- Rutas de Vistas (usadas en Servlets) ---
    // Esto evita escribir '/WEB-INF/jsp/pedidos/...' cada vez en los Servlets.
    public static final String VISTA_LISTAR_PEDIDOS = "/WEB-INF/jsp/pedidos/listarPedidos.jsp";
    public static final String VISTA_ERROR = "/error.jsp";

    // --- Valores Fijos de Lógica de Negocio (usados en DAOs y Facade) ---
    public static final int PEDIDO_ESTADO_PENDIENTE = 1;
    public static final int PEDIDO_ESTADO_ENTREGADO = 2;
    public static final String ESTADO_ACTIVO = "A";

    // Constructor privado para evitar que la clase sea instanciada.
    private Constantes() {}
}



//Estonces un servet podría usar:
//// Importar la clase para acceder a las constantes
//import com.polleria.util.Constantes;
//
//// ... dentro del método doGet o doPost del Servlet ...
//
//String accion = request.getParameter(Constantes.PARAM_ACCION);
//
//if ("listar".equals(accion)) {
//    // 1. Invocar al modelo/fachada
//    List<PedidoDTO> pedidos = facade.listarPedidosPendientes(Constantes.PEDIDO_ESTADO_PENDIENTE);
//
//    // 2. Colocar los resultados y redireccionar a la vista
//    request.setAttribute(Constantes.ATTR_LISTA_PRODUCTOS, pedidos);
//    
//    // El Servlet usa la constante de ruta
//    request.getRequestDispatcher(Constantes.VISTA_LISTAR_PEDIDOS).forward(request, response); 
//} 
////u