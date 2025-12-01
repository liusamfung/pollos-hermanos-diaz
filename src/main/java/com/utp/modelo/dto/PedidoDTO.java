//package com.utp.modelo.dto;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class PedidoDTO {
//    private long id;
//    private String codigo; // ABTNE, AA351, etc.
//    private long clienteId;
//    private LocalDateTime fechaRegistro;
//    private String estado; // PENDIENTE, COMPLETADO, CANCELADO, etc.
//    private double total;
//    
//    // Lista de ítems asociados a este pedido
//    private List<DetallePedidoDTO> detalles; 
//
//    // Constructor para el monitor de cocina (simplificado)
//    public PedidoDTO(String codigo, String estado) {
//        this.codigo = codigo;
//        this.estado = estado;
//    }
//    
//    // Constructor completo (necesario para la base de datos)
//    public PedidoDTO(long id, String codigo, long clienteId, LocalDateTime fechaRegistro, String estado, double total) {
//        this.id = id;
//        this.codigo = codigo;
//        this.clienteId = clienteId;
//        this.fechaRegistro = fechaRegistro;
//        this.estado = estado;
//        this.total = total;
//    }
//    
//    // Getters y Setters
//    public String getCodigo() { return codigo; }
//    public String getEstado() { return estado; }
//    public void setEstado(String estado) { this.estado = estado; }
//    // ... otros getters y setters
//    
//    // Método para el monitor de cocina (obtiene el principal y consideraciones)
//    public String getPrincipal() {
//        if (detalles != null && !detalles.isEmpty()) {
//            // Devuelve el nombre del primer producto del detalle como 'Principal'
//            return detalles.get(0).getNombreProducto();
//        }
//        return "N/A";
//    }
//
//    public String getConsideraciones() {
//        // En un sistema real, las consideraciones serían un campo de PedidoDTO.
//        // Aquí lo simulamos con el primer detalle para el ejemplo visual.
//        return "Ninguna"; 
//    }
//}