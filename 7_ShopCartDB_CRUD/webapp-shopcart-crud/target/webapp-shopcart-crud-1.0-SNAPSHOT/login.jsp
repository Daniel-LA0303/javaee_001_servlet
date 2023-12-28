<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
        <h1 class="my-4">Iniciar sesión Carrito</h1>

        <form action="/webapp-shopcart-crud/login" method="post">
            <div class="form-group">
                <label for="username">Usuario</label>
                <input type="text" name="username" id="username" class="form-control">
            </div>

            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" name="password" id="password" class="form-control">
            </div>

            <button type="submit" class="btn btn-primary">Ingresar</button>
        </form>
<jsp:include page="layout/footer.jsp"/>