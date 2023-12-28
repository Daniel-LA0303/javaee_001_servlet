<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="layout/header.jsp"/>

        <h1 class="mt-5">Listado de productos</h1>

        <c:if test="${username.present}">
            <div class="alert alert-success">Hola, <c:out value="${username.get()}"/></div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/producto/form">Agregar producto</a>
        </c:if>

        <table class="table table-hover table-striped mt-3">
            <tr>
                <td>Id</td>
                <td>Nombre</td>
                <td>Tipo</td>
                <c:if test="${username.present}">
                    <td>Precio</td>
                    <td>Agregar</td>
                    <td>Editar</td>
                    <td>Eliminar</td>
                </c:if>
            </tr>

            <c:forEach var="p" items="${productos}">
                <tr>
                    <td><c:out value="${p.id}"/></td>
                    <td><c:out value="${p.nombre}"/></td>
                    <td><c:out value="${p.categoria.nombre}"/></td>
                    <c:if test="${username.present}">
                        <td><c:out value="${p.precio}"/></td>
                        <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/agregar-carro?id=<c:out value="${p.id}"/>">Agregar al carrito</a></td>
                        <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/producto/form?id=<c:out value="${p.id}"/>">Editar</a></td>
                        <td>
                            <a class="btn btn-sm btn-danger" onclick="return confirm('¿Estás seguro de eliminar el producto?');"
                            href="${pageContext.request.contextPath}/producto/eliminar?id=<c:out value="${p.id}"/>">Eliminar</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <p>${applicationScope.message}</p>
        <p>${requestScope.message}</p>

<jsp:include page="layout/footer.jsp"/>
