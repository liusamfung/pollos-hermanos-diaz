package com.utp.util;

public final class Validacion {

    // Regex simple para validar un formato de email básico
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    /**
     * Verifica si una cadena no es nula y no está vacía (después de recortar espacios).
     * @param texto El String a validar.
     * @return true si la cadena es válida.
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Verifica si un valor es un entero válido y si cae dentro de un rango positivo.
     * @param valor El String que representa el número (ej: "15").
     * @return El entero parseado, o 0 si no es válido o es <= 0.
     */
    public static int esIdValido(String valor) {
        if (esTextoValido(valor)) {
            try {
                int id = Integer.parseInt(valor.trim());
                return id > 0 ? id : 0;
            } catch (NumberFormatException e) {
                return 0; // No es un número válido
            }
        }
        return 0; // Valor nulo o vacío
    }
    
    /**
     * Verifica que una cadena coincida con un patrón básico de email.
     * @param email El String del email.
     * @return true si el email es válido.
     */
    public static boolean esEmailValido(String email) {
        if (!esTextoValido(email)) {
            return false;
        }
        return email.matches(REGEX_EMAIL);
    }
    
    // Constructor privado para evitar instanciación
    private Validacion() {}
}

//
//Ejemplo de Uso en servletUsuario
//// ... dentro del método doPost ...
//
//String email = request.getParameter("emailCliente");
//String idStr = request.getParameter("idCliente");
//
//// VALIDACIÓN
//if (!Validacion.esTextoValido(email) || !Validacion.esEmailValido(email)) {
//    // Si la validación falla, redirigimos con un error.
//    request.setAttribute(Constantes.ATTR_MENSAJE_ERROR, "El formato del email es inválido.");
//    request.getRequestDispatcher(Constantes.VISTA_ERROR).forward(request, response);
//    return;
//}
//
//int idCliente = Validacion.esIdValido(idStr);
//if (idCliente == 0) {
//    request.setAttribute(Constantes.ATTR_MENSAJE_ERROR, "El ID de cliente es incorrecto.");
//    request.getRequestDispatcher(Constantes.VISTA_ERROR).forward(request, response);
//    return;
//}
//
//// Si llega aquí, los datos son válidos y se procede con la lógica de negocio.
//// ...