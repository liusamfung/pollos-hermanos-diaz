package com.utp.modelo.dao;

import com.utp.modelo.dto.ClienteDTO;

public interface ClienteDAO {

    ClienteDTO validarCredenciales(String email, String password);
}