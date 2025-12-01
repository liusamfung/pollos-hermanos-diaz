package com.utp.control.servlet;

import com.utp.modelo.dto.ClienteDTO; // Asume que ya tienes este DTO
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pago")
public class PagoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // No crea sesión si no existe
        
        // 1. Verificar si el cliente está logueado
//        if (session == null || session.getAttribute("cliente") == null) {
//            // Si no está logueado, lo redirigimos al login (o al catálogo con error)
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }

        // 2. Si está logueado, simplemente redirigimos a la vista de pago
        // (La vista usará el objeto 'cliente' de la sesión para precargar datos)
        request.getRequestDispatcher("/WEB-INF/vistas/pago.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Simular el procesamiento del pago (aquí iría la lógica de persistencia del pedido)
        System.out.println("DEBUG: Pedido recibido y pago simulado.");
        // Nota: En una app real, aquí se verificarían todos los campos POST
        
        // 2. Eliminar el carrito de la sesión después del pago exitoso
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("carrito");
        }
        
        // 3. Mostrar el mensaje y redirigir
        // Usaremos una pequeña página de confirmación intermedia para mostrar el mensaje
        
        // Guardamos un mensaje en el request (solo dura esta solicitud)
        request.setAttribute("mensajeConfirmacion", "¡Pago finalizado con éxito! Redirigiendo al inicio...");
        
        // Redirigimos a una página de confirmación temporal con auto-redirección
        request.getRequestDispatcher("/WEB-INF/vistas/confirmacion.jsp").forward(request, response);
    }
}