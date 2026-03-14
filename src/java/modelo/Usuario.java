package modelo; // Indica que la clase pertenece al paquete modelo

// Clase que representa la tabla usuarios de la base de datos
public class Usuario {

    // Atributo que representa el campo id de la tabla
    private int id;

    // Atributo que representa el nombre del usuario
    private String nombre;

    // Atributo que representa el correo del usuario
    private String correo;

    // Atributo que representa la clave del usuario
    private String clave;

    // Constructor vacío
    public Usuario() {
    }

    // =========================
    // MÉTODOS GET Y SET
    // =========================

    // Devuelve el valor del id
    public int getId() {
        return id;
    }

    // Asigna un valor al id
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Asigna el nombre del usuario
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el correo del usuario
    public String getCorreo() {
        return correo;
    }

    // Asigna el correo del usuario
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Devuelve la clave del usuario
    public String getClave() {
        return clave;
    }

    // Asigna la clave del usuario
    public void setClave(String clave) {
        this.clave = clave;
    }
}