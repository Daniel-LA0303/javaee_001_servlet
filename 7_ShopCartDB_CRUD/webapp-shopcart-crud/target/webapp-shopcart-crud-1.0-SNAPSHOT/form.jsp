<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.time.format.*, org.example.models.*"%>


<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");

    Producto producto = (Producto) request.getAttribute("producto");
    System.out.println(producto.getId());
String fecha = producto.getFechaRegisto() != null?
producto.getFechaRegisto().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): "";


%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Formulario</h1>

    <form action="<%=request.getContextPath()%>/producto/form" method="post">
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" placeholder="Nombre" value="<%=producto.getNombre() != null ? producto.getNombre() : ""%>">

        <% if(errores !=null && errores.containsKey("nombre")){ %>
            <p style="color: red"><%=errores.get("nombre")%></p>
        <% } %>
        <br>

        <label for="precio">Precio</label>
        <input type="number" name="precio" id="precio" placeholder="Precio" value="<%=producto.getPrecio() != 0 ? producto.getPrecio() : ""%>">
        <% if(errores != null && errores.containsKey("precio")) { %>
            <p style="color: red"><%=errores.get("precio")%></p>
        <% } %>
        <br>


        <label for="sku">Sku</label>
        <input type="text" name="sku" id="sku" placeholder="Sku" value="<%=producto.getSku() != null ? producto.getSku() : ""%>">
        <% if(errores !=null && errores.containsKey("sku")){ %>
            <p style="color: red"><%=errores.get("sku")%></p>
        <% } %>
        <br>

        <label for="fecha_registro">Feach Registro</label>
        <input type="date" name="fecha_registro" id="fecha_registro" placeholder="Fecha Registro" value="<%=fecha%>">
        <% if(errores != null && errores.containsKey("fecha_registro")) { %>
            <p style="color: red"><%=errores.get("fecha_registro")%></p>
        <% } %>
        <br>


        <label for="categoria">Categoria</label>
        <select name="categoria" id="categoria">
            <option value="">------Seleccionar-------</option>
            <% for (Categoria categoria : categorias) { %>
                <option value="<%=categoria.getId()%>" <%=categoria.getId().equals(producto.getCategoria().getId()) ? "selected" : ""%>>
                <%=categoria.getNombre()%></option>
            <% } %>
        </select>
        <% if(errores !=null && errores.containsKey("categoria")){ %>
            <p style="color: red"><%=errores.get("categoria")%></p>
        <% } %>


        <input type="submit" value="<%=producto.getId() != null ? "Actualizar" : "Guardar"%>">
        <input type="hidden" name="id" value="<%=producto.getId()%>">
    </form>
</body>
</html>