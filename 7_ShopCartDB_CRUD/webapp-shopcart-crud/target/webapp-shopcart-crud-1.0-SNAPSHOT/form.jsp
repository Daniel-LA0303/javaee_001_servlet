<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*, java.time.format.*, org.example.models.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="categorias" value="${requestScope.categorias}" />
<c:set var="errores" value="${requestScope.errores}" />
<c:set var="producto" value="${requestScope.producto}" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Formulario</h1>

    <form action="${pageContext.request.contextPath}/producto/form" method="post">
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" placeholder="Nombre" value="${producto.nombre != null ? producto.nombre : ''}">

        <c:if test="${errores != null && errores.containsKey('nombre')}">
            <p style="color: red">${errores['nombre']}</p>
        </c:if>
        <br>

        <label for="precio">Precio</label>
        <input type="number" name="precio" id="precio" placeholder="Precio" value="${producto.precio != 0 ? producto.precio : ''}">

        <c:if test="${errores != null && errores.containsKey('precio')}">
            <p style="color: red">${errores['precio']}</p>
        </c:if>
        <br>

        <label for="sku">Sku</label>
        <input type="text" name="sku" id="sku" placeholder="Sku" value="${producto.sku != null ? producto.sku : ''}">

        <c:if test="${errores != null && errores.containsKey('sku')}">
            <p style="color: red">${errores['sku']}</p>
        </c:if>
        <br>

        <label for="fecha_registro">Fecha Registro</label>
        <input type="date" name="fecha_registro" id="fecha_registro" placeholder="Fecha Registro" value="${producto.fechaRegisto != null ? producto.fechaRegisto.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">

        <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
            <p style="color: red">${errores['fecha_registro']}</p>
        </c:if>
        <br>

        <label for="categoria">Categoria</label>
        <select name="categoria" id="categoria">
            <option value="">------Seleccionar-------</option>
            <c:forEach var="categoria" items="${categorias}">
                <option value="${categoria.id}" ${categoria.id == producto.categoria.id ? 'selected' : ''}>
                    ${categoria.nombre}
                </option>
            </c:forEach>
        </select>

        <c:if test="${errores != null && errores.containsKey('categoria')}">
            <p style="color: red">${errores['categoria']}</p>
        </c:if>
        <br>
        <input type="submit" value="${producto.id != null ? 'Actualizar' : 'Guardar'}">
        <input type="hidden" name="id" value="${producto.id}">
    </form>
</body>
</html>
