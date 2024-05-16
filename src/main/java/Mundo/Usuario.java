/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import java.util.List;

/**
 *
 * @author jonathan
 */
public class Usuario{


    

    /*-----------------------------------
     *Atributos  
     *----------------------------------*/
    private String idUsuario;
    private String nombreCompleto;
    private String numeroTelefono;
    private String correo;
    private String contrasenia;
    private String cedula;

    
    /*-----------------------------------------------
     *Metodos 
     *---------------------------------------------*/

    public Usuario(String idUsuario, String nombreCompleto, String numeroTelefono, String correo, String contrasenia, String cedula) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.cedula = cedula;
    }

    public Usuario() {
    }
    
    

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    

    
}
