/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import config.conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danie
 */
public class gestionUsuario {

    public static void agregarUsuario(String nombreCompleto, String numeroTelefono, String cedula, String correo, String contrasenia) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {

            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para insertar los datos
            String consultaUsuario = "INSERT INTO usuario (nombreCompleto, numeroTelefono, correo, contrasenia, cedula) VALUES (?,?,?,?,?)";
            ps = conexion.prepareStatement(consultaUsuario);
            ps.setString(1, nombreCompleto);
            ps.setString(2, numeroTelefono);
            ps.setString(3, correo);
            ps.setString(4, contrasenia);
            ps.setString(5, cedula);

            // Ejecutar la consulta
            ps.executeUpdate();

            // Cerrar recursos
            ps.close();

            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public static int verificarUsuario(String nombreCompleto, String contrasenia, String tipo) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Consulta SQL parametrizada para verificar las credenciales
            String consulta = "";
            if (tipo.equals("usuario")) {
                consulta = "SELECT idUsuario FROM usuario WHERE nombreCompleto = ? AND contrasenia = ?";
            } else if (tipo.equals("administrador")) {
                consulta = "SELECT idAdministrador FROM administrador WHERE nombreAdmin = ? AND contrasenia = ?";
            }

            ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreCompleto);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();

            // Verificar si se encontró algún resultado
            if (rs.next()) {
                if (tipo.equals("usuario")) {
                    id = rs.getInt("idUsuario");
                } else if (tipo.equals("administrador")) {
                    id = rs.getInt("idAdministrador");
                }
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

        return id;
    }

       public static List<Usuario> obtenerUsuario(String idUsuario) {
        List<Usuario> userList = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para obtener los PQRS por ID de usuario
            String consultaPQRSporUsuario = "SELECT * FROM usuario WHERE idUsuario = ?";
            ps = conexion.prepareStatement(consultaPQRSporUsuario);
            ps.setString(1, idUsuario);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Iterar sobre los resultados y agregarlos a la lista
            while (rs.next()) {
                // Crear un objeto PQRS usando el constructor proporcionado
               Usuario usuario = new Usuario();
                       usuario.setIdUsuario(rs.getString("idUsuario"));
                       usuario.setNombreCompleto(rs.getString("nombreCompleto"));
                       usuario.setNumeroTelefono(rs.getString("numeroTelefono"));
                       usuario.setCorreo(rs.getString("correo"));
                       usuario.setContrasenia(rs.getString("contrasenia"));
                       usuario.setCedula(rs.getString("cedula"));
                 
               

                userList.add(usuario);
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
        System.out.println("Contenido de la lista Usuarios:");
        for (Usuario usuario : userList) {
            System.out.println(usuario.getNombreCompleto());
        }

        return userList;
    }
   
   
    public static Usuario obtenerId(String idUsuario) {
        Usuario usuarios = null;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para obtener el PQRS por su ID
            String consultaUsuario = "SELECT * FROM usuario WHERE idUsuario = ?";
            ps = conexion.prepareStatement(consultaUsuario);
            ps.setString(1, idUsuario);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si se encontró un resultado
            if (rs.next()) {
                // Crear un objeto PQRS usando el constructor proporcionado
                       usuarios = new Usuario();
                       usuarios.setIdUsuario(rs.getString("idUsuario"));
                       usuarios.setNombreCompleto(rs.getString("nombreCompleto"));
                       usuarios.setNumeroTelefono(rs.getString("numeroTelefono"));
                       usuarios.setCorreo(rs.getString("correo"));
                       usuarios.setContrasenia(rs.getString("contrasenia"));
                       usuarios.setCedula(rs.getString("cedula"));
                       
                
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

        return usuarios;

    }
        
    public static boolean actualizarUsuario(String idUsuario, String nombreCompleto, String numeroTelefono, String correo, String contrasenia, String cedula) {
     
        Usuario usuario = obtenerId(idUsuario);

        // Verificar si se encontró el PQRS
        if (usuario != null) {
            Connection conexion = null;
            PreparedStatement ps = null;

            try {
                // Obtener la conexión desde la clase Conexion
                conexion miConexion = new conexion();
                conexion = miConexion.getConexion();

                // Preparar la consulta SQL para actualizar los datos del PQRS
                String consultaActualizarUsuario = "UPDATE usuario SET nombreCompleto=?, numeroTelefono=?, correo=?, contrasenia=?, cedula=? WHERE idUsuario=?";
                ps = conexion.prepareStatement(consultaActualizarUsuario);

                ps.setString(1, nombreCompleto);
                ps.setString(2, numeroTelefono);
                ps.setString(3, correo);
                ps.setString(4, contrasenia);
                ps.setString(5, cedula);
                ps.setString(6, idUsuario);
               

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
            // Si no se encontró el Usuario, devolver false
            return false;
        }

    }
}
