<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo - Pollos Hermanos Díaz</title>
    <!-- Usando Tailwind CSS para un look limpio -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body { font-family: 'Inter', sans-serif; background-color: #f7f7f7; }
    </style>
</head>
<body class="p-8">

    <div class="max-w-4xl mx-auto bg-white p-6 rounded-xl shadow-2xl">
        
        <!-- Bloque de Usuario Logueado (Cerrar Sesión) -->
        <c:if test="${not empty sessionScope.cliente}">
            <div class="mb-6 p-4 bg-red-100 border-l-4 border-red-500 text-red-800 rounded-md shadow-sm flex justify-between items-center">
                <p class="font-semibold text-lg">
                    Bienvenido, ${sessionScope.cliente.nombre}.
                </p>
                <a href="${pageContext.request.contextPath}/logout" class="text-red-600 hover:text-red-800 font-medium underline text-sm">
                    Cerrar Sesión
                </a>
            </div>
        </c:if>
        
        <!-- HEADER Y TÍTULO -->
        <h1 class="text-4xl font-bold text-red-700 mb-6 border-b pb-2">🐔 Nuestro Catálogo de Productos</h1>
        
        <!-- NUEVOS BOTONES DE NAVEGACIÓN AÑADIDOS AQUÍ -->
        <div class="flex flex-wrap gap-4 mb-8">
            <!-- 1. Botón "Ir a Inicio" -->
            <a href="${pageContext.request.contextPath}/index.html" 
               class="flex-1 text-center px-4 py-2 bg-gray-200 text-gray-700 font-semibold rounded-lg hover:bg-gray-300 transition shadow-md">
                Ir a Inicio
            </a>
            
            <!-- 2. Botón "Loguearme" (Solo visible si NO está logueado) -->
            <c:if test="${empty sessionScope.cliente}">
                <a href="${pageContext.request.contextPath}/login" 
                   class="flex-1 text-center px-4 py-2 bg-blue-500 text-white font-semibold rounded-lg hover:bg-blue-600 transition shadow-md">
                    Loguearme
                </a>
            </c:if>

            <!-- 3. Botón "Ir al Carrito" (Existente, movido a la barra de navegación) -->
            <a href="${pageContext.request.contextPath}/carrito"
               class="flex-1 text-center px-4 py-2 bg-red-600 text-white font-bold rounded-lg hover:bg-red-700 transition shadow-lg">
                🛒 Ir al Carrito
            </a>
        </div>


        <p class="text-gray-600 mb-8">
            Bienvenido al menú oficial. Todos los precios están en PEN.
        </p>

        <!-- Uso de JSTL para iterar sobre la lista de Productos -->
        <div class="space-y-6">
            <c:choose>
                <c:when test="${not empty requestScope.productos}">
                    <!-- Iteración con la etiqueta JSTL 'forEach' -->
                    <c:forEach var="producto" items="${requestScope.productos}">
                        <div class="bg-gray-50 border border-gray-200 p-4 rounded-lg shadow-sm hover:shadow-md transition duration-300 flex justify-between items-center">
                            
                            <!--Expression Language (EL) para DTOs-->
                            <div>
                                <h2 class="text-xl font-semibold text-gray-800">${producto.nombre}</h2>
                                <p class="text-sm text-gray-500 mt-1">${producto.descripcion}</p>
                            </div>
                            
                            <div class="text-right">
                                <!-- Formateo del precio: JSTL permite formatear números -->
                                <p class="text-2xl font-bold text-green-600">
                                    S/. 
                                    <c:out value="${producto.precio}" /> 
                                </p>
                                
                               <form action="${pageContext.request.contextPath}/carrito" method="post">
                                    <input type="hidden" name="idProducto" value="${producto.id}">
                                    <input type="hidden" name="accion" value="agregar">
                                    
                                    <button type="submit" class="mt-2 px-4 py-2 bg-red-600 text-white text-sm font-medium rounded-full hover:bg-red-700 transition">
                                        Añadir
                                    </button>
                                </form>
                                
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="text-center p-10 bg-yellow-100 rounded-lg text-yellow-700">
                        Aún no hay productos disponibles en el catálogo.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <footer class="mt-10 pt-4 border-t text-center text-sm text-gray-500">
            Renderizado por JSP (Vista) | Datos obtenidos del Servlet (Controlador) a través del Facade.
        </footer>
    </div>

</body>
</html>