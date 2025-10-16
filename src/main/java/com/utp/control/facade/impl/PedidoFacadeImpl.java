//package com.utp.control.facade.impl;
//
//import com.utp.control.facade.PedidoFacade;
//import com.utp.modelo.dao.PedidoDAO; // Dependencia de la capa de persistencia
//import com.utp.modelo.dto.PedidoDTO;
//import com.utp.modelo.dto.ProductoDTO;
//import com.utp.modelo.dto.DetallePedidoDTO;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PedidoFacadeImpl implements PedidoFacade {
//
//    private final PedidoDAO pedidoDAO;
//
//    // Inyección de la dependencia DAO
//    public PedidoFacadeImpl(PedidoDAO pedidoDAO) {
//        this.pedidoDAO = pedidoDAO;
//    }
//
//@Override
//public void procesarNuevoPedido(List<ProductoDTO> seleccionados) {
//    // 1. Mapeo de ProductoDTO a DetallePedidoDTO
//    List<DetallePedidoDTO> detalles = seleccionados.stream()
//        .map(p -> new DetallePedidoDTO(
//            (long) p.getId(), 
//            p.getNombre(), 
//            1,
//            p.getPrecio().doubleValue() 
//        ))
//        .collect(Collectors.toList()); 
//    PedidoDTO nuevoPedido = new PedidoDTO(detalles);
//    
//    // Llama al DAO para guardar el encabezado y los detalles dentro de una transacción.
//    // El DAO actualiza 'nuevoPedido' con el ID generado, o retorna null si falla.
//    PedidoDTO pedidoPersistido = pedidoDAO.crearPedido(nuevoPedido); 
//    
//    if (pedidoPersistido != null && pedidoPersistido.getIdPedido() != null) {
//        System.out.println("DEBUG: Pedido N°" + pedidoPersistido.getIdPedido() + " creado y marcado como PENDIENTE.");
//    } else {
//        System.err.println("ERROR: La transacción de creación del pedido falló. Se ejecutó ROLLBACK.");
//    }
//}
//    @Override
//    public List<PedidoDTO> obtenerPedidosPendientes() {
//        return pedidoDAO.obtenerPedidosPorEstado("PENDIENTE");
//    }
//
//    @Override
//    public void marcarComoDespachado(long idPedido) {
//        pedidoDAO.actualizarEstadoPedido(idPedido, "DESPACHADO");
//        System.out.println("DEBUG: Pedido N°" + idPedido + " despachado con éxito.");
//    }
//}