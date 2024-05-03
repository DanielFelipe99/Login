/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

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
        Connection conexion= null;
        PreparedStatement ps = null;
  
        
        
        try {
            // Establecer conexión con la base de datos
            System.out.println("Estoy dentro del metodo agregar");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpqrs", "root", "admin");
            
            // Preparar la consulta SQL para insertar los datos
            String consultaUsuario = "INSERT INTO usuario (nombreCompleto, numeroTelefono, correo, contrasenia, cedula) VALUES (?,?,?,?,?)";
            ps= conexion.prepareStatement(consultaUsuario);
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
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     
     
    public static boolean verificarUsuario(String nombreCompleto, String contrasenia) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean usuarioValido = false;

        try {
            // Establecer conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionpqrs", "root", "admin");

            // Consulta SQL parametrizada para verificar si el usuario existe con el nombre completo y la contraseña
            String consulta = "SELECT COUNT(*) FROM usuario WHERE nombreCompleto = ? AND contrasenia = ?";
            ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreCompleto);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();

            // Verificar si se encontró algún resultado
            if (rs.next()) {
                int count = rs.getInt(1);
                usuarioValido = (count > 0);
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

        return usuarioValido;
    }

}
