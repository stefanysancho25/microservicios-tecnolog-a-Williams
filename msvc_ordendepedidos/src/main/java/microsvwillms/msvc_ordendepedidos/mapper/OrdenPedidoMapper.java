package microsvwillms.msvc_ordendepedidos.mapper;

import microsvwillms.msvc_ordendepedidos.dto.OrdenPedidoRequestDTO;
import microsvwillms.msvc_ordendepedidos.dto.OrdenPedidoResponseDTO;
import microsvwillms.msvc_ordendepedidos.model.entity.OrdenPedido;

public class OrdenPedidoMapper {
    public static OrdenPedido toEntity(OrdenPedidoRequestDTO dto) {
        OrdenPedido orden = new OrdenPedido();
        orden.setCodigo(dto.getCodigo());
        orden.setCliente(dto.getCliente());
        //orden.setTotal(0.0);
        orden.setEstado("CREADA");
        return orden;
    }

    public static OrdenPedidoResponseDTO toResponseDTO(OrdenPedido entity) {
        OrdenPedidoResponseDTO dto = new OrdenPedidoResponseDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setCliente(entity.getCliente());
        //dto.setTotal(entity.getTotal());
        dto.setEstado(entity.getEstado());
        dto.setFechaCreacion(entity.getFechaCreacion());
        return dto;
    }
}
