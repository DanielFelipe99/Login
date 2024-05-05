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

    public static int verificarUsuario(String nombreCompleto, String contrasenia) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idUsuario = -1;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Consulta SQL parametrizada para verificar si el usuario existe con el nombre completo y la contraseña
            String consulta = "SELECT idUsuario FROM usuario WHERE nombreCompleto = ? AND contrasenia = ?";
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreCompleto);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();

            // Verificar si se encontró algún resultado
            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
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

        return idUsuario;
    }

}
