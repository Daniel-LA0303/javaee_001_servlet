<%@page contentType="text/html" pageEncoding="UTF-8" import="org.example.models.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="carrito" value="${sessionScope.carrito}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
    <h1>Carro de compras</h1>

    <c:choose>
        <c:when test="${empty carrito or empty carrito.items}">
            <p>No hay productos en el carro</p>
        </c:when>
        <c:otherwise>
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
                    <c:forEach var="item" items="${carrito.items}">
                        <tr>
                            <td><c:out value="${item.producto.id}"/></td>
                            <td><c:out value="${item.producto.nombre}"/></td>
                            <td><c:out value="${item.producto.precio}"/></td>
                            <td><c:out value="${item.cantidad}"/></td>
                            <td><c:out value="${item.importe}"/></td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="4" style="text-align: right">Total:</td>
                        <td><c:out value="${carrito.total}"/></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3">Total</td>
                        <td><c:out value="${carrito.total}"/></td>
                    </tr>
                </tfoot>
            </table>
        </c:otherwise>
    </c:choose>

    <p><a href="<c:out value="${pageContext.request.contextPath}/productos"/>">Seguir comprando</a></p>
    <p><a href="<c:out value="${pageContext.request.contextPath}/index.html"/>">Volver</a></p>
</body>
</html>
