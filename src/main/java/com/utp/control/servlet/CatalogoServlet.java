package com.utp.control.servlet;

import com.utp.modelo.dao.ProductoDAO;
import com.utp.modelo.dao.impl.ProductoDAOImplMySQL;
import com.utp.control.facade.ProductoFacade;
import com.utp.control.facade.impl.ProductoFacadeImpl;
import com.utp.modelo.dto.ProductoDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

// 1. Declaración de la dependencia usando la INTERFAZ (DIP)
    private ProductoFacade productoFacade;

    @Override
    public void init() throws ServletException {
        super.init();

        // 2. INYECCIÓN MANUAL DE DEPENDENCIAS (DI / DIP)
        ProductoDAO productoDAO = new ProductoDAOImplMySQL();

        this.productoFacade = new ProductoFacadeImpl(productoDAO);
        System.out.println("DEBUG: Dependencias de CatalogoServlet inyectadas manualmente.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ProductoDTO> productos = productoFacade.getCatalogoParaVenta();

            System.out.println(productos);

            request.setAttribute("productos", productos);

            // Envía la vista (JSP)
            request.getRequestDispatcher("/WEB-INF/vistas/catalogo.jsp").forward(request, response);

        } catch (Exception e) {
            // errores centralizado
            request.setAttribute("errorMensaje", "Ocurrió un error al cargar el catálogo: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/vistas/error.jsp").forward(request, response);
        }
    }

    // Aquí iría el doPost(HttpServletRequest request, HttpServletResponse response)
    // para manejar pedidos, etc.
}
