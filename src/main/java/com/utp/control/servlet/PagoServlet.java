package com.utp.control.servlet;

import com.utp.control.facade.PedidoFacade;
import com.utp.control.facade.impl.PedidoFacadeImpl;
import com.utp.modelo.dao.PedidoDAO;
import com.utp.modelo.dao.impl.PedidoDAOImplMySQL;
import com.utp.modelo.dto.ClienteDTO; 
import com.utp.modelo.dto.ItemCarritoDTO;
import com.utp.modelo.dto.PedidoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pago")
public class PagoServlet extends HttpServlet {
     
    private PedidoFacade pedidoFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        // INYECCIÓN MANUAL DE DEPENDENCIAS (Añadimos la lógica de Pedido)
        PedidoDAO pedidoDAO = new PedidoDAOImplMySQL();
        this.pedidoFacade = new PedidoFacadeImpl(pedidoDAO);
        System.out.println("DEBUG: Dependencias de PagoServlet inyectadas manualmente.");
    }
    
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

        HttpSession session = request.getSession(false);
        
        // 1. Obtener datos clave de la sesión
        List<ItemCarritoDTO> carrito = (List<ItemCarritoDTO>) session.getAttribute("carrito");
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente"); // ¡Asumimos que está logueado!
        
        if (cliente == null || carrito == null || carrito.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Sesión de cliente o carrito no válidos.");
            return;
        }

        try {
            // 2. Lógica de persistencia del pedido
            
            // Asumimos que la validación de la tarjeta, etc., pasa (simulación)
            System.out.println("DEBUG: Pedido recibido y pago simulado.");
            
            // Llamada al Facade para registrar el pedido
            PedidoDTO pedidoRegistrado = pedidoFacade.procesarCompra(cliente.getId(), carrito);
            
            if (pedidoRegistrado == null) {
                throw new Exception("Error al registrar el pedido en la base de datos.");
            }
            
            System.out.println("DEBUG: Pedido N° " + pedidoRegistrado.getId() + " registrado con éxito.");
            
            // 3. Eliminar el carrito de la sesión después del pago exitoso
            session.removeAttribute("carrito");
            
            // 4. Mostrar el mensaje y redirigir
            request.setAttribute("mensajeConfirmacion", "¡Pago finalizado con éxito! Su pedido N°" + pedidoRegistrado.getId() + " está en estado PENDIENTE.");
            
            request.getRequestDispatcher("/WEB-INF/vistas/confirmacion.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de error si falla la DB o el Facade
            request.setAttribute("error", "Hubo un error al procesar su pedido: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/vistas/error.jsp").forward(request, response);
        }
    }
}