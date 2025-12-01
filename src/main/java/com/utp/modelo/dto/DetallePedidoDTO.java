// Archivo: com/utp/modelo/dto/DetallePedidoDTO.java
package com.utp.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO que mapea la tabla detalle_pedido.
 * Guarda la información necesaria para la persistencia (IDs, cantidad, precio fijo).
 */
public class DetallePedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; // ID de la fila detalle (PRI)
    private Long pedidoId; // FK a Pedido (pedido_id)
    private Long productoId; // FK a Producto (producto_id)
    private int cantidad;
    private BigDecimal precioUnitario; // Precio al momento de la compra (decimal(6,2))
    private String nombreProducto; 

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    // Constructor vacío
    public DetallePedidoDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    
    /**
     * Calcula el subtotal para esta línea de detalle.
     * @return cantidad * precioUnitario.
     */
    public BigDecimal getSubtotal() {
        if (precioUnitario == null) {
            return BigDecimal.ZERO;
        }
        return this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
    }
}