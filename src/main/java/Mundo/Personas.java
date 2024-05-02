/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

/**
 *
 * @author jonathan
 */
public class Personas {
    /*------------------------------------------
    *Atributos
    *------------------------------------------*/
    private String nombreCompleto;
    private String numeroTelefono;
    private String cedula;
    private String correo;


    
    /*-----------------------------------------------------------
     * Metodos
     *-----------------------------------------------------------*/
    public Personas(String nombreCompleto, String numeroTelefono, String cedula, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.cedula = cedula;
        this.correo = correo;
    
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   
}
