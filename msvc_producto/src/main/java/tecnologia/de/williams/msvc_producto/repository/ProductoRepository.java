package tecnologia.de.williams.msvc_producto.repository;

import java.util.List;
import java.util.Optional;

 import org.springframework.data.mongodb.repository.MongoRepository;
 import org.springframework.data.mongodb.repository.Query;
 import org.springframework.stereotype.Repository;

 import tecnologia.de.williams.msvc_producto.model.entity.Producto;
 @Repository 
 public interface ProductoRepository extends
  MongoRepository<Producto, String> {  

          /* VALIDAR EXISTENCIA */  
        boolean existsByCodigo(String codigo);   
          /* BUSCAR POR CÓDIGO */  
         Optional<Producto> findByCodigo(String codigo);  
          /* PRODUCTOS DISPONIBLES */  
         @Query("{ 'stockCantidad': { $gt: 0 }, 'disponibleVenta': true }") 
         List<Producto> findProductosDisponibles();  
         /*BUSCAR POR CATEGORÍA */  
        List<Producto> findByCategoriaIgnoreCase(String categoria); 
         /*BUSCAR POR MARCA */   
        List<Producto> findByMarcaIgnoreCase(String marca);   
        /* BUSCAR POR RANGO DE PRECIO*/  
        @Query("{ 'precio': { $gte: ?0, $lte: ?1 } }")  
           List<Producto> findByPrecioBetween(Double min, Double max);  
        /* STOCK BAJO  */ 
         @Query("{ $expr: { $lte: [ '$stockCantidad', '$stockMinimo' ] } }") 
         List<Producto> findProductosConStockBajo();
         }