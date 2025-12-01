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
    <style>
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
        .card {
            transition: transform 0.2s;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .precio {
            font-size: 1.2rem;
            font-weight: bold;
            color: #dc3545;
        }
    </style>
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
        <div class="container text-center py-5">
            <h1 class="display-5 fw-bold">Nuestra Carta</h1>
            <p class="lead text-muted">Disfruta del mejor sabor a la leña.</p>
        </div>

        <div class="container mb-5">
            <h2 class="border-bottom pb-2 mb-4">Pollos a la Brasa</h2>

            <div class="row row-cols-1 row-cols-md-3 g-4">

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/pollo_entero.jpg" class="card-img-top" alt="Pollo Entero">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Pollo Entero + Papas</h5>
                            <p class="card-text text-muted">
                                Un pollo entero marinado por 24 horas, acompañado de nuestras papas nativas fritas y ensalada clásica.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/medio_pollo.png" class="card-img-top" alt="1/2 Pollo">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">1/2 Pollo + Papas</h5>
                            <p class="card-text text-muted">
                                La porción perfecta para compartir. Medio pollo jugoso con papas crocantes y ensalada fresca.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/cuarto_pollo.jpg" class="card-img-top" alt="1/4 Pollo">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">1/4 de Pollo</h5>
                            <p class="card-text text-muted">
                                Ideal para uno. Elige entre parte pierna o pecho, acompañado de papas y cremas.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="container mb-5">
            <h2 class="border-bottom pb-2 mb-4">Parrillas y Extras</h2>

            <div class="row row-cols-1 row-cols-md-3 g-4">

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/anticuchos.jpg" class="card-img-top" alt="Anticuchos">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Anticuchos de Corazón</h5>
                            <p class="card-text text-muted">
                                Dos palitos de puro corazón de res macerados en ají panca, acompañados de papa dorada y choclo.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/parrilla_entera.jpg" class="card-img-top" alt="Mollejitas">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Mollejitas a la Parrilla</h5>
                            <p class="card-text text-muted">
                                Porción generosa de mollejitas de pollo marinadas al limón y parrilla. Incluye papas doradas.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0 shadow-sm">
                        <img src="assets/img/chicha.jpg" class="card-img-top" alt="Chicha">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Jarra de Chicha Morada</h5>
                            <p class="card-text text-muted">
                                1 Litro de nuestra chicha morada casera, hecha con maíz morado, piña, canela y clavo.
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <a href="catalogo" class="btn btn-warning">Ordenar</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <jsp:include page="WEB-INF/common/footer.jsp"></jsp:include>
    </body>
</html>
