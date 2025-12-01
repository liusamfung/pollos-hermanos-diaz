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
// Archivo: com/utp/control/facade/PedidoFacade.java
package com.utp.control.facade;

import com.utp.modelo.dto.ItemCarritoDTO;
import com.utp.modelo.dto.PedidoDTO;
import java.util.List;

public interface PedidoFacade {
    /**
     * Procesa la compra a partir de un carrito, crea el PedidoDTO y lo persiste.
     * @param clienteId ID del cliente que realiza la compra.
     * @param carrito Lista de items del carrito.
     * @return El PedidoDTO persistido con su ID.
     */
    PedidoDTO procesarCompra(long clienteId, List<ItemCarritoDTO> carrito);
    
    /**
     * Obtiene una lista de pedidos filtrados por estado.
     * EXPONER EL METODO DEL DAO A LA CAPA DE CONTROL.
     * @param estado El estado del pedido a buscar (ej: "PENDIENTE", "ENVIADO").
     * @return Una lista de PedidoDTO.
     */
    List<PedidoDTO> obtenerPedidosPorEstado(String estado);
    
    /**
     * Actualiza el estado de un pedido específico.
     * @param idPedido El ID del pedido a actualizar.
     * @param nuevoEstado El nuevo estado.
     * @return true si se actualizó, false en caso contrario.
     */
    boolean actualizarEstadoPedido(long idPedido, String nuevoEstado); 
}