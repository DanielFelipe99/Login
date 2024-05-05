<%-- 
    Document   : User
    Created on : 30/04/2024, 3:30:23 p. m.
    Author     : jonathan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Agregar enlaces a los archivos CSS de Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <a class="navbar-brand" href="#">PQRS</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#crearModal">Crear PQRS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Buscar PQRS</a>
                        </li>
                    </ul>
                </div>
            </nav>

        </header>


        <main>
            <div class="container my-4">
                <!-- Sección para crear PQRS -->
                <section>
                    <h2>Tus PQRS</h2>
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

                </section>

                <!-- Sección para editar PQRS -->
                <section>
                    <h2>Editar PQRS</h2>
                    <!-- Formulario para editar PQRS -->
                </section>

                <!-- Sección para eliminar PQRS -->
                <section>
                    <h2>Eliminar PQRS</h2>
                    <!-- Tabla o lista de PQRS con opción para eliminar -->
                </section>

                <!-- Sección para buscar PQRS -->
                <section>
                    <h2>Buscar PQRS</h2>
                    <!-- Formulario de búsqueda y tabla de resultados -->
                </section>
            </div>
        </main>



        <!-- Modal para crear PQRS -->
        <div class="modal fade" id="crearModal" tabindex="-1" aria-labelledby="crearModalLabel" aria-hidden="true">
            <div class="modal-dialog">
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
                                <label for="adjuntoInput">Adjunto (PDF)</label>
                                <input type="file" class="form-control-file" id="adjuntoInput" name="adjunto">
                            </div>
                            <!-- Otros campos necesarios -->
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
                        <form>
                            <div class="form-group">
                                <label for="editarTituloInput">Título</label>
                                <input type="text" class="form-control" id="editarTituloInput" placeholder="Ingrese el título">
                            </div>
                            <div class="form-group">
                                <label for="editarDescripcionInput">Descripción</label>
                                <textarea class="form-control" id="editarDescripcionInput" rows="3" placeholder="Ingrese la descripción"></textarea>
                            </div>
                            <!-- Otros campos necesarios -->
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" id="actualizarPQRSButton">Actualizar PQRS</button>
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
            });

        </script>
    </body>
</html>
