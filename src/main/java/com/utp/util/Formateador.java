package com.utp.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Formateador {

    // Define el formato de moneda para Perú (Soles)
    private static final DecimalFormat FORMATO_MONEDA = new DecimalFormat("S/ #,##0.00");
    
    // Define el formato de fecha y hora local
    private static final SimpleDateFormat FORMATO_FECHA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Formatea un valor double como cadena de moneda local (ej: S/ 15.00).
     * @param monto El valor numérico a formatear.
     * @return El monto formateado como String.
     */
    public static String formatearComoMoneda(double monto) {
        return FORMATO_MONEDA.format(monto);
    }

    /**
     * Formatea un objeto Date como cadena de fecha y hora (ej: 25/10/2025 20:30:00).
     * @param fecha El objeto Date a formatear.
     * @return La fecha formateada como String.
     */
    public static String formatearFechaHora(Date fecha) {
        if (fecha == null) {
            return "N/A";
        }
        return FORMATO_FECHA_HORA.format(fecha);
    }

    private Formateador() {}
}

//
//Ejemplo de uso en el DTO 
//
//package com.polleria.modelo.dto;
//
//import com.polleria.util.Formatador;
//import java.util.Date;
//
//public class PedidoDTO {
//    
//    private double total;
//    private Date fecha;
//    // ... otros campos (id, clienteId)
//
//    // GETTERS Y SETTERS ESTÁNDAR
//    public double getTotal() { return total; }
//    public Date getFecha() { return fecha; }
//    // ... (setters)
//
//    // ===================================================
//    // GETTERS DE PRESENTACIÓN (USAN LA CLASE UTILITY)
//    // ===================================================
//
//    /** * Método Getter que usa Formatador.java para formatear el Total.
//     * Este es el que usará el EL en la Vista: ${item.totalFormateado}
//     */
//    public String getTotalFormateado() {
//        return Formatador.formatearComoMoneda(this.total);
//    }
//
//    /**
//     * Método Getter que usa Formatador.java para formatear la fecha.
//     * Este es el que usará el EL en la Vista: ${item.fechaFormateada}
//     */
//    public String getFechaFormateada() {
//        return Formatador.formatearFechaHora(this.fecha);
//    }
//}






//uuu
//Ejemplo en el JSP usando el DTO primero
//
//<c:forEach var="item" items="${listaPedidos}">
//    <tr>
//        <td>${item.totalFormateado}</td> 
//
//        <td>${item.fechaFormateada}</td> 
//    </tr>
//</c:forEach>

