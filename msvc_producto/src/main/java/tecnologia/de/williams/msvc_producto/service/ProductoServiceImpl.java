package tecnologia.de.williams.msvc_producto.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tecnologia.de.williams.msvc_producto.controller.ImportResultDTO;
import tecnologia.de.williams.msvc_producto.model.entity.Producto;
import tecnologia.de.williams.msvc_producto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

   @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ImportResultDTO importProductsFromFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("El archivo seleccionado está vacío");
        }

        int total = 0;
        int importados = 0;
        int duplicados = 0;
        int errores = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                total++;
                String separator = line.contains(";") ? ";" : ",";
                 String[] data = line.split(separator);

                // Validación de columnas (Nombre, Código, Precio, Stock)
                if (data.length < 4) {
                    errores++;
                    continue;
                }

                try {
                    String nombre = data[0].trim();
                    String codigo = data[1].trim();

                   // Reemplaza coma decimal por punto para que parseDouble no falle
            String precioStr = data[2].trim().replace(",", ".");
            String stockStr = data[3].trim().replace(",", ".");
            
            double precio = Double.parseDouble(precioStr);
            int stock = (int) Double.parseDouble(stockStr); // Maneja si viene como 10.0

                    // 1. Validar si el precio o stock son negativos
                    if (precio < 0 || stock < 0) {
                        errores++;
                        continue;
                    }

                    // 2. Validar duplicado por código
                    if (productoRepository.existsByCodigo(codigo)) {
                        duplicados++;
                        continue;
                    }

                    // 3. Crear y configurar producto
                    Producto producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setCodigo(codigo);
                    producto.setPrecio(precio);
                    producto.setStockCantidad(stock);
                    
                    // Valores por defecto y cálculos
                    producto.setUbicacion("BODEGA PRINCIPAL");
                    producto.actualizarDisponibilidad(stock);
                    producto.calcularPreciosConIva();

                    productoRepository.save(producto);
                    importados++;

                } catch (NumberFormatException e) {
                    errores++; // Error si el precio o stock no son números
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error crítico al procesar el archivo: " + e.getMessage());
        }

        return new ImportResultDTO(total, importados, duplicados, errores);
    }

    /* LISTAR */
    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    /* BUSCAR POR ID */
    @Override
    public Optional<Producto> buscarPorId(String id) {
        return productoRepository.findById(id);
    }

    /* GUARDAR / ACTUALIZAR */
    @Override
    public Producto guardar(Producto producto) {
        producto.actualizarDisponibilidad(null);
        producto.calcularPreciosConIva();
        return productoRepository.save(producto);
    }

    /* ELIMINAR */
    @Override
    public void eliminarPorId(String id) {
        productoRepository.deleteById(id);
    }

    /*PRODUCTOS DISPONIBLES */
    @Override
    public List<Producto> productosDisponibles() {
        return productoRepository.findProductosDisponibles();
    }

    /* DESCONTAR STOCK */
    @Override
    public void descontarStock(String id, Integer cantidad) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (cantidad <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        if (producto.getStockCantidad() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        producto.setStockCantidad(producto.getStockCantidad() - cantidad);
        producto.actualizarDisponibilidad(5);

        productoRepository.save(producto);
    }

    @Override
    public String importarProductosDesdeArchivo(MultipartFile file) {
    
        throw new UnsupportedOperationException("Unimplemented method 'importarProductosDesdeArchivo'");
    }
}