/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;


import config.conexion;
import javax.mail.Authenticator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author jonathan
 */
public class GestionPQRS {

    public static void agregarPQRS(String titulo, String descripcion, String adjuntos, String estado, String tipo_id, String usuario_id) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para insertar los datos de la PQRS
            String consultaPQRS = "INSERT INTO pqrs (titulo,descripcion, adjuntos, estado,tipo_id,usuario_id) VALUES (?,?,?,?,?,?)";
            ps = conexion.prepareStatement(consultaPQRS);
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setString(3, adjuntos);
            ps.setString(4, estado);
            ps.setString(5, tipo_id);
            ps.setString(6, usuario_id);
            // Ejecutar la consulta
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   public static void responderPQRS(String respuesta, String idPQRS) {
        Connection conexion = null;
        PreparedStatement ps = null;
     

        try {
            // Código para obtener la conexión a la base de datos
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para insertar la respuesta en la tabla respuesta_pqrs
            String consulta = "INSERT INTO respuesta_pqrs (idPQRS, respuesta) VALUES (?, ?)";
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, idPQRS);
            ps.setString(2, respuesta);

            // Ejecutar la consulta
            ps.executeUpdate();

            // Obtener la información del usuario para enviar el correo electrónico
            //List<Usuario> userList = obtenerUsuario(idUsuario);

            // Enviar correo electrónico al usuario
            //for (Usuario usuario : userList) {
              //  enviarCorreo(usuario.getCorreo(), "Respuesta a su PQRS", "Estimado " + usuario.getNombreCompleto() + ",\n\nHemos recibido su PQRS y le estamos enviando la siguiente respuesta:\n\n" + respuesta);
            //}
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones, por ejemplo, redirigir a una página de error
        } finally {
            // Cerrar recursos
            try {
                
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
   /*
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) throws AddressException, MessagingException {
        // Configuración para enviar correo electrónico
        String remitente = "danielf.zapata221@umariana.edu.co"; // Coloca aquí tu dirección de correo
        String password = "1085346286"; // Coloca aquí tu contraseña de correo

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props,new Authenticator() {
           
        });

       
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);

            System.out.println("Correo electrónico enviado correctamente a " + destinatario);
        
    }
   */
   
    public static List<PQRS> obtenerPQRSporUsuario(String usuarioId) {
        List<PQRS> pqrsList = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para obtener los PQRS por ID de usuario
            String consultaPQRSporUsuario = "SELECT * FROM pqrs WHERE usuario_id = ?";
            ps = conexion.prepareStatement(consultaPQRSporUsuario);
            ps.setString(1, usuarioId);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Iterar sobre los resultados y agregarlos a la lista
            while (rs.next()) {
                // Crear un objeto PQRS usando el constructor proporcionado
                PQRS pqrs = new PQRS(
                        rs.getString("idPQRS"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("adjuntos"),
                        rs.getString("estado"),
                        rs.getString("tipo_id"),
                        rs.getString("usuario_id")
                );

                pqrsList.add(pqrs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Contenido de la lista pqrsList:");
        for (PQRS pqrs : pqrsList) {
            System.out.println(pqrs.getTipo());
        }

        return pqrsList;
    }

    public static PQRS obtenerPQRSId(String idPQRS) {
        PQRS pqrs = null;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para obtener el PQRS por su ID
            String consultaPQRSporId = "SELECT * FROM pqrs WHERE idPQRS = ?";
            ps = conexion.prepareStatement(consultaPQRSporId);
            ps.setString(1, idPQRS);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si se encontró un resultado
            if (rs.next()) {
                // Crear un objeto PQRS usando el constructor proporcionado
                pqrs = new PQRS(
                        rs.getString("idPQRS"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("adjuntos"),
                        rs.getString("estado"),
                        rs.getString("tipo_id"),
                        rs.getString("usuario_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pqrs;

    }

    /**
     * Método para eliminar un tutorial por su ID.
     *
     * @param id El ID del tutorial a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean eliminarPQRS(int id) {
        // Obtener la conexión desde la clase Conexion
        conexion miConexion = new conexion();
        Connection conexion = miConexion.getConexion();
        if (conexion != null) {
            String sql = "DELETE FROM pqrs WHERE idPQRS = ?";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                int filasEliminadas = preparedStatement.executeUpdate();
                return filasEliminadas > 0; // Devuelve true si se eliminó al menos una fila
            } catch (SQLException ex) {
                System.out.println("Error SQL al ejecutar la consulta: " + ex.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: No se pudo establecer conexión con la base de datos.");
            return false;
        }
    }

    public static boolean actualizarPQRS(String idPQRS, String titulo, String descripcion, String adjuntos, String estado, String tipoId, String usuarioId) {
        // Obtener el PQRS por su ID
        PQRS pqrs = obtenerPQRSId(idPQRS);

        // Verificar si se encontró el PQRS
        if (pqrs != null) {
            Connection conexion = null;
            PreparedStatement ps = null;

            try {
                // Obtener la conexión desde la clase Conexion
                conexion miConexion = new conexion();
                conexion = miConexion.getConexion();

                // Preparar la consulta SQL para actualizar los datos del PQRS
                String consultaActualizarPQRS = "UPDATE pqrs SET titulo=?, descripcion=?, adjuntos=?, estado=?, tipo_id=?, usuario_id=? WHERE idPQRS=?";
                ps = conexion.prepareStatement(consultaActualizarPQRS);

                ps.setString(1, titulo);
                ps.setString(2, descripcion);
                ps.setString(3, adjuntos);
                ps.setString(4, estado);
                ps.setString(5, tipoId);
                ps.setString(6, usuarioId);
                ps.setString(7, idPQRS);

                // Ejecutar la consulta
                int filasActualizadas = ps.executeUpdate();

                // Verificar si se actualizó correctamente
                return filasActualizadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                // Cerrar recursos
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Si no se encontró el PQRS, devolver false
            return false;
        }
    }

    public static boolean actualizarEstado(String idPQRS, String estado) {
        // Obtener el PQRS por su ID
        PQRS pqrs = obtenerPQRSId(idPQRS);

        // Verificar si se encontró el PQRS
        if (pqrs != null) {
            Connection conexion = null;
            PreparedStatement ps = null;

            try {
                // Obtener la conexión desde la clase Conexion
                conexion miConexion = new conexion();
                conexion = miConexion.getConexion();

                // Preparar la consulta SQL para actualizar los datos del PQRS
                String consultaActualizarPQRS = "UPDATE pqrs SET estado=? WHERE idPQRS=?";
                ps = conexion.prepareStatement(consultaActualizarPQRS);

                ps.setString(1, estado);

                ps.setString(2, idPQRS);

                // Ejecutar la consulta
                int filasActualizadas = ps.executeUpdate();

                // Verificar si se actualizó correctamente
                return filasActualizadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                // Cerrar recursos
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Si no se encontró el PQRS, devolver false
            return false;
        }
    }

    public static List<PQRS> obtenerTodosPQRS() {
        List<PQRS> pqrsList = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para obtener todos los PQRS
            String consultaTodosPQRS = "SELECT * FROM pqrs";
            ps = conexion.prepareStatement(consultaTodosPQRS);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Iterar sobre los resultados y agregarlos a la lista
            while (rs.next()) {
                // Crear un objeto PQRS usando el constructor proporcionado
                PQRS pqrs = new PQRS(
                        rs.getString("idPQRS"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("adjuntos"),
                        rs.getString("estado"),
                        rs.getString("tipo_id"),
                        rs.getString("usuario_id")
                );

                pqrsList.add(pqrs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return pqrsList;
    }

}
