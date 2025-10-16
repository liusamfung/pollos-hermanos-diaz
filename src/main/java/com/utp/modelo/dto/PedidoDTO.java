//package com.utp.modelo.dto;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class PedidoDTO {
//    private Long idPedido;
//    private LocalDateTime fechaHora;
//    private String estado; 
//    private List<DetallePedidoDTO> detalles; // Contenedor de los detalles
//
//    public PedidoDTO() {}
//    public PedidoDTO(List<DetallePedidoDTO> detalles) {
//        this.detalles = detalles;
//        this.fechaHora = LocalDateTime.now();
//        this.estado = "PENDIENTE";
//    }
//
//    // --- Getters y Setters ---
//    public Long getIdPedido() { return idPedido; }
//    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }
//    public LocalDateTime getFechaHora() { return fechaHora; }
//    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
//    public String getEstado() { return estado; }
//    public void setEstado(String estado) { this.estado = estado; }
//    public List<DetallePedidoDTO> getDetalles() { return detalles; }
//    public void setDetalles(List<DetallePedidoDTO> detalles) { this.detalles = detalles; }
//}