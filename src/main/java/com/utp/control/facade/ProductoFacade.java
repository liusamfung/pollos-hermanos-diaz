package com.utp.control.facade;

import com.utp.modelo.dto.ProductoDTO;
import java.util.List;

// Interfaz (Contrato) que define las operaciones de negocio de la pollería.
// El Servlet solo ve esta interfaz (DIP)
public interface ProductoFacade {

    /**
     * Lógica de negocio para obtener el catálogo visible por el cliente.
     * Puede incluir lógica adicional (filtrar, ordenar, aplicar promociones).
     * @return Lista de productos listos para la venta.
     */
    List<ProductoDTO> getCatalogoParaVenta();
}
