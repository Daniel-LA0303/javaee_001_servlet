<%@page contentType="UTF-8" import="java.util.*, org.example.models.*"%>


<%
    //Obtenemos los atributos de la request
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    //obteniendo el mensaje de la sesion
    String message = (String) request.getAttribute("message");
    String messageGlobal = (String) getServletContext().getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado</title>
</head>
<body>
  <h1>Listado de productos</h1>
    <% if(username.isPresent()){%>
    <div>Hola, <%=username.get()%> bienvenido</div>
    <% } %>
    <table>
        <tr>
          <td>Id</td>
          <td>Nombre</td>
          <td>Tipo</td>
          <% if(username.isPresent()){%>
          <td>Precio</td>
          <td>Agregar</td>
          <% } %>
        </tr>
        <% for (Producto p : productos) { %>
        <tr>
          <td><%=p.getId()  %></td>
          <td><%=p.getNombre()  %></td>
          <td><%=p.getTipo()  %></td>
          <% if(username.isPresent()){%>
          <td><%=p.getPrecio()  %></td>
          <td><a href="<%=request.getContextPath()%>/agregar-carro?id=<%=p.getId()%>">Agregar al carrito</a></td>
          <% } %>
        </tr>
        <% } %>
    </table>

    <p><%=message%></p>
    <p><%=messageGlobal%></p>


</body>
</html>