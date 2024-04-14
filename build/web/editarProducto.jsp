<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Producto</title>
</head>
<body>
  <h1>Editar Producto</h1>
  <div id="detalleProducto">
    <!-- Aquí se mostrarán los detalles del producto -->
  </div>
  <script>
      document.addEventListener('DOMContentLoaded', function() {
  var idProducto = obtenerParametroUrl('id'); // Obtener el ID del producto de la URL
  if (idProducto) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'ObtenerProductoServlet?id=' + idProducto, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // Mostrar los detalles del producto en la página
          document.getElementById('detalleProducto').innerHTML = xhr.responseText;
        } else {
          console.error('Error al obtener los detalles del producto:', xhr.status);
        }
      }
    };
    xhr.send();
  } else {
    console.error('No se proporcionó un ID de producto válido');
  }
});

function obtenerParametroUrl(nombre) {
  var urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(nombre);
}

  </script>
</body>
</html>
