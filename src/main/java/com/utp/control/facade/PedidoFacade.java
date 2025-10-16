//package com.utp.control.facade;
//
//import com.utp.modelo.dto.PedidoDTO;
//import com.utp.modelo.dto.ProductoDTO;
//import java.util.List;
//
//public interface PedidoFacade {
//    
//    /**
//     * Procesa la solicitud del cliente. Crea un nuevo Pedido con estado "PENDIENTE".
//     * Simula el pago y crea los detalles del pedido.
//     * @param seleccionados
//     */
//    void procesarNuevoPedido(List<ProductoDTO> seleccionados); 
//    
//    /**
//     * Obtiene todos los pedidos en estado "PENDIENTE" para la vista de cocina.
//     * @return 
//     */
//    List<PedidoDTO> obtenerPedidosPendientes();
//
//    /**
//     * Actualiza el estado de un pedido a "DESPACHADO" y ejecuta cualquier lógica asociada.
//     * @param idPedido
//     */
//    void marcarComoDespachado(long idPedido);
//}