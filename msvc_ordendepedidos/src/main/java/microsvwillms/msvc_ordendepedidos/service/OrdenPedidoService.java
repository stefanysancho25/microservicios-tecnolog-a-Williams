package microsvwillms.msvc_ordendepedidos.service;

import java.util.List;

import microsvwillms.msvc_ordendepedidos.model.entity.OrdenPedido;


public interface OrdenPedidoService {

    OrdenPedido crearOrden(OrdenPedido orden);

    OrdenPedido actualizarOrden(Long id, OrdenPedido orden);

    OrdenPedido obtenerPorId(Long id);

    List<OrdenPedido> listarTodas();

    void eliminarOrden(Long id);

    OrdenPedido crearOrden(OrdenPedido orden, Long productoId, Integer cantidad);
}
