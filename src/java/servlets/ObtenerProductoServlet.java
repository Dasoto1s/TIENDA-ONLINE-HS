package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ObtenerProductoServlet", urlPatterns = {"/ObtenerProductoServlet"})
public class ObtenerProductoServlet extends HttpServlet {

    private final String usuario = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/abc";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar la respuesta HTTP
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Obtener el ID del producto de los parámetros de la solicitud
        String idProductoStr = request.getParameter("id");
        if (idProductoStr != null) {
            int idProducto = Integer.parseInt(idProductoStr);

            Connection conexion = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);

                // Consulta para obtener los detalles del producto
                pstmt = conexion.prepareStatement("SELECT * FROM PRODUCTO WHERE Id_Producto = ?");
                pstmt.setInt(1, idProducto);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Crear una tabla HTML con los detalles del producto
                    out.println("<table>");
                    out.println("<tr><th>ID</th><th>Nombre</th><th>Descripción</th><th>Precio</th></tr>");
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("Id_Producto") + "</td>");
                    out.println("<td>" + rs.getString("Nombre") + "</td>");
                    out.println("<td>" + rs.getString("Descripcion") + "</td>");
                    out.println("<td>" + rs.getDouble("Precio") + "</td>");
                    out.println("</tr>");
                    out.println("</table>");
                } else {
                    // No se encontró ningún producto con el ID especificado
                    out.println("No se encontró ningún producto con el ID especificado");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                // Mostrar mensaje de error
                out.println("Error al obtener el producto");
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
        } else {
            // No se proporcionó un ID válido
            out.println("ID de producto no válido");
        }
    }
}
