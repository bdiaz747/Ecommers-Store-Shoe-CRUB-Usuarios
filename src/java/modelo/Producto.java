package modelo;

// Clase que representa la tabla "producto" en la base de datos
public class Producto {

    // ================================
    // 🔹 ATRIBUTOS (campos de la BD)
    // ================================

    private int idProducto;              // id_producto (PK)
    private int idCategoria;             // FK hacia categoria
    private String nombreProducto;       // nombre del producto
    private String marcaProducto;        // marca
    private String descripcionProducto;  // descripción
    private double precioProducto;       // precio
    private int stockProducto;           // stock disponible

    // 🔹 NUEVO CAMPO (imagen)
    private String imagenProducto;       // ruta de la imagen (ej: img/productos/zapato.jpg)

    // ================================
    // 🔹 CONSTRUCTOR VACÍO
    // ================================
    public Producto() {
    }

    // ================================
    // 🔹 CONSTRUCTOR CON PARÁMETROS
    // ================================
    public Producto(int idProducto, int idCategoria, String nombreProducto,
                    String marcaProducto, String descripcionProducto,
                    double precioProducto, int stockProducto,
                    String imagenProducto) {

        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.imagenProducto = imagenProducto; // asignamos imagen
    }

    // ================================
    // 🔹 GETTERS Y SETTERS
    // ================================

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    // ================================
    // 🔹 GET Y SET DE IMAGEN
    // ================================

    // Obtener la ruta de la imagen
    public String getImagenProducto() {
        return imagenProducto;
    }

    // Asignar la ruta de la imagen
    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }
}