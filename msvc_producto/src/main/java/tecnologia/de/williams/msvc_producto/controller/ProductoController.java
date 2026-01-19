package tecnologia.de.williams.msvc_producto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tecnologia.de.williams.msvc_producto.model.entity.Producto;
import tecnologia.de.williams.msvc_producto.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

   @PostMapping("/import")
    public ResponseEntity<?> importProducts(@RequestParam("file") MultipartFile file) {
    ImportResultDTO result = productoService.importProductsFromFile(file);
    return ResponseEntity.ok(result);
}

    /*LISTAR TODOS LOS PRODUCTOS */
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    /* BUSCAR PRODUCTO POR ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Producto> producto = productoService.buscarPorId(id);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado con id: " + id);
    }

    /* CREAR PRODUCTO */
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    /* ACTUALIZAR PRODUCTO */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable String id,
            @RequestBody Producto producto) {

        Optional<Producto> productoActual = productoService.buscarPorId(id);

        if (productoActual.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede actualizar. Producto no encontrado.");
        }

        producto.setId(id);
        return ResponseEntity.ok(productoService.guardar(producto));
    }
 
      /* DESCONTAR STOCK DEL PRODUCTO */

    @PutMapping("/{id}/descontar-stock/{cantidad}")
     public ResponseEntity<?> descontarStock(
        @PathVariable String id,
        @PathVariable Integer cantidad) {

        
    Producto producto = productoService.buscarPorId(id).orElse(null);

    if (producto == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado");
    }

    if (cantidad <= 0) {
        return ResponseEntity.badRequest()
                .body("La cantidad debe ser mayor a cero");
    }

    if (producto.getStockCantidad() < cantidad) {
        return ResponseEntity.badRequest()
                .body("Stock insuficiente");
    }

    producto.setStockCantidad(producto.getStockCantidad() - cantidad);
    // Actualizar disponibilidad según el nuevo stock
     producto.actualizarDisponibilidad(cantidad);
    productoService.guardar(producto);

    return ResponseEntity.ok("Stock actualizado correctamente");
}


    /*MODIFICAR PRODUCTO */
    public ResponseEntity<?> modificar(
        @PathVariable String id,
        @RequestBody Producto productoNuevo) {

    Producto productoActual = productoService.buscarPorId(id).orElse(null);

    if (productoActual == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto no encontrado para modificar");
    }

    productoNuevo.setId(id);
    productoService.guardar(productoNuevo);

    return ResponseEntity.ok(productoNuevo);
}

    /*PRODUCTOS DISPONIBLES */
    @GetMapping("/disponibles")
    public ResponseEntity<List<Producto>> disponibles() {
        return ResponseEntity.ok(productoService.productosDisponibles());
    }
}
