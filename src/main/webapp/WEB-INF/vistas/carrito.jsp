<%-- Archivo: /WEB-INF/vistas/carrito.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resumen de Pedido - Pollos Hermanos Díaz</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style> body { font-family: 'Inter', sans-serif; background-color: #f7f7f7; } </style>
</head>
<body class="p-8">

    <div class="max-w-4xl mx-auto bg-white p-6 rounded-xl shadow-2xl">
        <h1 class="text-4xl font-bold text-red-700 mb-6 border-b pb-2">🛒 Tu Carrito de Compras</h1>
        
        <c:set var="totalPagar" value="${0}"/>
        
        <c:choose>
            <c:when test="${not empty sessionScope.carrito}">
                
                <div class="space-y-4">
                    <c:forEach var="item" items="${sessionScope.carrito}">
                        <div class="bg-blue-50 border-l-4 border-red-500 p-4 flex justify-between items-center">
                            <div>
                                <h2 class="text-lg font-semibold text-gray-800">${item.producto.nombre}</h2>
                                <p class="text-sm text-gray-500">Cantidad: ${item.cantidad}</p>
                            </div>
                            <div class="text-right">
                                <p class="text-xl font-bold text-red-600">
                                    S/. <c:out value="${item.subtotal}" /> 
                                </p>
                            </div>
                        </div>
                        <c:set var="totalPagar" value="${totalPagar + item.subtotal}"/>
                    </c:forEach>
                </div>

                <div class="mt-8 pt-4 border-t-2 border-dashed flex justify-between items-center">
                    <p class="text-2xl font-bold">Total a Pagar:</p>
                    <p class="text-3xl font-extrabold text-red-700">S/. <c:out value="${totalPagar}" /></p>
                </div>
                
                <a href="${pageContext.request.contextPath}/pago" 
                   class="mt-6 inline-block w-full px-6 py-3 bg-green-600 text-white font-bold rounded-lg shadow-lg text-center 
                          hover:bg-green-700 transition duration-300 transform hover:scale-105">
                    Procesar Pago y Datos de Envío
                </a>

            </c:when>
            <c:otherwise>
                <div class="text-center p-10 bg-yellow-100 rounded-lg text-yellow-700">
                    Tu carrito de compras está vacío. <br><a href="${pageContext.request.contextPath}/catalogo" class="text-blue-600 underline">Ir al Catálogo</a>
                </div>
            </c:otherwise>
        </c:choose>
        
        <footer class="mt-10 pt-4 border-t text-center text-sm text-gray-500">
            Renderizado por JSP (Vista) | Carrito almacenado en la Sesión HTTP.
        </footer>
    </div>

</body>
</html>