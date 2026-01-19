package microsvwillms.msvc_ordendepedidos.dto;

public class ProductoDTO {
    private String id;
    private String nombre;
    private Double precio;
    private Integer stockCantidad;
    

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public Integer getStockCantidad() {
        return stockCantidad;
    }

    public void setStockCantidad(Integer stockCantidad) {
        this.stockCantidad = stockCantidad;
    }

    }