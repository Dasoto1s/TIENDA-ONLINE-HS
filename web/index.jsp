<%@page import="logica.Producto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestión Inventario</title>
  <link rel="stylesheet" href="CSS/index.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Krona+One&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="CSS/inicioAdmin.css">
  <link rel="stylesheet" href="CSS/gestionInventario.css">
</head>
<body>
  <header>
    <nav>
      <div class="buscador">
        <h2 class="Dasboard">Dasboard</h2>
        <div class="navBuscador">
          <input type="text" placeholder="Buscar">
          <button>
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-search" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <path d="M10 10m-7 0a7 7 0 1 0 14 0a7 7 0 1 0 -14 0" />
              <path d="M21 21l-6 -6" />
            </svg>
          </button>
        </div>
      </div>
    </nav>
  </header>

  <div class="menu">
    <div class="perfil">
        <img src="IMAGENES/foto perfil.webp" alt="Perfil del administrador">
      <p>Nombre del Administrador</p>
    </div>
    <ul class="opciones">
   
    
       <li><a href="inicioAdmin.html" id="gestionarInventario">Gestionar Inventario</a></li>
      <li><a href="gestionPerfiles.html" id="gestionarPerfiles">Gestionar Perfiles</a></li>
      <li><a href="gestionarCambiosDevoluciones.html" id="devolucionesCambios">Devoluciones / Cambios</a></li>
      <li><a href="gestionarbannerDestacados.html" id="publicidadDestacados">Banner / Destacados</a></li>
      <li><a href="gestionarPedidos.html" id="pedidos">Pedidos</a></li>
      <li><a href="index.html" id="verTienda" target="_blank">Ver Tienda</a></li>
    </ul>
  </div>

<h1>inventario</h1> 
  

  <div class="container">

    <div class="buscadorDos">
        <input type="text" placeholder="Código">
        <input type="text" placeholder="Nombre">
        <button>Buscar</button>
      </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Prescripción</th>
          <th>Precio</th>
          <th>Imagen</th>
          <th>Género</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Producto 1</td>
          <td>Descripción del Producto 1</td>
          <td>$100</td>
          <td><img src="imagen_producto1.jpg" alt="Producto 1"></td>
          <td>Masculino</td>
          <td>
            <button class="edit-btn">Editar</button>
            <button class="delete-btn">Eliminar</button>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>Producto 2</td>
          <td>Descripción del Producto 2</td>
          <td>$150</td>
          <td><img src="imagen_producto2.jpg" alt="Producto 2"></td>
          <td>Femenino</td>
          <td>
            <button class="edit-btn">Editar</button>
            <button class="delete-btn">Eliminar</button>
          </td>
        </tr>
        <tr>
          <td>3</td>
          <td>Producto 3</td>
          <td>Descripción del Producto 3</td>
          <td>$120</td>
          <td><img src="imagen_producto3.jpg" alt="Producto 3"></td>
          <td>Masculino</td>
          <td>
            <button class="edit-btn">Editar</button>
            <button class="delete-btn">Eliminar</button>
          </td>
          <tr>
            <td>4</td>
            <td>Producto 4</td>
            <td>Descripción del Producto 4</td>
            <td>$120</td>
            <td><img src="imagen_producto3.jpg" alt="Producto 3"></td>
            <td>Masculino</td>
            <td>
              <button class="edit-btn">Editar</button>
              <button class="delete-btn">Eliminar</button>
            </td>

            
        </tr>
   
      </tbody>
    </table>

      


 <div class="agregar-producto">
    <button id="btnAgregarProducto">Agregar Nuevo Producto</button>
  <form action="AgregarProductoServlet" method="POST" enctype="multipart/form-data" id="formularioAgregarProducto" class="hidden">
        <input type="text" placeholder="Nombre" name="nombre" required>
            <input type="text" placeholder="Descripción" name="descripcion" required>
            <input type="file" id="imagenProducto" accept="image/*" name="imagen" required>
            <input type="number" placeholder="Precio" name="precio" step="0.01" required>
            <input type="number" placeholder="Talla" name="talla" required>
            <input type="text" placeholder="Color" name="color" required>
            <input type="text" placeholder="Género" name="genero" required>
        <button type="submit">Guardar</button>
    </form>
</div>
      
      <h1>Ver lista de productos</h1>
    <p>Haz clic en el siguiente botón para ver la lista de productos</p>
    <form action="MostrarProductosServlet" method="GET">
    <button type="submit">Mostrar Productos</button>
</form>

</div>
  
  
  

  

    
<script src="JS/gestionInventario.js"></script>
</body>
</html>