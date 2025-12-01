// Archivo: com/utp/control/facade/impl/PedidoFacadeImpl.java (CORREGIDO FINALMENTE)
package com.utp.control.facade.impl;

import com.utp.control.facade.PedidoFacade;
import com.utp.modelo.dao.PedidoDAO;
import com.utp.modelo.dto.PedidoDTO;
import com.utp.modelo.dto.DetallePedidoDTO;
import com.utp.modelo.dto.ItemCarritoDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PedidoFacadeImpl implements PedidoFacade {

    private final PedidoDAO pedidoDAO;

    public PedidoFacadeImpl(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    @Override
    public PedidoDTO procesarCompra(long clienteId, List<ItemCarritoDTO> carrito) {
        
        if (carrito == null || carrito.isEmpty()) {
            return null;
        }
        
        BigDecimal total = BigDecimal.ZERO;
        List<DetallePedidoDTO> detalles = new ArrayList<>();

        for (ItemCarritoDTO item : carrito) {
            
            DetallePedidoDTO detalle = new DetallePedidoDTO();
            
            // CORRECCIÓN 1: Convertir el 'int' (getId()) a 'Long' para setProductoId()
            detalle.setProductoId(Long.valueOf(item.getProducto().getId())); 

            detalle.setCantidad(item.getCantidad());
            
            // CORRECCIÓN 2: Asignar directamente el BigDecimal
            BigDecimal precioUnitario = item.getProducto().getPrecio(); 
            detalle.setPrecioUnitario(precioUnitario);
            
            detalles.add(detalle);
            
            // Cálculo del subtotal
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad()));
            total = total.add(subtotal);
        }

        // 2. Crear el PedidoDTO principal
        PedidoDTO pedido = new PedidoDTO();
        pedido.setClienteId(clienteId);
        pedido.setTotal(total);
        pedido.setEstado("PENDIENTE"); 
        pedido.setDetalles(detalles);

        // 3. Persistir usando el DAO
        return pedidoDAO.crearPedido(pedido);
    }
    
    @Override
    public List<PedidoDTO> obtenerPedidosPorEstado(String estado) {
        return pedidoDAO.obtenerPedidosPorEstado(estado);
    }

    @Override
    public boolean actualizarEstadoPedido(long idPedido, String nuevoEstado) {
        return pedidoDAO.actualizarEstadoPedido(idPedido, nuevoEstado);
    }
}