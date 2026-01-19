package tecnologia.de.williams.msvc_producto.controller;

public class ImportResultDTO {

    private int totalRegistros;
    private int importados;
    private int duplicados;
    private int errores;

    public ImportResultDTO(int totalRegistros, int importados, int duplicados, int errores) {
        this.totalRegistros = totalRegistros;
        this.importados = importados;
        this.duplicados = duplicados;
        this.errores = errores;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public int getImportados() {
        return importados;
    }

    public void setImportados(int importados) {
        this.importados = importados;
    }

    public int getDuplicados() {
        return duplicados;
    }

    public void setDuplicados(int duplicados) {
        this.duplicados = duplicados;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }
}

