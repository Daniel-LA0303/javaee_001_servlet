<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@page import="java.util.Map" %>
<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");


%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<div class=" col-12 col-sm-6 m-auto mt-5">
    <h3 class="mb-3">Formulario</h3>

    <% if(errores != null && errores.size() > 0){ %>
        <ul class="list-group mb-3">
            <% for(String error : errores.values()){ %>
                <li class="list-group-item list-group-item-danger"><%=error%></li>
            <% } %>
        </ul>
    <% } %>

    <form action="/webapp-form/registro" method="post">

        <div class="mb-3">
            <label for="username" class="form-label">Nombre</label>
            <input type="text" name="username" placeholder="Nombre" id="username" class="form-control">
            <% if(errores != null && errores.containsKey("username")){
                out.println("<p class='text-danger'>" + errores.get("username") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" name="password" placeholder="Contraseña" id="password" class="form-control">
            <% if(errores != null && errores.containsKey("password")){
                out.println("<p class='text-danger'>" + errores.get("password") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="text" name="email" placeholder="Email" id="email" class="form-control">
            <% if(errores != null && errores.containsKey("email")){
                out.println("<p class='text-danger'>" + errores.get("email") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <label for="pais" class="form-label">País</label>
            <select name="pais" id="pais" class="form-select">
                <option value="España">España</option>
                <option value="Francia">Francia</option>
                <option value="Italia">Italia</option>
                <option value="Alemania">Alemania</option>
                <option value="Portugal">Portugal</option>
                <option value="Inglaterra">Inglaterra</option>
            </select>
            <% if(errores != null && errores.containsKey("pais")){
                out.println("<p class='text-danger'>" + errores.get("pais") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <label for="lenguajes" class="form-label">Lenguajes</label>
            <select id="lenguajes" name="lenguajes" multiple class="form-select">
                <option value="Java">Java</option>
                <option value="PHP">PHP</option>
                <option value="Python">Python</option>
                <option value="JavaScript">JavaScript</option>
                <option value="C#">C#</option>
                <option value="C++">C++</option>
                <option value="C">C</option>
                <option value="Ruby">Ruby</option>
                <option value="Go">Go</option>
                <option value="Swift">Swift</option>
            </select>
            <% if(errores != null && errores.containsKey("lenguajes")){
                out.println("<p class='text-danger'>" + errores.get("lenguajes") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <div class="form-check">
                <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input">
                <label class="form-check-label">Administrador</label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="roles" value="ROLE_USER" checked class="form-check-input">
                <label class="form-check-label">Usuario</label>
            </div>
            <div class="form-check">
                <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input">
                <label class="form-check-label">Invitado</label>
            </div>
            <% if(errores != null && errores.containsKey("roles")){
                out.println("<p class='text-danger'>" + errores.get("roles") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <label class="form-label">Género</label>
            <div class="form-check">
                <input type="radio" name="genero" value="Hombre" class="form-check-input">
                <label class="form-check-label">Hombre</label>
            </div>
            <div class="form-check">
                <input type="radio" name="genero" value="Mujer" class="form-check-input">
                <label class="form-check-label">Mujer</label>
            </div>
            <div class="form-check">
                <input type="radio" name="genero" value="Otro" class="form-check-input">
                <label class="form-check-label">Otro</label>
            </div>
            <% if(errores != null && errores.containsKey("genero")){
                out.println("<p class='text-danger'>" + errores.get("genero") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <div class="form-check">
                <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
                <label class="form-check-label" for="habilitar">Habilitar</label>
            </div>
            <% if(errores != null && errores.containsKey("habilitar")){
                out.println("<p class='text-danger'>" + errores.get("habilitar") + "</p>" );
            }
            %>
        </div>

        <div class="mb-3">
            <div>
                <input type="submit" value="Enviar" class="btn btn-primary">
            </div>
        </div>

        <input type="hidden" name="secret" value="1234">
    </form>
</div>

</body>
</html>