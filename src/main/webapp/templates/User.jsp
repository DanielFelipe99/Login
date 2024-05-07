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
        <title>Usuario</title>
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
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#crearModal">Crear PQRS</a>
                        </li>
                    </ul>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item dropdown">
                                <!-- Menú desplegable para el usuario -->
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%
                                        String nombreCompleto = (String) session.getAttribute("nombreCompleto");
                                    %>
                                    <%= nombreCompleto%>
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



        <main>
            <section>
                <div class="container my-4">
                    <!-- Sección para crear PQRS -->
                    <div class="card mb-4">
                        <div class="card-body">
                            <form id="buscarPQRSForm">
                                <div class="form-group">
                                    <label for="buscarInput">Buscar por título o descripción:</label>
                                    <input type="text" class="form-control" id="buscarInput" placeholder="Ingrese su búsqueda">
                                </div>
                                <button type="submit" class="btn btn-primary">Buscar</button>
                            </form>
                        </div>
                    </div>


                    <!-- Sección para buscar VISUALIZAR -->
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
                                        <button class="btn btn-danger btn-sm me-2 eliminar-btn" data-id="<%= pqrs.getIdPqrs()%>"><i class="fas fa-trash-alt"></i> Eliminar</button>
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


            </section>
        </div>
    </section>
</main>



<!-- Modal para crear PQRS -->
<div class="modal fade" id="crearModal" tabindex="-1" aria-labelledby="crearModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="crearModalLabel">Crear PQRS</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Formulario para crear PQRS -->
                <form action="/Login/SvCrearPQRS" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="tituloInput">Título</label>
                        <input type="text" class="form-control" id="tituloInput" name="titulo" placeholder="Ingrese el título">
                    </div>
                    <div class="form-group">
                        <label for="descripcionInput">Descripción</label>
                        <textarea class="form-control" id="descripcionInput" name="descripcion" rows="3" placeholder="Ingrese la descripción"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="categoria">Categoría</label>
                        <select id="categoria" name="categoria" class="form-control" required>
                            <option value="" disabled selected>Seleccione una categoría</option>
                            <option value="1">Petición</option>
                            <option value="2">Queja</option>
                            <option value="3">Reclamo</option>
                            <option value="4">Solicitud</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="adjuntoInput">Adjunto (PDF)</label>
                        <input type="file" class="form-control-file" id="adjuntoInput" name="adjunto">
                    </div>
                    <!-- Campo oculto para el ID del usuario -->
                    <input type="hidden" id="idUsuarioInput" name="idUsuario" value="<%= request.getAttribute("idUsuario")%>">

                    <button type="submit" class="btn btn-primary">Crear PQRS</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal para editar PQRS -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarModalLabel">Editar PQRS</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Formulario para editar PQRS -->
                <form action="/Login/SvActualizar" method="get" enctype="multipart/form-data">
                    <input type="hidden" id="editarIdPQRSInput" name="idPQRS"> <!-- Campo oculto para almacenar el ID de la PQRS -->

                    <div class="form-group">
                        <label for="editarTituloInput">Título</label>
                        <input type="text" class="form-control" id="editarTituloInput" name="titulo" placeholder="Ingrese el título">
                    </div>
                    <div class="form-group">
                        <label for="editarDescripcionInput">Descripción</label>
                        <textarea class="form-control" id="editarDescripcionInput" name="descripcion" rows="3" placeholder="Ingrese la descripción"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="editarAdjuntoInput">Adjunto</label>
                        <input value="editarAdjuntoInput" type="file" class="form-control-file" id="editarAdjuntoInput" name="adjuntos">
                    </div>
                    <div class="form-group">
                        <label for="editarEstadoInput">Estado</label>
                        <input value="editarEstadoInput" type="text" class="form-control" id="editarEstadoInput" name="estado" placeholder="Ingrese el estado" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editarTipoIdInput">Tipo ID</label>
                        <select id="editarTipoIdInput" name="tipo_id" class="form-control" required>
                            <option value="editarTipoInput" disabled selected>Seleccione un tipo ID</option>
                            <option value="1">Petición</option>
                            <option value="2">Queja</option>
                            <option value="3">Reclamo</option>
                            <option value="4">Solicitud</option>
                        </select>
                    </div>

                    <!-- Otros campos necesarios -->
                    <button type="submit" class="btn btn-primary" id="actualizarPQRSButton">Actualizar PQRS</button>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>

            </div>
        </div>
    </div>
</div>




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

        // Manejar el envío del formulario de creación de PQRS
        $('#crearPQRSButton').click(function () {
            var titulo = $('#tituloInput').val();
            var descripcion = $('#descripcionInput').val();

            // Aquí debes agregar la lógica para enviar los datos del formulario al servidor
            // y crear un nuevo PQRS

            // Después de crear el PQRS, puedes cerrar el modal
            $('#crearModal').modal('hide');

            // Opcional: Mostrar un mensaje de éxito o actualizar la lista de PQRS
        });

        // Manejador de eventos para botones de "Eliminar"
        $('.eliminar-btn').click(function () {
            var idPQRS = $(this).data('id');
            if (confirm("¿Estás seguro de que deseas eliminar el tutorial con ID " + idPQRS + "?")) {

                $.ajax({
                    url: "/Login/SvEliminar?id=" + idPQRS,
                    method: "POST",
                    success: function (response) {
                        // Manejar la respuesta del servidor
                        if (response === "success") {

                            alert("El PQRS se eliminó correctamente.");
                            location.reload();
                        } else {

                            alert("Hubo un error al eliminar PQRS.");
                        }
                    },
                    error: function (error) {
                        console.error("Error al eliminar el PQRS", error);
                    }
                });
            } else {


            }
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
