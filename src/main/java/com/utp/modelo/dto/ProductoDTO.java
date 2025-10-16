package com.utp.modelo.dto;

import java.math.BigDecimal;

public class ProductoDTO {

  private int id;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private int stock;
  private boolean esActivo;

  public ProductoDTO() {
  }

  public ProductoDTO(int id, String nombre, String descripcion, BigDecimal precio, int stock, boolean esActivo) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
    this.esActivo = esActivo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public boolean esActivo() {
    return esActivo;
  }

  public void setEsActivo(boolean esActivo) {
    this.esActivo = esActivo;
  }

  @Override
  public String toString() {
    return "Producto{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", precio=" + precio +
        ", stock=" + stock +
        ", esActivo=" + esActivo +
        '}';
  }
}
