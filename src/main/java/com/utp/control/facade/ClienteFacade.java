package com.utp.control.facade;

import com.utp.modelo.dto.ClienteDTO;

// Interfaz (Contrato) que define las operaciones de negocio del Cliente.
// El Servlet solo ve esta interfaz (DIP).
public interface ClienteFacade {

    /**
     * Lógica de negocio para validar el ingreso de un cliente al sistema.
     * * @param email Correo electrónico del cliente.
     * @param password Contraseña en texto plano (se debería encriptar aquí si fuera necesario).
     * @return ClienteDTO con los datos del usuario si las credenciales son correctas, o null si falla.
     */
    ClienteDTO login(String email, String password);
}