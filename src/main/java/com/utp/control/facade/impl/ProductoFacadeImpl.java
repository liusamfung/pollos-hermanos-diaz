package com.utp.control.facade.impl;

import com.utp.modelo.dao.ProductoDAO;
import com.utp.control.facade.ProductoFacade;
import com.utp.modelo.dto.ProductoDTO;

import java.util.List;

// Implementación concreta del contrato ProductoFacade
public class ProductoFacadeImpl implements ProductoFacade {

    // Dependencia del DAO: Se inyectará en el constructor.
    private final ProductoDAO productoDAO;

    // Inyección de Dependencia (Constructor Injection)
    // Recibe el DAO como interfaz
    public ProductoFacadeImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<ProductoDTO> getCatalogoParaVenta() {
        // Lógica de Negocio:
        // 1. Aquí se podría verificar stock mínimo.
        // 2. Aquí se podría aplicar una lógica de ordenamiento especial.
        // 3. Actualmente, solo llama al DAO para obtener los productos activos.
        
        System.out.println("DEBUG: Lógica de negocio ejecutada para listar catálogo.");
        return productoDAO.obtenerTodosActivos();
    }
}
