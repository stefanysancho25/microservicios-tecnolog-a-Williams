package microsvwillms.msvc_ordendepedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import microsvwillms.msvc_ordendepedidos.client.ProductoClientFeign;
import microsvwillms.msvc_ordendepedidos.dto.ProductoDTO;
import microsvwillms.msvc_ordendepedidos.model.entity.OrdenPedido;
import microsvwillms.msvc_ordendepedidos.repository.OrdenPedidoRepository;

@Service
public class OrdenPedidoServiceImpl implements OrdenPedidoService {

    private final OrdenPedidoRepository repository;
    private final ProductoClientFeign productoClient;

    public OrdenPedidoServiceImpl(
        OrdenPedidoRepository repository,
        ProductoClientFeign productoClient
    ) {
        this.repository = repository;
        this.productoClient = productoClient;
    }

    @Override
    public OrdenPedido crearOrden(OrdenPedido orden, Long productoId, Integer cantidad) {

        // 1️⃣ Validar duplicado
        if (repository.existsByCodigo(orden.getCodigo())) {
            throw new RuntimeException("Ya existe la orden con código: " + orden.getCodigo());
        }
       
        // 2️⃣ Consultar producto
        ProductoDTO producto = productoClient.obtenerProductoPorId(productoId);

        if (producto.getStockCantidad() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        // 3️⃣ Descontar stock
        productoClient.descontarStock(productoId, cantidad);

        // 4️⃣ Guardar orden
        orden.setEstado("CONFIRMADA");
        return repository.save(orden);
    }

       @Override
    public OrdenPedido crearOrden(OrdenPedido orden) {
        // Este es el método que llama tu controlador actual
        if (repository.existsByCodigo(orden.getCodigo())) {
            throw new RuntimeException("Ya existe la orden con código: " + orden.getCodigo());
        }
        return repository.save(orden);
    }
        @Override
        public OrdenPedido actualizarOrden(Long id, OrdenPedido orden) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public OrdenPedido obtenerPorId(Long id) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<OrdenPedido> listarTodas() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void eliminarOrden(Long id) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

}