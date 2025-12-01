package com.utp.control.servlet;

import com.utp.control.facade.PedidoFacade;
import com.utp.util.DependencyInitializer; 
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/pedidos/actualizar")
public class AdminPedidoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PedidoFacade pedidoFacade; 

    @Override
    public void init() throws ServletException {
        super.init();
        this.pedidoFacade = DependencyInitializer.getPedidoFacade(); 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idPedidoStr = request.getParameter("idPedido");
        String nuevoEstado = request.getParameter("nuevoEstado");

        if (idPedidoStr == null || nuevoEstado == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos.");
            return;
        }

        try {
            long idPedido = Long.parseLong(idPedidoStr);
            
            boolean exito = this.pedidoFacade.actualizarEstadoPedido(idPedido, nuevoEstado);
            
            if (exito) {
                // Redirigir de vuelta al monitor (el pedido desaparecerá si ya no es PENDIENTE)
                response.sendRedirect(request.getContextPath() + "/admin/monitor-cocina");
            } else {
                request.setAttribute("mensajeError", "No se pudo actualizar el estado.");
                request.getRequestDispatcher("/WEB-INF/vistas/monitor_cocina.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en servidor.");
        }
    }
}