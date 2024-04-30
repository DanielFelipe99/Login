<%-- 
    Document   : index
    Created on : 29/04/2024, 9:18:12 p. m.
    Author     : danie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/loginCss.css">
        <title>Login</title>
    </head>
    <body>
        <section>
            <form>
                <h1>Inicio de Sesion</h1>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="text" required>
                    <label for="">Usuario</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" required>
                    <label for="">Contraseña</label>
                </div>
                <button>Ingresar</button>
            </form>
            
        </section>
    </body>
</html>


