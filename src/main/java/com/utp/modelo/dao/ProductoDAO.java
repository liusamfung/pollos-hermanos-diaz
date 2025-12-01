package com.utp.modelo.dao;

import com.utp.modelo.dto.ProductoDTO;
import java.util.List;

public interface ProductoDAO {

  List<ProductoDTO> obtenerTodosActivos();
  
  ProductoDTO obtenerPorId(long id) throws Exception;
  
}
