<header class="p-3 text-bg-danger">
    <div class="container">
        <!--        Boton de navegador-->
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <img src="assets/img/logo_polleria.png" alt="Logo simple" width="45" height="45">
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li>
                    <a href="index.jsp" class="nav-link px-2 text-warning">Pollos hermanos Diaz</a>
                </li>
                <li>
                    <a href="nosotros.jsp" class="nav-link px-2 text-white">Sobre Nosotros</a>
                </li>
                <li>
                    <a href="carta.jsp" class="nav-link px-2 text-white">Carta</a>
                </li>
                <li>
                    <a href="preguntas.jsp" class="nav-link px-2 text-white">FAQs</a>
                </li>
                <li>
                    <a href="https://www.whatsapp.com/download?lang=es" class="nav-link px-2 text-white">Contactanos</a>
                </li>
            </ul>
            <div class="dropdown text-end">
                <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="assets/img/icono_usuario.png" alt="icono_usuario" width="32" height="32" class="rounded-circle">
                    <span class="text-white me-2">Hola, ${sessionScope.cliente.nombre}</span>
                </a>
                <ul class="dropdown-menu text-small" style="">
                    <li>
                        <a class="dropdown-item" href="#">Mi Cuenta</a>
                    </li>
                    <li>
                        <a class="dropdown-item"href="catalogo">Hacer Pedido</a>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <a class="dropdown-item" href="logout">Cerrar Sesiòn</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
