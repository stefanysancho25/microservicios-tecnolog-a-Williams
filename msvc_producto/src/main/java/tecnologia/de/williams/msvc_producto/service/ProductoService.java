package tecnologia.de.williams.msvc_producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import tecnologia.de.williams.msvc_producto.controller.ImportResultDTO;
import tecnologia.de.williams.msvc_producto.model.entity.Producto;

 // Indica que esta clase es un servicio de Spring
public interface ProductoService {
 String importarProductosDesdeArchivo(MultipartFile file);
 


    List<Producto> listarTodos();

    Optional<Producto> buscarPorId(String id);

    Producto guardar(Producto producto);

    void eliminarPorId(String id);

    List<Producto> productosDisponibles();

    void descontarStock(String id, Integer cantidad);
    
   ImportResultDTO importProductsFromFile(MultipartFile file);
   
}