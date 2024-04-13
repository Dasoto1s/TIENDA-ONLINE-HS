<%@ page import="java.util.List" %>
<%@ page import="logica.Producto" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mostrar usuarios</title>
</head>
<body>
    
      
      
    
    <h1>Lista de productos</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Imagen</th>
                <th>Talla</th>
                <th>Color</th>
                <th>Género</th> 
            </tr>
        </thead>
        
        
        
        <tbody>
            <% List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            for (Producto producto : productos) { %>
                <tr>
                    <td><%= producto.getId() %></td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= producto.getDescripcion() %></td>
                    <td><%= producto.getPrecio() %></td>
                    <td><img src="<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>"></td>
                    <td><%= producto.getTalla() %></td>
                    <td><%= producto.getColor() %></td>
                    <td><%= producto.getGenero() %></td>

                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
