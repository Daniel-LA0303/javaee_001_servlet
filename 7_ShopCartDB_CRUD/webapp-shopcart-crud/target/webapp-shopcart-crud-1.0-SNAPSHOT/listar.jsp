<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <c:if test="${username.present}">
        <div>Hola, <c:out value="${username.get()}"/></div>
        <a href="${pageContext.request.contextPath}/producto/form">Agregar producto</a>
    </c:if>

    <table>
        <tr>
            <td>Id</td>
            <td>Nombre</td>
            <td>Tipo</td>
            <c:if test="${username.present}">
                <td>Precio</td>
                <td>Agregar</td>
                <td>Editar</td>
            </c:if>
        </tr>

        <c:forEach var="p" items="${productos}">
            <tr>
                <td><c:out value="${p.id}"/></td>
                <td><c:out value="${p.nombre}"/></td>
                <td><c:out value="${p.categoria.nombre}"/></td>
                <c:if test="${username.present}">
                    <td><c:out value="${p.precio}"/></td>
                    <td><a href="${pageContext.request.contextPath}/agregar-carro?id=<c:out value="${p.id}"/>">Agregar al carrito</a></td>
                    <td><a href="${pageContext.request.contextPath}/producto/form?id=<c:out value="${p.id}"/>">Editar</a></td>
                    <td>
                        <a onclick="return confirm('¿Estás seguro de eliminar el producto?');"
                           href="${pageContext.request.contextPath}/producto/eliminar?id=<c:out value="${p.id}"/>">Eliminar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

    <p>${applicationScope.message}</p>
    <p>${requestScope.message}</p>
</body>
</html>
