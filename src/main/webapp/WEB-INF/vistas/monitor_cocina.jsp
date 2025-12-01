<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Monitor de Cocina - Pollos Hermanos Díaz</title>
    <!-- Recarga automática cada 15 segundos -->
    <meta http-equiv="refresh" content="15"> 
    <script src="https://cdn.tailwindcss.com"></script>
    <style> 
        body { font-family: 'Inter', sans-serif; background-color: #fafafa; } 
        .card:hover { transform: translateY(-2px); box-shadow: 0 10px 15px rgba(0,0,0,0.1); }
    </style>
</head>
<body class="p-6">
    <div class="max-w-full mx-auto">
        <h1 class="text-4xl font-extrabold text-red-700 mb-6 border-b-4 border-red-200 pb-3 flex items-center">
            <span class="mr-3"></span> Cocina: Pedidos Pendientes
        </h1>

        <c:if test="${not empty mensajeError}">
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                <c:out value="${mensajeError}"/>
            </div>
        </c:if>
        
        <div class="flex flex-wrap gap-6">
            <c:choose>
                <c:when test="${empty requestScope.pedidos}">
                    <div class="w-full text-center p-12 bg-white rounded-xl shadow text-gray-400">
                        <p class="text-2xl">No hay pedidos pendientes.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="pedido" items="${requestScope.pedidos}">
                        <div class="w-full sm:w-80 p-5 rounded-xl bg-white border-4 border-red-500 shadow-xl card transition-all duration-300">
                            
                            <!-- Cabecera Tarjeta -->
                            <div class="flex justify-between items-start mb-3">
                                <h2 class="text-2xl font-bold text-gray-900">#<c:out value="${pedido.id}"/></h2>
                                <span class="text-sm font-bold text-red-700 bg-red-100 px-3 py-1 rounded-full">
                                    <c:out value="${pedido.estado}"/>
                                </span>
                            </div>
                            
                            <!-- Hora -->
                            <p class="text-xs text-gray-500 mb-4 font-mono">
                                Hora: 
                                <fmt:parseDate value="${pedido.fechaPedido}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both"/>
                                <fmt:formatDate value="${parsedDate}" pattern="HH:mm:ss"/>
                            </p>
                            
                            <!-- Lista de Productos -->
                            <div class="space-y-2 mb-4 p-3 bg-gray-50 rounded border border-gray-200">
                                <c:forEach var="detalle" items="${pedido.detalles}">
                                    <div class="flex justify-between text-gray-800">
                                        <span class="font-bold text-lg"><c:out value="${detalle.cantidad}"/>x</span>
                                        <span class="text-sm"><c:out value="${detalle.nombreProducto}"/></span>
                                    </div>
                                </c:forEach>
                            </div>
                            
                            <!-- Botones de Acción -->
                            <div class="flex space-x-2 mt-4">
                                <form action="${pageContext.request.contextPath}/admin/pedidos/actualizar" method="post" class="w-1/2">
                                    <input type="hidden" name="idPedido" value="${pedido.id}">
                                    <input type="hidden" name="nuevoEstado" value="RECHAZADO">
                                    <button onclick="return confirm('¿Rechazar pedido?');" class="w-full py-2 bg-gray-200 hover:bg-gray-300 text-gray-700 rounded font-bold">
                                        Rechazar
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/admin/pedidos/actualizar" method="post" class="w-1/2">
                                    <input type="hidden" name="idPedido" value="${pedido.id}">
                                    <input type="hidden" name="nuevoEstado" value="LISTO">
                                    <button class="w-full py-2 bg-green-600 hover:bg-green-700 text-white rounded font-bold shadow-md">
                                        ¡LISTO!
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>