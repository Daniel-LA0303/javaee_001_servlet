<%@page contentType="text/html" pageEncoding="UTF-8" import="org.example.models.*"%>

<%
    Carrito carrito = (Carrito) session.getAttribute("carrito");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
    <h1>Carro de compras</h1>
    <% if (carrito == null || carrito.getItems().isEmpty()) { %>
        <p>No hay productos en el carro</p>
    <% }else{ %>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Subtotal</th>
                </tr>
            </thead>
            <tbody>
                <% for (ItemaCarro item : carrito.getItems()) { %>
                    <tr>
                        <td><%= item.getProducto().getId() %></td>
                        <td><%= item.getProducto().getNombre() %></td>
                        <td><%= item.getProducto().getPrecio() %></td>
                        <td><%= item.getCantidad() %></td>
                        <td><%= item.getImporte() %></td>
                    </tr>
                <% } %>

                <tr>
                    <td colspan="4" style="text-align: right">Total:</td>
                    <td><%= carrito.getTotal() %></td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3">Total</td>
                    <td><%= carrito.getTotal() %></td>
                </tr>
            </tfoot>
        </table>
    <% } %>
    <p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Seguir comprando</a></p>
</body>
</html>