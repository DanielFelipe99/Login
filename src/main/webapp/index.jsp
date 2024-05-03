<%-- 
    Document   : index
    Created on : 29/04/2024, 9:18:12 p. m.
    Author     : danie
--%>

<%@page import="config.conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/loginCss.css">
        <title>Login</title>
    </head>
    <body>
        <div>
            <%
              
              conexion con=new conexion();
              Connection conexion = con.getConexion();
             %>
        </div>
        <section>
            <form action="SvVerificarUsuario" method="post"> 
                <h1>Inicio de Sesion</h1>
                <div class="inputbox">
                    <ion-icon name="mail-outline"></ion-icon>
                    <input type="text" name="nombreCompleto" required>
                    <label for="nombreCompleto">Usuario</label>
                </div>
                <div class="inputbox">
                    <ion-icon name="lock-closed-outline"></ion-icon>
                    <input type="password" name="contrasenia" required>
                    <label for="contrasenia">Contraseña</label>
                </div>
                <button>Ingresar</button>
                <a href="#" class="registrar" data-toggle="modal" data-target="#registroModal">Registrarse</a>
            </form>

        </section>

        <!-- MODAL PARA REGISTRRARSE  -->
        <div class="modal fade" id="registroModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Registro</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Formulario de registro -->
                        <form  action="SvCrearUsuario" method="post">
                            <!-- Campos de registro -->
                            <div class="form-group">
                                <label for="nombreCompleto">Nombre Completo</label>
                                <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" required>
                            </div>
                            <div class="form-group">
                                <label for="numeroTelefono">Número de Teléfono</label>
                                <input type="tel" class="form-control" id="numeroTelefono" name="numeroTelefono" required>
                            </div>
                            <div class="form-group">
                                <label for="cedula">Cédula</label>
                                <input type="text" class="form-control" id="cedula" name="cedula" required>
                            </div>
                            <div class="form-group">
                                <label for="correo">Correo Electrónico</label>
                                <input type="email" class="form-control" id="correo" name="correo" required>
                            </div>
                            <div class="form-group">
                                <label for="contrasenia">Contraseña</label>
                                <input type="password" class="form-control" id="contrasenia" name="contrasenia" required>
                            </div>
                            <!-- Otros campos de registro específicos para usuario o administrador -->
                           
                            <!-- Agregamos un campo oculto para indicar que se está realizando un registro -->
                            <button type="submit" class="btn btn-primary">Registrarse</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>

                    </div>
                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

       
    </body>
</html>


