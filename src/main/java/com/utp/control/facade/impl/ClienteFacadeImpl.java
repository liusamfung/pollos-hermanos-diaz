package com.utp.control.facade.impl;

import com.utp.control.facade.ClienteFacade;
import com.utp.modelo.dao.ClienteDAO;
import com.utp.modelo.dto.ClienteDTO;

// Implementación concreta del contrato ClienteFacade
public class ClienteFacadeImpl implements ClienteFacade {

    // Dependencia del DAO: Se inyectará en el constructor.
    private final ClienteDAO clienteDAO;

    // Inyección de Dependencia (Constructor Injection)
    // Recibe el DAO como interfaz
    public ClienteFacadeImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ClienteDTO login(String email, String password) {
        // Lógica de Negocio:
        // 1. Aquí se podría verificar si el email tiene un formato válido antes de llamar a la BD.
        // 2. Aquí se debería encriptar la contrasena.
        // 3. Actualmente, llama al DAO para validar las credenciales directas.
        
        System.out.println("DEBUG: Lógica de negocio ejecutada para login de cliente: " + email);
        return clienteDAO.validarCredenciales(email, password);
    }
}
