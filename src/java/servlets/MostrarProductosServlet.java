package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Producto;

@WebServlet(name = "MostrarProductosServlet", urlPatterns = {"/MostrarProductosServlet"})
public class MostrarProductosServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);

            // Consulta para obtener todos los productos
            pstmt = conexion.prepareStatement("SELECT * FROM PRODUCTO");
            rs = pstmt.executeQuery();

            // Crear lista para almacenar productos
            List<Producto> productos = new ArrayList<>();

            // Iterar sobre los resultados y crear objetos Producto
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getString("Id_Producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setTalla(rs.getInt("talla"));
                producto.setColor(rs.getString("color"));
                producto.setGenero(rs.getString("genero"));
                // Asegúrate de manejar la obtención de la imagen según cómo la estés almacenando en la base de datos
                // producto.setImagen(rs.getBlob("IMAGEN"));
                

                productos.add(producto);
            }

            // Verificar si hay productos disponibles
            if (!productos.isEmpty()) {
                // Setear los productos en el atributo de solicitud para enviarlos a la página JSP
                request.setAttribute("productos", productos);

                // Redirigir a la página JSP para mostrar los productos
                request.getRequestDispatcher("mostrarProductos.jsp").forward(request, response);
            } else {
                // Mostrar un mensaje si no hay productos disponibles
                response.getWriter().println("<h1>No hay productos disponibles</h1>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Mostrar mensaje de error
            response.getWriter().println("<h1>Error al obtener los productos</h1>");
            response.getWriter().println("<p>" + ex.getMessage() + "</p>");
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
