<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pollos Hermanos Diaz</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
        crossorigin="anonymous"></script>
    </head>

    <body class="bg-light">
        <c:choose>
            <c:when test="${not empty sessionScope.cliente}">
                <%-- Si existe 'cliente' en la sesión (Usuario Logueado) --%>
                <jsp:include page="WEB-INF/common/header_logueado.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <%-- Si NO existe 'cliente' (Visitante / No Logueado) --%>
                <jsp:include page="WEB-INF/common/header.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>
        <div class="bg-dark text-white py-5 text-center">
            <div class="container">
                <h1 class="display-4 fw-bold">Nuestra Historia</h1>
                <p class="lead text-white-50">Más que una pollería, una tradición familiar desde 1995.</p>
            </div>
        </div>

        <div class="container py-5 my-4">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <h2 class="fw-bold mb-4 text-danger">Los Inicios de los Hermanos Diaz</h2>
                    <p class="lead">Todo comenzó con un pequeño horno de barro y un sueño.</p>
                    <p>
                        Hace más de 20 años, decidimos traer la receta secreta de la abuela a la ciudad. 
                        Lo que empezó como un pequeño puesto de fin de semana, se convirtió rápidamente en 
                        el lugar favorito del barrio gracias a nuestro inconfundible sabor ahumado y nuestras 
                        papas nativas siempre crocantes.
                    </p>
                    <p>
                        En <strong>Pollos Hermanos Diaz</strong>, creemos que la comida une a las personas. 
                        Por eso, cada pollo que sale de nuestro horno lleva el mismo cariño y dedicación 
                        que el primero que servimos.
                    </p>
                </div>
                <div class="col-lg-6">
                    <img src="assets/img/restaurante_clientes.jpg" class="img-fluid img-historia" alt="Nuestra historia">
                </div>
            </div>
        </div>

        <hr class="container featurette-divider">

        <div class="bg-light py-5">
            <div class="container text-center">
                <div class="row mb-5">
                    <div class="col-12">
                        <h2 class="fw-bold">¿Por qué elegirnos?</h2>
                        <p class="text-muted">Tres razones que nos hacen únicos.</p>
                    </div>
                </div>
                <div class="row g-4">
                    <div class="col-md-4">
                        <div class="card h-100 border-0 shadow-sm p-3">
                            <div class="card-body">
                                <div class="mb-3 text-warning">
                                    <i class="bi bi-fire display-4"></i> </div>
                                <h4 class="card-title">Brasa Auténtica</h4>
                                <p class="card-text">Usamos 100% carbón de algarrobo y leña seleccionada para garantizar ese sabor ahumado que nos caracteriza.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card h-100 border-0 shadow-sm p-3">
                            <div class="card-body">
                                <div class="mb-3 text-warning">
                                    <i class="bi bi-heart-fill display-4"></i> </div>
                                <h4 class="card-title">Insumos Frescos</h4>
                                <p class="card-text">Nuestras papas son peladas y cortadas a diario. Nada congelado. El pollo llega fresco cada mañana.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card h-100 border-0 shadow-sm p-3">
                            <div class="card-body">
                                <div class="mb-3 text-warning">
                                    <i class="bi bi-house-door-fill display-4"></i> </div>
                                <h4 class="card-title">Ambiente Familiar</h4>
                                <p class="card-text">Un lugar seguro, amplio y cómodo donde los niños pueden jugar y los adultos disfrutar tranquilos.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container py-5 text-center">
            <h2 class="mb-4">¿Ya se te antojó?</h2>
            <p class="lead mb-4">Ven a visitarnos o pide por delivery y disfruta del verdadero sabor.</p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <a href="carta.jsp" class="btn btn-warning btn-lg px-4 gap-3">Ver Menú</a>
                <a href="https://www.whatsapp.com/download?lang=es" class="btn btn-outline-dark btn-lg px-4">Reservar Mesa</a>
            </div>
        </div>

        <jsp:include page="WEB-INF/common/footer.jsp"></jsp:include>
    </body>
</html>
