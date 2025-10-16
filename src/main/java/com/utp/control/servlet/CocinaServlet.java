//package com.utp.control.servlet;
//
//import com.utp.control.facade.PedidoFacade;
//import com.utp.control.facade.impl.PedidoFacadeImpl;
//import com.utp.modelo.dao.PedidoDAO;
//import com.utp.modelo.dao.impl.PedidoDAOImplMySQL;
//import com.utp.modelo.dto.PedidoDTO;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/cocina")
//public class CocinaServlet extends HttpServlet {
//
//    private PedidoFacade pedidoFacade;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        
//        // ************************************************
//        // INYECCIÓN MANUAL DE DEPENDENCIAS SIMPLIFICADA
//        // ************************************************
//        
//        // A. Instanciar la dependencia de nivel inferior (Pedido DAO, ahora completo)
//        PedidoDAO pedidoDAO = new PedidoDAOImplMySQL();
//        
//        // B. Instanciar el Facade e inyectarle el PedidoDAO
//        this.pedidoFacade = new PedidoFacadeImpl(pedidoDAO);
//        
//        System.out.println("DEBUG: Dependencias de CocinaServlet inyectadas manualmente y simplificadas.");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            // 1. Obtener todos los pedidos con estado "PENDIENTE"
//            List<PedidoDTO> pedidosPendientes = pedidoFacade.obtenerPedidosPendientes();
//
//            // 2. Guardar el DTO en el Request
//            request.setAttribute("pedidos", pedidosPendientes);
//
//            // 3. Envío a la vista (JSP)
//            request.getRequestDispatcher("/WEB-INF/vistas/monitor_cocina.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            request.setAttribute("errorMensaje", "Error al cargar pedidos de cocina: " + e.getMessage());
//            request.getRequestDispatcher("/WEB-INF/vistas/error.jsp").forward(request, response);
//        }
//    }
//    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String idPedidoStr = request.getParameter("idPedido");
//        
//        if (idPedidoStr != null) {
//            try {
//                long idPedido = Long.parseLong(idPedidoStr);
//                pedidoFacade.marcarComoDespachado(idPedido);
//                
//                response.setStatus(HttpServletResponse.SC_OK); // 200 OK para AJAX
//                
//            } catch (NumberFormatException e) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de pedido inválido.");
//            } catch (Exception e) {
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al despachar el pedido: " + e.getMessage());
//            }
//        } else {
//             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetro 'idPedido' faltante.");
//        }
//    }
//}