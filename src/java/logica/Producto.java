package logica;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private byte[] imagenBytes; // Cambio en la representación de la imagen
    private float precio;
    private int talla;
    private String color;
    private String genero;

    // Constructor, getters y setters

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagenBytes() { // Cambio en el getter para la imagen
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) { // Cambio en el setter para la imagen
        this.imagenBytes = imagenBytes;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
