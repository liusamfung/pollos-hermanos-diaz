// Archivo: com/utp/modelo/dto/PedidoDTO.java
package com.utp.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO que mapea la tabla pedido.
 * Contiene la información de cabecera y una lista de sus detalles.
 */
public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; // ID del pedido (PRI)
    private Long clienteId; // FK a Cliente (cliente_id)
    private LocalDateTime fechaPedido; // (fecha_pedido)
    private BigDecimal total; // (total decimal(7,2))
    private String estado; // (estado)
    
    // Relación de Uno a Muchos: Contiene los items del pedido
    private List<DetallePedidoDTO> detalles;

    // Constructor vacío
    public PedidoDTO() {
        this.estado = "PENDIENTE"; // Valor por defecto
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<DetallePedidoDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedidoDTO> detalles) { this.detalles = detalles; }
}