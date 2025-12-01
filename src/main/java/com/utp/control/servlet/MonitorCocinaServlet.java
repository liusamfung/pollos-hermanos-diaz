package com.utp.control.servlet;

import com.utp.control.facade.PedidoFacade;
import com.utp.modelo.dto.PedidoDTO;
import com.utp.util.DependencyInitializer; 
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/monitor-cocina")
public class MonitorCocinaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PedidoFacade pedidoFacade; 

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtenemos la instancia lista del Facade
        this.pedidoFacade = DependencyInitializer.getPedidoFacade(); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // La cocina solo quiere ver lo que está PENDIENTE
        final String ESTADO_PENDIENTE = "PENDIENTE";
        
        try {
            if (this.pedidoFacade != null) {
                List<PedidoDTO> pedidosPendientes = this.pedidoFacade.obtenerPedidosPorEstado(ESTADO_PENDIENTE); 
                request.setAttribute("pedidos", pedidosPendientes);
            } else {
                 request.setAttribute("mensajeError", "Error crítico: Sistema de pedidos no disponible.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar pedidos.");
        }
        
        request.getRequestDispatcher("/WEB-INF/vistas/monitor_cocina.jsp").forward(request, response);
    }
}