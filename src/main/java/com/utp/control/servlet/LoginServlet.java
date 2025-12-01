package com.utp.control.servlet;

import com.utp.control.facade.ClienteFacade;
import com.utp.control.facade.impl.ClienteFacadeImpl;
import com.utp.modelo.dao.ClienteDAO;
import com.utp.modelo.dao.impl.ClienteDAOImplMySQL;
import com.utp.modelo.dto.ClienteDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // 1. Declaración de la dependencia usando la INTERFAZ (DIP)
    private ClienteFacade clienteFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        // 2. INYECCIÓN MANUAL DE DEPENDENCIAS (DI / DIP)
        ClienteDAO clienteDAO = new ClienteDAOImplMySQL();

        this.clienteFacade = new ClienteFacadeImpl(clienteDAO);
        System.out.println("DEBUG: Dependencias de LoginServlet inyectadas manualmente.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Envía la vista (JSP)
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    // POST: Procesa los datos del formulario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recibir parámetros
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {            
            ClienteDTO clienteLogueado = clienteFacade.login(email, password);

            if (clienteLogueado != null) {
                HttpSession session = request.getSession();

                //Guardar el objeto completo en la sesión
                session.setAttribute("cliente", clienteLogueado);

                // C. Verificación de ROL
                String rol = clienteLogueado.getRol();

                if ("ADMIN".equalsIgnoreCase(rol)) {
                    // Si es admin se mandara a un apartado especial
//                    response.sendRedirect(request.getContextPath() + "/adminnnnnn.html");
                } else {
                    // Si es cliente lo mandamos al inicio
//                    Cuando usas response.sendRedirect(request.getContextPath() + "/catalogo"):
//                         El navegador del cliente recibe una respuesta del servidor indicando que debe hacer una nueva solicitud GET al URL /catalogo.
//                         Esta nueva solicitud es capturada por el CatalogoServlet (que está mapeado a /catalogo).
                    response.sendRedirect(request.getContextPath() + "/catalogo");
                }

            } else {
                // Login fallido
                request.setAttribute("errorMensaje", "El email o la contraseña son incorrectos.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // error centralizado
            request.setAttribute("errorMensaje", "Ocurrió un error en el sistema: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/vistas/error.jsp").forward(request, response);
        }
    }
}
