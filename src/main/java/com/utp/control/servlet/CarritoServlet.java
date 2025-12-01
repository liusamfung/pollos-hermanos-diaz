// Archivo: com/utp/control/servlet/CarritoServlet.java
package com.utp.control.servlet;

import com.utp.control.facade.ProductoFacade;
import com.utp.control.facade.impl.ProductoFacadeImpl;
import com.utp.modelo.dao.ProductoDAO;
import com.utp.modelo.dao.impl.ProductoDAOImplMySQL;
import com.utp.modelo.dto.ProductoDTO;
import com.utp.modelo.dto.ItemCarritoDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
    
    private ProductoFacade productoFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        // INYECCIÓN MANUAL DE DEPENDENCIAS (Igual que en CatalogoServlet)
        ProductoDAO productoDAO = new ProductoDAOImplMySQL();
        this.productoFacade = new ProductoFacadeImpl(productoDAO);
        System.out.println("DEBUG: Dependencias de CarritoServlet inyectadas manualmente.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige a la vista del carrito
        request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // La acción por defecto es "agregar", ya que es lo único que el botón del catálogo enviará
        String accion = request.getParameter("accion"); 

        if ("agregar".equals(accion)) {
            agregarProductoACarrito(request, response);
        } else {
            // Manejar otras acciones POST (eliminar, actualizar)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción de carrito no reconocida.");
        }
    }
    
    private void agregarProductoACarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long idProducto = Long.parseLong(request.getParameter("idProducto"));
            
            // 1. Buscar el ProductoDTO completo (usando el Facade/DAO)
            ProductoDTO producto = productoFacade.buscarPorId(idProducto);
            
            if (producto == null) {
                // Manejo de error si el ID no existe en la DB
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado.");
                return;
            }
            
            // 2. Obtener la sesión y la lista del carrito
            HttpSession session = request.getSession();
            List<ItemCarritoDTO> carrito = (List<ItemCarritoDTO>) session.getAttribute("carrito");
            
            if (carrito == null) {
                carrito = new ArrayList<>();
                session.setAttribute("carrito", carrito);
            }
            
            // 3. Lógica para agregar o incrementar
            boolean encontrado = false;
            for (ItemCarritoDTO item : carrito) {
                if (item.getProducto().getId() == idProducto) {
                    item.setCantidad(item.getCantidad() + 1); 
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                carrito.add(new ItemCarritoDTO(producto, 1)); // Agregar nuevo ítem con cantidad 1
            }
            
            // 4. Redirigir de nuevo al catálogo (Post-Redirect-Get)
            response.sendRedirect(request.getContextPath() + "/catalogo"); 

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido.");
        } catch (Exception e) {
            // Manejo de errores de base de datos o fachada
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno al procesar el carrito.");
        }
    }
}