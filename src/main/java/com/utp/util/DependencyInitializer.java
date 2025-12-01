package com.utp.util;

import com.utp.control.facade.PedidoFacade;
import com.utp.control.facade.impl.PedidoFacadeImpl;
import com.utp.modelo.dao.PedidoDAO;
import com.utp.modelo.dao.impl.PedidoDAOImplMySQL;

/**
 * Clase de utilidad para inicializar y gestionar las instancias de las capas de la aplicación.
 * Actúa como un "Service Locator" simple para conectar DAOs y Facades sin frameworks externos.
 */
public class DependencyInitializer {
    
    // Instancia única del Facade (Singleton)
    private static final PedidoFacade pedidoFacadeInstance;

    static {
        try {
            // 1. Crear la instancia del DAO
            // Deberás reemplazar la línea de abajo con la inicialización correcta de tu DAO MySQL
            PedidoDAO pedidoDAO = new PedidoDAOImplMySQL(); 
            
            // 2. Crear la instancia del Facade inyectando el DAO
            pedidoFacadeInstance = new PedidoFacadeImpl(pedidoDAO);
            
            System.out.println("DEBUG: PedidoFacade inicializado correctamente en DependencyInitializer.");
            
        } catch (Exception e) {
            System.err.println("ERROR FATAL al inicializar dependencias: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Obtiene la instancia lista para usar del PedidoFacade.
     * @return La instancia de PedidoFacade.
     */
    public static PedidoFacade getPedidoFacade() {
        return pedidoFacadeInstance;
    }
}