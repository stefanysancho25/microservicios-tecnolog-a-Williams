package microsvwillms.msvc_ordendepedidos.dto;

import java.util.List;

public class OrdenPedidoRequestDTO {
    private String codigo;
    private String cliente;
   
    private List<ProductoDTO> productos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

}
