<%-- Archivo: /WEB-INF/vistas/confirmacion.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmación</title>
    <meta http-equiv="refresh" content="3; url=${pageContext.request.contextPath}/index.jsp">
    <script src="https://cdn.tailwindcss.com"></script>
    <style> body { font-family: 'Inter', sans-serif; background-color: #f7f7f7; } </style>
</head>
<body class="p-8 flex items-center justify-center min-h-screen">

    <div class="max-w-md mx-auto bg-white p-10 rounded-xl shadow-2xl text-center">
        <svg class="mx-auto h-16 w-16 text-green-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <h1 class="mt-4 text-3xl font-extrabold text-green-700">¡Pedido Confirmado!</h1>
        <p class="mt-2 text-xl text-gray-600 font-semibold">
            ${requestScope.mensajeConfirmacion}
        </p>
    </div>
</body>
</html>