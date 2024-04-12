package Backend;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackendHSStudio {
    private final String usuario;
    private final String password;
    private final String url;

    public BackendHSStudio(String usuario, String password, String url) {
        this.usuario = usuario;
        this.password = password;
        this.url = url;
    }

    public void agregarProducto(String nombre, String descripcion, InputStream inputStream, float precio, int talla, String color, String genero) throws IOException, SQLException {
    Connection conexion = null;
    PreparedStatement pstmt = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(url, usuario, password);

        // Agregar producto a la base de datos
        pstmt = conexion.prepareStatement("INSERT INTO PRODUCTO (NOMBRE, DESCRIPCION, IMAGEN, PRECIO, TALLA, COLOR, GENERO) VALUES (?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, nombre);
        pstmt.setString(2, descripcion);
        pstmt.setBlob(3, inputStream);
        pstmt.setFloat(4, precio);
        pstmt.setInt(5, talla);
        pstmt.setString(6, color);
        pstmt.setString(7, genero);
        pstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(BackendHSStudio.class.getName()).log(Level.SEVERE, "No se pudo cargar el controlador JDBC", ex);
        throw new SQLException("No se pudo cargar el controlador JDBC", ex);
    } catch (SQLException ex) {
        Logger.getLogger(BackendHSStudio.class.getName()).log(Level.SEVERE, "Error al conectar a la base de datos", ex);
        throw ex;
    } finally {
        // Cerrar recursos
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BackendHSStudio.class.getName()).log(Level.SEVERE, "Error al cerrar la conexión", ex);
        
            
            
            }
        }
    }
}