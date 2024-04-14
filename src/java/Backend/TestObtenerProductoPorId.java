package Backend;


import Backend.BackendHSStudio;
import logica.Producto;
import java.sql.SQLException;

public class TestObtenerProductoPorId {
    public static void main(String[] args) {
        // Crea una instancia de BackendHSStudio con las credenciales y URL de la base de datos
        BackendHSStudio backend = new BackendHSStudio("root", "", "jdbc:mysql://localhost:3306/abc");

        try {
            // Obtén un producto por su ID (reemplaza 1 con el ID del producto que deseas obtener)
            int idProducto = 31;
            Producto producto = backend.obtenerProductoPorId(idProducto);

            // Verifica si se obtuvo un producto
            if (producto != null) {
                // Imprime las propiedades del producto
                System.out.println("ID: " + producto.getId());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Descripción: " + producto.getDescripcion());
                System.out.println("Precio: " + producto.getPrecio());
                System.out.println("Talla: " + producto.getTalla());
                System.out.println("Color: " + producto.getColor());
                System.out.println("Género: " + producto.getGenero());
                // Asegúrate de manejar la obtención de la imagen según cómo la estés almacenando en la base de datos
                // System.out.println("Imagen: " + producto.getImagen());
            } else {
                System.out.println("No se encontró ningún producto con el ID " + idProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
