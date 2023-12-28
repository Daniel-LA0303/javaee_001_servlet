<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*, java.time.format.*, org.example.models.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="categorias" value="${requestScope.categorias}" />
<c:set var="errores" value="${requestScope.errores}" />
<c:set var="producto" value="${requestScope.producto}" />


<jsp:include page="layout/header.jsp"/>
        <h3>${title}</h3>

        <form action="${pageContext.request.contextPath}/producto/form" method="post" class="my-4">
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" name="nombre" id="nombre" class="form-control" placeholder="Nombre" value="${producto.nombre != null ? producto.nombre : ''}">
                
                <c:if test="${errores != null && errores.containsKey('nombre')}">
                    <p class="text-danger">${errores['nombre']}</p>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="precio">Precio</label>
                <input type="number" name="precio" id="precio" class="form-control" placeholder="Precio" value="${producto.precio != 0 ? producto.precio : ''}">
                
                <c:if test="${errores != null && errores.containsKey('precio')}">
                    <p class="text-danger">${errores['precio']}</p>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="sku">Sku</label>
                <input type="text" name="sku" id="sku" class="form-control" placeholder="Sku" value="${producto.sku != null ? producto.sku : ''}">
                
                <c:if test="${errores != null && errores.containsKey('sku')}">
                    <p class="text-danger">${errores['sku']}</p>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="fecha_registro">Fecha Registro</label>
                <input type="date" name="fecha_registro" id="fecha_registro" class="form-control" placeholder="Fecha Registro" value="${producto.fechaRegisto != null ? producto.fechaRegisto.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')): ''}">
                
                <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
                    <p class="text-danger">${errores['fecha_registro']}</p>
                </c:if>
            </div>
            
            <div class="form-group">
                <label for="categoria">Categoria</label>
                <select name="categoria" id="categoria" class="form-control">
                    <option value="">------Seleccionar-------</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.id}" ${categoria.id == producto.categoria.id ? 'selected' : ''}>
                            ${categoria.nombre}
                        </option>
                    </c:forEach>
                </select>
                
                <c:if test="${errores != null && errores.containsKey('categoria')}">
                    <p class="text-danger">${errores['categoria']}</p>
                </c:if>
            </div>
            
            <button type="submit" class="btn btn-primary mt-5">${producto.id != null ? 'Actualizar' : 'Guardar'}</button>
            <input type="hidden" name="id" value="${producto.id}">
        </form>

<jsp:include page="layout/footer.jsp"/>