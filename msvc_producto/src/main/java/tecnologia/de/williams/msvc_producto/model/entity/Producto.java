package tecnologia.de.williams.msvc_producto.model.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@Document(collection = "Productos")
public class Producto {

    @Id
    private String id;            
    private String nombre;
    private String codigo;
    private Double precio;    // precio unitario
    private Double precioMayoreo; // Nuevo campo para precio por mayor
    private String marca;
    private String descripcion;
    private String categoria;  
    private Integer stockCantidad;  // stock - cantidad disponible
    private Integer stockMinimo;    // stock - cantidad mínima
    private String ubicacion;     // ubicacion del producto en bodega
    private boolean disponibleVenta;
    
    public Producto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- CÓDIGO ---
     public String getCodigo() { 
        return codigo;
     } 
     public void setCodigo(String codigo) { 
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }
    // --- PRECIO UNITARIO --- 
    public Double getPrecioConIva() {
         if (precio == null)return 0.0;
         return precio * 1.15; 
    }

    public Double getPrecioMayoreo() {
        return precioMayoreo;
    } 
    /* Precio mayoreo con IVA (15%)*/
    public Double getPrecioMayoreoConIva() {
         if (precioMayoreo == null) return 0.0;
        return precioMayoreo * 1.15;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStockCantidad() {
        return stockCantidad;
    }

    public void setStockCantidad(Integer stockCantidad) {
        if (stockCantidad != null && stockCantidad >= 0) {
            this.stockCantidad = stockCantidad;
        }
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        if (stockMinimo != null && stockMinimo >= 0) {
            this.stockMinimo = stockMinimo;
        }
    }

     public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    

    // --- ESTADO DEL PRODUCTO --- 
    public boolean isDisponibleVenta() { 
        return disponibleVenta;
     } 

    public boolean isStockCritico() { 
        return stockCantidad != null && 
        stockMinimo != null && 
        stockCantidad <= stockMinimo;
    }        

    public void actualizarDisponibilidad(Integer stockCantidad) {
          this.disponibleVenta = this.stockCantidad != null && this.stockCantidad > 0;
        }

    public void calcularPreciosConIva() {
    if (precio != null) {
    precio= BigDecimal.valueOf(precio)
    .multiply(BigDecimal.valueOf(1.15 ))
    .doubleValue(); 

      }
    }
    public void setPrecio(Double precio) {
    this.precio = precio;
    }
}
