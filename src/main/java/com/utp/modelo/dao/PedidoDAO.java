// Archivo: com/utp/modelo/dao/PedidoDAO.java
package com.utp.modelo.dao;

import com.utp.modelo.dto.PedidoDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Pedido.
 */
public interface PedidoDAO {
    
    /**
     * Persiste el encabezado y los detalles de un nuevo pedido en una transacción.
     * @param pedido El objeto PedidoDTO con sus detalles.
     * @return El PedidoDTO con el ID generado, o null si falla la persistencia.
     */
    PedidoDTO crearPedido(PedidoDTO pedido);

    /**
     * Obtiene una lista de pedidos filtrados por estado, incluyendo sus detalles.
     * @param estado El estado del pedido a buscar (ej: "PENDIENTE", "ENVIADO").
     * @return Una lista de PedidoDTO.
     */
    List<PedidoDTO> obtenerPedidosPorEstado(String estado);
    
    /**
     * Actualiza el campo estado de un pedido específico.
     * @param idPedido El ID del pedido a actualizar.
     * @param nuevoEstado El nuevo estado.
     * @return true si se actualizó, false en caso contrario.
     */
    boolean actualizarEstadoPedido(long idPedido, String nuevoEstado);
}