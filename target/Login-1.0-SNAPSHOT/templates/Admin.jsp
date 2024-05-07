<%-- 
    Document   : User
    Created on : 30/04/2024, 3:30:23 p. m.
    Author     : jonathan
--%>

<%@page import="java.util.List"%>
<%@page import="Mundo.PQRS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Agregar enlaces a los archivos CSS de Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

            .navbar-nav.ml-auto .nav-link.dropdown-toggle {
                white-space: nowrap; /* Evita que el texto se divida en varias líneas */
            }

            .dropdown-menu {
                min-width: auto; /* Ajusta el ancho automáticamente al contenido */
                border: none; /* Elimina el borde del menú */
                box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15); /* Agrega una sombra */
            }

            .dropdown-menu .dropdown-item {
                padding: 0.5rem 1rem; /* Ajusta el espaciado de los elementos del menú */
            }

            .dropdown-menu .dropdown-item:hover {
                background-color: #f8f9fa; /* Cambia el color de fondo al pasar el cursor */
            }

            .dropdown-menu .dropdown-item:focus {
                background-color: #f8f9fa; /* Cambia el color de fondo al enfocar */
                color: #212529; /* Cambia el color del texto al enfocar */
            }

            .dropdown-menu .dropdown-item.active {
                background-color: #007bff; /* Cambia el color de fondo del elemento activo */
                color: #fff; /* Cambia el color del texto del elemento activo */
            }

            .dropdown-menu .dropdown-item:not(:last-child) {
                border-bottom: 1px solid #dee2e6; /* Agrega un borde inferior entre elementos */
            }


            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }

            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                background-color: #f8f9fa;
                color: #212529;
                animation: fadeIn 0.5s ease-in-out;
            }

            @keyframes fadeIn {
                0% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }

            header {
                background-color: #343a40;
                color: #fff;
                box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
            }

            main {
                flex: 1;
                padding: 2rem;
            }

            section {
                margin-bottom: 2rem;
                opacity: 0;
                animation: fadeInUp 0.5s ease-in-out forwards;
            }

            @keyframes fadeInUp {
                0% {
                    opacity: 0;
                    transform: translateY(20px);
                }
                100% {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            .form-control {
                background-color: transparent;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
                color: #495057;
                transition: border-color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            }

            .form-control:focus {
                border-color: #80bdff;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                transition: background-color 0.3s ease-in-out, border-color 0.3s ease-in-out;
            }

            .btn-primary:hover {
                background-color: #0069d9;
                border-color: #0062cc;
            }

            .modal-content {
                background-color: #fff;
                border: none;
                border-radius: 0.25rem;
                box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
                animation: fadeInUp 0.5s ease-in-out;
            }

            .modal-header,
            .modal-footer {
                border: none;
            }



            footer {
                background-color: #343a40;
                color: #fff;
                padding: 1rem;
                text-align: center;
                box-shadow: 0 -0.5rem 1rem rgba(0, 0, 0, 0.15);
            }
        </style>

    </head>
    <body>
        <header>
            <!-- Barra de navegación -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <!-- Marca y botón de alternancia -->
                <a class="navbar-brand" href="#">PQRS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Menú de navegación -->
                <div class="collapse navbar-collapse" id="navbarNav">
                    
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item dropdown">
                                <!-- Menú desplegable para el usuario -->
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%
                                        // Obtener el tipo de usuario de la sesión
                                        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

                                        // Verificar el tipo de usuario y mostrar el nombre correspondiente
                                        if (tipoUsuario != null && tipoUsuario.equals("usuario")) {
                                            // Obtener el nombre del usuario
                                            String nombreUsuario = (String) session.getAttribute("nombreCompleto");
                                            out.print(nombreUsuario);
                                        } else if (tipoUsuario != null && tipoUsuario.equals("administrador")) {
                                            // Mostrar el nombre del administrador
                                            String nombreAdmin = (String) session.getAttribute("nombreAdmin");
                                            out.print("Administrador: " + nombreAdmin);
                                        } else {
                                            // En caso de que no se haya iniciado sesión o no se haya establecido el tipo de usuario
                                            out.print("Usuario"); // O cualquier otro valor por defecto
                                        }
                                    %>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <!-- Opciones del menú desplegable -->
                                    <a class="dropdown-item" href="#">Editar información</a>
                                    <a class="dropdown-item" href="../index.jsp">Cerrar sesión</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>



                                    <!-- Contenido de la página del administrador -->
                                    <main>
                                        <section>
                                            <div class="container my-4">
                                                <!-- Sección para buscar y visualizar PQRS -->
                                                <section class="row">
                                                    <%
                                                        // Obtener la lista de PQRS del atributo "pqrss" en el objeto request
                                                        List<PQRS> pqrss = (List<PQRS>) session.getAttribute("pqrss");

                                                        // Verificar si la lista de PQRS no está vacía
                                                        if (pqrss != null && !pqrss.isEmpty()) {
                                                            // Iterar sobre la lista de PQRS
                                                            for (PQRS pqrs : pqrss) {
                                                    %>
                                                    <div class="col-md-4 mb-3">
                                                        <div class="card overflow-auto">
                                                            <div class="card-header d-flex justify-content-between align-items-center">
                                                                <span>PQRS TITULO: <%= pqrs.getTitulo()%></span>
                                                                <div>
                                                                    <button class="btn btn-primary btn-sm actualizar-btn" data-id="<%= pqrs.getIdPqrs()%>"><i class="fas fa-edit"></i> Actualizar</button>
                                                                </div>
                                                            </div>
                                                            <div class="card-body">
                                                                <h5 class="card-title">Descripción: <%= pqrs.getDescripcion()%></h5>

                                                                <%
                                                                    // Mostrar contenido específico dependiendo del tipo de PQRS
                                                                    if (pqrs.getTipo().equals("1")) {
                                                                %>
                                                                <p class="card-text">Tipo: Petición</p>
                                                                <%
                                                                } else if (pqrs.getTipo().equals("2")) {
                                                                %>
                                                                <p class="card-text">Tipo: Queja</p>
                                                                <%
                                                                } else if (pqrs.getTipo().equals("3")) {
                                                                %>
                                                                <p class="card-text">Tipo: Reclamo</p>
                                                                <%
                                                                } else if (pqrs.getTipo().equals("4")) {
                                                                %>
                                                                <p class="card-text">Tipo: Solicitud</p>
                                                                <%
                                                                    }
                                                                %>
                                                                <p class="card-text">Adjuntos: <%= pqrs.getAdjuntos()%></p>
                                                                <p class="card-text">Estado: <%= pqrs.getEstado()%></p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <%
                                                        }
                                                    } else {
                                                    %>
                                                    <div class="col-md-12">
                                                        <div class="alert alert-info" role="alert">
                                                            No hay PQRS disponibles.
                                                        </div>
                                                    </div>
                                                    <%
                                                        }
                                                    %>
                                                </section>
                                            </div>
                                        </section>
                                    </main>


<!-- Pie de página -->
<footer>
    <!-- Contenido del pie de página -->
</footer>


<!-- Agregar enlaces a los archivos JS de Bootstrap y jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script >

    $(document).ready(function () {
        // Mostrar el modal de creación de PQRS
        $('a[data-target="#crearModal"]').click(function (e) {
            e.preventDefault(); // Evitar que el enlace realice una navegación
            $('#crearModal').modal('show');
        });

 
    });

</script>

<script>
    $(document).ready(function () {
        // Capturar el evento de clic en el botón "Actualizar"
        $('.actualizar-btn').click(function () {
            // Obtener el ID de la PQRS seleccionada
            var pqrsId = $(this).data('id');

            // Hacer una petición AJAX para obtener la información de la PQRS
            $.ajax({
                url: '/Login/SvActualizar?id=' + pqrsId, // URL del servlet para obtener la información de la PQRS
                method: 'POST',
                success: function (data) {

                    // Llenar los campos del formulario con la información obtenida
                    $('#editarIdPQRSInput').val(pqrsId);
                    $('#editarTituloInput').val(data.titulo);
                    $('#editarDescripcionInput').val(data.descripcion);
                    $('#editarAdjuntoInput').val(data.adjunto);
                    $('#editarEstadoInput').val(data.estado);
                    
                    $('#editarTipoInput').val(data.tipo_id);
                    $('#editarUsuarioIdInput').val(data.usuario_id);
                    
                    

                    $('#editarModal').modal('show');

                },
                error: function () {
                    alert('Error al obtener la información de la PQRS');
                }
            });
        });
    });
</script>
</body>
</html>
