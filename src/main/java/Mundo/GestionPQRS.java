/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jonathan
 */
public class GestionPQRS {

    public static void agregarPQRS(String titulo,String descripcion, String adjuntos, String estado) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            // Obtener la conexión desde la clase Conexion
            conexion miConexion = new conexion();
            conexion = miConexion.getConexion();

            // Preparar la consulta SQL para insertar los datos de la PQRS
            String consultaPQRS = "INSERT INTO pqrs (titulo,descripcion, adjuntos, estado) VALUES (?,?,?,?)";
            ps = conexion.prepareStatement(consultaPQRS);
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setString(3, adjuntos);
            ps.setString(4, estado);

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
}
