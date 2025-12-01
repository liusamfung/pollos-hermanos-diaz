// Archivo: com/utp/modelo/dto/ItemCarritoDTO.java
package com.utp.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemCarritoDTO implements Serializable {
    private ProductoDTO producto; 
    private int cantidad;

    public ItemCarritoDTO() {}

    public ItemCarritoDTO(ProductoDTO producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public ProductoDTO getProducto() { return producto; }
    public void setProducto(ProductoDTO producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // Método de Negocio
public BigDecimal getSubtotal() { // <-- ¡Cambiar el tipo de retorno!
    if (producto != null) {
        // Convertir la cantidad (int) a BigDecimal
        BigDecimal cantidadBD = new BigDecimal(this.cantidad);
        
        // Multiplicar y devolver el resultado como BigDecimal
        return this.producto.getPrecio().multiply(cantidadBD);
    }
    // Devolver un BigDecimal.ZERO en lugar de 0.0
    return BigDecimal.ZERO; 
}
}