/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jonathan
 */
public class conexion {
     /*-----------------------------------------------------------------
     * Atributos
     *------------------------------------------------*/
   Connection conectar = null;
    String usuario = "root";
    String contrasenia = "admin";
    String baseDatos = "gestionpqrs"; // Asumiendo que el nombre de la base de datos es "store"
    String ip = "localhost";
    String puerto = "3306";
    String zona = "?serverTimezone=UTC";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + baseDatos + zona;
    
    public Connection getConexion(){
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            if (conectar != null) {
                System.out.println("Conexion exitosa mi may");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se ha encontrado el controlador JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println("Cadena de conexion: " + cadena); // Imprimir la cadena de conexion
            e.printStackTrace();
        }
        
        return conectar;
    }
    
}
