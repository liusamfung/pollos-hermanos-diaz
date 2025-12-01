package com.utp.control.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Obtener la sesión existente (sin crear una nueva si no existe)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 2. Invalidar la sesión.
            // Esto elimina todos los atributos (cliente, carrito, etc.) y la termina.
            session.invalidate();
            System.out.println("DEBUG: Sesión invalidada exitosamente.");
        }
        
        // 3. Redirigir al usuario a la página de inicio o login
        // Usamos index.html por ser la página de inicio.
        response.sendRedirect(request.getContextPath() + "/index.jsp"); 
    }
}