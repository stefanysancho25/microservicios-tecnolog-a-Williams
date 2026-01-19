package microsvwillms.msvc_ordendepedidos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microsvwillms.msvc_ordendepedidos.dto.OrdenPedidoRequestDTO;
import microsvwillms.msvc_ordendepedidos.dto.OrdenPedidoResponseDTO;
import microsvwillms.msvc_ordendepedidos.mapper.OrdenPedidoMapper;
import microsvwillms.msvc_ordendepedidos.model.entity.OrdenPedido;
import microsvwillms.msvc_ordendepedidos.response.ApiResponse;
import microsvwillms.msvc_ordendepedidos.service.OrdenPedidoService;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenPedidoController {

    private final OrdenPedidoService service;

    public OrdenPedidoController(OrdenPedidoService service) {
        this.service = service;
    }

    // POST /api/ordenes
    @PostMapping
    public ResponseEntity<ApiResponse<OrdenPedidoResponseDTO>> crearOrden(
        @RequestBody OrdenPedidoRequestDTO request) {

    OrdenPedido orden = OrdenPedidoMapper.toEntity(request);
    OrdenPedido creada = service.crearOrden(orden);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true,
                    "Orden creada correctamente",
                    OrdenPedidoMapper.toResponseDTO(creada)));
}

    @GetMapping
    public List<OrdenPedido> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public OrdenPedido obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public OrdenPedido actualizar(@PathVariable Long id, @RequestBody OrdenPedido orden) {
        return service.actualizarOrden(id, orden);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarOrden(id);
    }
}
