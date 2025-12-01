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
                <jsp:include page="WEB-INF/common/header_logueado.jsp"></jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="WEB-INF/common/header.jsp"></jsp:include>
            </c:otherwise>
        </c:choose>
        <!--Carrusel de Imagenes-->
        <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-label="Slide 1" aria-current="true"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>

            <div class="carousel-inner">

                <div class="carousel-item active">
                    <img src="assets/img/polleria_plano.jpg" class="d-block w-100" alt="Descripción imagen 1">

                    <div class="container">
                        <div class="carousel-caption text-start">
                            <h1><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Multiples Ambientes</span></h1>
                            <p><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Los multiples ambientes permiten al cliente estar a gusto.</span></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <img src="assets/img/restaurante_clientes.jpg" class="d-block w-100" alt="Descripción imagen 2">

                    <div class="container">
                        <div class="carousel-caption">
                            <h1><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Para disfrutar en familia.</span></h1>
                            <p><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Ven con tu familia a disfrutar del mejor pollo a la brasa.</span></p>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <img src="assets/img/cocinero_2.jpg" class="d-block w-100" alt="Descripción imagen 3">

                    <div class="container">
                        <div class="carousel-caption text-end">
                            <h1><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Cocineros estrella.</span></h1>
                            <p><span class="d-inline-block bg-dark bg-opacity-50 p-2 rounded">Los mejores cocineros del País.</span></p>
                        </div>
                    </div>
                </div>

            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!--divisor-->
        <div class="container my-5">
            <hr class="featurette-divider">
        </div>

        <!--        Contenido Adicional-->
        <div class="container marketing bg-light">

            <div class="row">

                <div class="col-lg-4">
                    <img src="assets/img/medio_pollo.png" class="bd-placeholder-img rounded-circle" width="140" height="140" style="object-fit: cover;" alt="Descripción 1">

                    <h2 class="fw-normal">Pollo a la Leña</h2>
                    <p>Nuestro marinado secreto de 24 horas y la cocción lenta a la leña garantizan el piel más crujiente y la carne más jugosa.</p>
                    <p><a class="btn btn-secondary text-black bg-warning" href="carta.jsp">Ver Carta »</a></p>
                </div>

                <div class="col-lg-4">
                    <img src="assets/img/delivery_test.jpg" class="bd-placeholder-img rounded-circle" width="140" height="140" style="object-fit: cover;" alt="Descripción 2">

                    <h2 class="fw-normal">Delivery Rápido</h2>
                    <p>¿Hambre y no quieres salir? Llevamos el sabor de los Hermanos Diaz directo a tu puerta, caliente y al instante.</p>
                    <p><a class="btn btn-secondary text-black bg-warning" href="catalogo">Pedir Ahora »</a></p>
                </div>

                <div class="col-lg-4">
                    <img src="assets/img/combo_pollo_1.webp" class="bd-placeholder-img rounded-circle" width="140" height="140" style="object-fit: cover;" alt="Descripción 3">

                    <h2 class="fw-normal">Combos Familiares</h2>
                    <p>Tenemos las mejores ofertas para compartir en familia. Packs económicos que incluyen papas, ensalada y bebida.</p>
                    <p><a class="btn btn-secondary text-black bg-warning" href="#">Ver Promos »</a></p>
                </div>
            </div>
        </div>
        <!--divisor-->
        <div class="container my-5">
            <hr class="featurette-divider">
        </div>
        <jsp:include page="WEB-INF/common/footer.jsp"></jsp:include>
    </body>
</html>