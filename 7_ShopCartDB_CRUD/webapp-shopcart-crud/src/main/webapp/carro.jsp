<%@page contentType="text/html" pageEncoding="UTF-8" import="org.example.models.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="carrito" value="${sessionScope.carrito}"/>

<jsp:include page="layout/header.jsp"/>
        <h3>${title}</h3>

        <c:choose>
            <c:when test="${empty carrito or empty carrito.items}">
                <p class="alert alert-info">No hay productos en el carro</p>
            </c:when>
            <c:otherwise>
                <table class="table table-striped table-hover">
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

                </table>
            </c:otherwise>
        </c:choose>

        <div><a class="btn btn-small btn-success my-2" href="<c:out value="${pageContext.request.contextPath}/productos"/>">Seguir comprando</a></div>
        <div><a class="btn btn-small btn-secondary" href="<c:out value="${pageContext.request.contextPath}/index.jsp"/>">Volver</a></div>
<jsp:include page="layout/footer.jsp"/>
