<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
        crossorigin="anonymous"></script>
        <link href="assets/css/sign-in.css" rel="stylesheet">
    </head>

    <body class="d-flex align-items-center py-4 bg-danger text-white">
        <main class="form-signin w-100 m-auto bg-light p-4 rounded-4 shadow-lg border border-dark">
            <form action="LoginServlet" method="POST">
                <div style="text-align: center;">
                    <img class="mb-4" src="assets/img/logo_polleria.png" alt="" width="72" height="57">
                    <h1 class="h3 mb-3 fw-normal">Inicio de Sesiòn</h1>
                </div>

                <div class="form-floating text-body mt-2">
                    <input type="email" name="email" class="form-control" id="floatingInput" placeholder="nombre@dominio.com" required>
                    <label for="floatingInput">Correo Electronico</label>
                </div>
                <div class="form-floating text-body mt-2">
                    <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="contraseña" required>
                    <label for="floatingPassword">Contraseña</label>
                </div>

                <div class="mt-3">
                    <button class="btn btn-warning w-100 py-2" type="submit">Iniciar Sesión</button>
                </div>
                <div class="form-floating text-body mt-2 text-center">
                    <a class="p-4 mb-0 text-danger text-center small" href="index.jsp">Volver al inicio</a>
                </div>
            </form>
        </main>
    </body>
</html>
