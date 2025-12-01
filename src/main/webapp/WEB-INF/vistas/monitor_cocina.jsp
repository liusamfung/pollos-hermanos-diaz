<%-- Archivo: /WEB-INF/vistas/monitor_cocina.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Monitor de Cocina - Pollos Hermanos Díaz</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style> body { font-family: 'Inter', sans-serif; background-color: #f7f7f7; } </style>
</head>
<body class="p-8">

    <div class="max-w-7xl mx-auto">
        <h1 class="text-4xl font-bold text-gray-800 mb-6 border-b pb-2">🍳 Monitor de Pedidos</h1>
        
        <div class="flex flex-wrap gap-6">
            
            <c:forEach var="pedido" items="${requestScope.pedidos}">
                
                <c:choose>
                    <c:when test="${pedido.estado eq 'PENDIENTE'}">
                        <c:set var="cardColor" value="bg-green-100 border-green-500 shadow-lg"/>
                        <c:set var="statusText" value="text-red-600 font-bold"/>
                        <c:set var="statusLabel" value="Nuevo pedido"/>
                        <c:set var="showActions" value="true"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="cardColor" value="bg-gray-200 border-gray-400 opacity-70"/>
                        <c:set var="statusText" value="text-gray-500 font-medium"/>
                        <c:set var="statusLabel" value="Pedido realizado"/>
                        <c:set var="showActions" value="false"/>
                    </c:otherwise>
                </c:choose>
                
                <div class="w-full sm:w-64 p-4 rounded-lg border-2 ${cardColor}">
                    <p class="text-sm ${statusText} mb-2">${statusLabel}</p>
                    <p class="text-sm text-gray-700">Código: <b><c:out value="${pedido.codigo}"/></b></p>
                    <p class="text-lg font-semibold text-gray-900 mb-1">Principal: <c:out value="${pedido.principal}"/></p>
                    <p class="text-sm text-gray-600 mb-4">Consideraciones: <c:out value="${pedido.consideraciones}"/></p>
                    
                    <c:if test="${showActions}">
                        <div class="flex justify-between space-x-2">
                            <form action="${pageContext.request.contextPath}/admin/pedidos" method="post" class="w-1/2">
                                <input type="hidden" name="codigoPedido" value="${pedido.codigo}">
                                <input type="hidden" name="accion" value="rechazar">
                                <button type="submit" class="w-full py-2 bg-red-400 text-white font-medium rounded hover:bg-red-500">RECHAZAR</button>
                            </form>
                            
                            <form action="${pageContext.request.contextPath}/admin/pedidos" method="post" class="w-1/2">
                                <input type="hidden" name="codigoPedido" value="${pedido.codigo}">
                                <input type="hidden" name="accion" value="listo">
                                <button type="submit" class="w-full py-2 bg-blue-500 text-white font-medium rounded hover:bg-blue-600">Listo</button>
                            </form>
                        </div>
                    </c:if>
                    
                    <c:if test="${!showActions}">
                        <button class="w-full py-2 bg-gray-400 text-white font-medium rounded opacity-50 cursor-not-allowed">Olvidar</button>
                    </c:if>
                </div>
            </c:forEach>
            
        </div>
    </div>

</body>
</html>