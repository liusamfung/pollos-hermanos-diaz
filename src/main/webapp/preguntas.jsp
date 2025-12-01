<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preguntas Frecuentes - Pollos Hermanos Diaz</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <style>
            /* Estilo suave para el acordeón */
            .accordion-button:not(.collapsed) {
                background-color: #fff3cd; /* Un amarillo muy suave al abrir */
                color: #000;
                font-weight: bold;
            }
            .accordion-button:focus {
                box-shadow: none; /* Quita el borde azul por defecto */
                border-color: #ffc107;
            }
            .faq-header {
                max-width: 700px;
                margin: 0 auto;
            }
        </style>
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

        <div class="container py-5 text-center">
            <div class="faq-header">
                <h1 class="display-5 fw-bold mb-3">Preguntas Frecuentes</h1>
                <p class="lead text-muted">Resolvemos tus dudas sobre nuestros servicios, delivery y horarios.</p>
            </div>
        </div>

        <div class="container mb-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    
                    <div class="accordion shadow-sm" id="accordionFAQ">
                        
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    ¿Cuál es el horario de atención?
                                </button>
                            </h2>
                            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionFAQ">
                                <div class="accordion-body">
                                    Atendemos todos los días de la semana, incluidos feriados.
                                    <br>
                                    <strong>Lunes a Jueves:</strong> 12:00 PM - 10:00 PM
                                    <br>
                                    <strong>Viernes a Domingo:</strong> 12:00 PM - 11:30 PM
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingTwo">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    ¿Hasta dónde llega el Delivery?
                                </button>
                            </h2>
                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionFAQ">
                                <div class="accordion-body">
                                    Nuestro servicio de delivery cubre todo el distrito y zonas aledañas (hasta 5km a la redonda). El tiempo aproximado de entrega es de <strong>30 a 45 minutos</strong> dependiendo de la demanda.
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingThree">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    ¿Qué medios de pago aceptan?
                                </button>
                            </h2>
                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionFAQ">
                                <div class="accordion-body">
                                    Aceptamos todas las formas de pago para tu comodidad:
                                    <ul>
                                        <li>Efectivo (soles)</li>
                                        <li>Tarjetas de Crédito y Débito (Visa, Mastercard)</li>
                                        <li>Billeteras digitales: <strong>Yape y Plin</strong></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFour">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    ¿Hacen reservas para cumpleaños?
                                </button>
                            </h2>
                            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionFAQ">
                                <div class="accordion-body">
                                    ¡Por supuesto! Nos encanta celebrar contigo. Puedes reservar una mesa para grupos mayores a 6 personas llamándonos o escribiéndonos al WhatsApp. Si es tu cumpleaños, ¡el postre va por nuestra cuenta!
                                </div>
                            </div>
                        </div>

                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingFive">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    ¿Venden solo papas o cremas por separado?
                                </button>
                            </h2>
                            <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionFAQ">
                                <div class="accordion-body">
                                    Sí, puedes pedir porciones extra de papas fritas, ensalada o nuestros potes de ají y mayonesa casera de 250gr para llevar.
                                </div>
                            </div>
                        </div>

                    </div> </div>
            </div>
        </div>

        <div class="container text-center py-4 mb-4">
            <h4 class="fw-bold">¿No encontraste tu respuesta?</h4>
            <p class="text-muted">Escríbenos directamente y te ayudaremos al instante.</p>
            <a href="https://www.whatsapp.com/download?lang=es" class="btn btn-success btn-lg">
                <i class="bi bi-whatsapp"></i> Escribir al WhatsApp
            </a>
        </div>

        <jsp:include page="WEB-INF/common/footer.jsp"></jsp:include>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>