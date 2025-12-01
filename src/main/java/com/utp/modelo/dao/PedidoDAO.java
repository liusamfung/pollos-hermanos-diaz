//package com.utp.modelo.dao;
//
//import com.utp.modelo.dto.PedidoDTO;
//import java.util.List;
//
//public interface PedidoDAO {
//    // 1. Necesario para el monitor de cocina
//    List<PedidoDTO> listarPedidosPendientes(); 
//    
//    // 2. Necesario para el AdminServlet (acciones Listo/Rechazar)
//    void actualizarEstado(String codigoPedido, String nuevoEstado); 
//    
//    // 3. Necesario para guardar un nuevo pedido después del pago
//    long guardarPedido(PedidoDTO pedido);
//    
//    // ... otros métodos (buscarPorId, etc.)
//}