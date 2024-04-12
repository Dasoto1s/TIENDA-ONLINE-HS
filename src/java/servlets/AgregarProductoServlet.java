package servlets;
import Backend.BackendHSStudio;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AgregarProductoServlet", urlPatterns = {"/AgregarProductoServlet"})
@MultipartConfig // Necesario para manejar solicitudes de formulario multipartes (archivos)
public class AgregarProductoServlet extends HttpServlet {

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Part imagenPart = request.getPart("imagen");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int talla = Integer.parseInt(request.getParameter("talla"));
        String color = request.getParameter("color");
        String genero = request.getParameter("genero");

        // Crear instancia de BackendHSStudio
        BackendHSStudio backend = new BackendHSStudio("root", "", "jdbc:mysql://localhost:3306/abc");

        // Llamar al método en la clase BackendHSStudio para agregar el producto
        backend.agregarProducto(nombre, descripcion, imagenPart.getInputStream(), precio, talla, color, genero);

        // Mostrar mensaje de éxito
        response.getWriter().println("<h1>Producto agregado correctamente</h1>");

    } catch (NumberFormatException | IOException | SQLException ex) {
        Logger.getLogger(AgregarProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        // Mostrar mensaje de error
        response.getWriter().println("<h1>Error al agregar el producto</h1>");
        response.getWriter().println("<p>" + ex.getMessage() + "</p>");
    }
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}