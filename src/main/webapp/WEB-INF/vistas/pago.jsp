<%-- Archivo: /WEB-INF/vistas/pago.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Datos de Envío y Pago - Pollos Hermanos Díaz</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style> body { font-family: 'Inter', sans-serif; background-color: #f7f7f7; } </style>
</head>
<body class="p-8">

    <div class="max-w-xl mx-auto bg-white p-8 rounded-xl shadow-2xl">
        <h1 class="text-3xl font-bold text-red-700 mb-6 border-b pb-2">💳 Finalizar Pedido</h1>
        <p class="text-gray-600 mb-6">Por favor, verifica tus datos de envío antes de pagar.</p>

        <form action="${pageContext.request.contextPath}/pago" method="post" class="space-y-4">
            
            <c:set var="cliente" value="${sessionScope.cliente}"/>
            
            <div>
                <label for="nombre" class="block text-sm font-medium text-gray-700">Nombre</label>
                <input type="text" id="nombre" name="nombre" 
                       value="${cliente.nombre}" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>

            <div>
                <label for="apellido" class="block text-sm font-medium text-gray-700">Apellido</label>
                <input type="text" id="apellido" name="apellido" 
                       value="${cliente.apellido}" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>

            <div>
                <label for="direccion" class="block text-sm font-medium text-gray-700">Dirección de Envío</label>
                <input type="text" id="direccion" name="direccion" 
                       value="${cliente.direccion}" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>
            
            <div>
                <label for="telefono" class="block text-sm font-medium text-gray-700">Teléfono</label>
                <input type="tel" id="telefono" name="telefono" 
                       value="${cliente.telefono}" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>
                       
            <div>
                <label for="tarjetaNumero" class="block text-sm font-medium text-gray-700">Numero de tarjeta</label>
                <input id="tarjetaNumero" name="tarjetaNumero" 
                       value="" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>
            
            <div>
                <label for="tarjetaExpiracion" class="block text-sm font-medium text-gray-700">Fecha de expiración de la tarjeta</label>
                <input id="tarjetaExpiracion" name="tarjetaExpiracion" 
                       value="" required 
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>
            
            <div>
                <label for="CCV" class="block text-sm font-medium text-gray-700">CCV</label>
                <input id="CCV" name="CCV" 
                       value="" required
                       class="mt-1 block w-full rounded-md border-gray-300 shadow-sm p-2 border focus:ring-red-500 focus:border-red-500">
            </div>
                       
            <div class="pt-4">
                <button type="submit" 
                        class="w-full py-3 px-4 border border-transparent rounded-md shadow-sm text-lg font-medium text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 transition duration-150">
                    Finalizar Pago
                </button>
            </div>
        </form>
    </div>
</body>
</html>