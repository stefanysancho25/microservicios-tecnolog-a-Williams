package microsvwillms.msvc_ordendepedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import microsvwillms.msvc_ordendepedidos.dto.ProductoDTO;

@FeignClient(name = "msvc-producto" )
public interface ProductoClientFeign {

    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable("id") Long productoId);

    @PutMapping("/api/productos/{id}/descontar-stock/{cantidad}")
    void descontarStock(
            @PathVariable("id") Long productoId,
            @PathVariable("cantidad")  Integer cantidad
    );
}
