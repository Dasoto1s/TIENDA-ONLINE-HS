package Backend;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Producto;
import java.io.InputStream;


public class BackendHSStudio {
    private final String usuario;
    private final String password;
    private final String url;

    public BackendHSStudio(String usuario, String password, String url) {
        this.usuario = usuario;
        this.password = password;
        this.url = url;
    }

    public void agregarProducto(String nombre, String descripcion, InputStream inputStream, float precio, int talla, String color, String genero) throws IOException, SQLException, ClassNotFoundException {
          // Agregar producto a la base de datos
       Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection(url, usuario, password);

        // Agregar producto a la base de datos
        PreparedStatement pstmt = conexion.prepareStatement("INSERT INTO PRODUCTO (NOMBRE, DESCRIPCION, IMAGEN, PRECIO, TALLA, COLOR, GENERO) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, nombre);
        pstmt.setString(2, descripcion);
        pstmt.setBlob(3, inputStream);
        pstmt.setFloat(4, precio);
        pstmt.setInt(5, talla);
        pstmt.setString(6, color);
        pstmt.setString(7, genero);
        pstmt.executeUpdate();
    }

    public List<Producto> obtenerProductos() throws SQLException {
    List<Producto> productos = new ArrayList<>();
    
    try (Connection conexion = DriverManager.getConnection(url, usuario, password);
         PreparedStatement pstmt = conexion.prepareStatement("SELECT * FROM PRODUCTO");
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getString("Id_Producto"));
            producto.setNombre(rs.getString("NOMBRE"));
            producto.setDescripcion(rs.getString("DESCRIPCION"));
            producto.setPrecio(rs.getFloat("PRECIO"));
            producto.setTalla(rs.getInt("TALLA"));
            producto.setColor(rs.getString("COLOR"));
            producto.setGenero(rs.getString("GENERO"));
            // Asegúrate de manejar la obtención de la imagen según cómo la estés almacenando en la base de datos
            // producto.setImagen(rs.getBlob("IMAGEN")); 

            productos.add(producto);
        }
    } catch (SQLException ex) {
        // Manejo de excepciones
        ex.printStackTrace(); // Para depuración, imprime el rastreo de la pila en la consola
        throw ex; // Relanza la excepción para que el servlet pueda manejarla adecuadamente
    }
    
    return productos;
}
        
    public Producto obtenerProductoPorId(int id) throws SQLException {
    Producto producto = null;
    
    try (Connection conexion = DriverManager.getConnection(url, usuario, password);
         PreparedStatement pstmt = conexion.prepareStatement("SELECT * FROM PRODUCTO WHERE Id_Producto = ?");
    ) {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getString("Id_Producto"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setPrecio(rs.getFloat("PRECIO"));
                producto.setTalla(rs.getInt("TALLA"));
                producto.setColor(rs.getString("COLOR"));
                producto.setGenero(rs.getString("GENERO"));
                // Asegúrate de manejar la obtención de la imagen según cómo la estés almacenando en la base de datos
                producto.setImagenBytes(rs.getBytes("IMAGEN"));
            }
        }
    } catch (SQLException ex) {
        // Manejo de excepciones
        ex.printStackTrace(); // Para depuración, imprime el rastreo de la pila en la consola
        throw ex; // Relanza la excepción para que el servlet pueda manejarla adecuadamente
    }
    
    return producto;
}


}
