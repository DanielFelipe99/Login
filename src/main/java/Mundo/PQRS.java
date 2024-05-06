/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

/**
 *
 * @author jonathan
 */
public class PQRS {

    /*----------------------------------------------------------------------------
     * Atributos
     *--------------------------------------------------------------------------*/
    private String idPqrs;
    private String titulo;
    private String descripcion;
    private String adjuntos;
    private String estado;
    private String tipo;
    /*---------------------------------------------------------------------------
     * Metodos 
     *-------------------------------------------------------------------------*/
    // Constructor
    public PQRS(String idPqrs ,String titulo, String descripcion, String adjuntos, String estado,String tipo) {
        this.idPqrs=idPqrs;
        this.titulo=titulo;
        this.descripcion = descripcion;
        this.adjuntos = adjuntos;
        this.estado = estado;
        this.tipo=tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(String adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdPqrs() {
        return idPqrs;
    }

    public void setIdPqrs(String idPqrs) {
        this.idPqrs = idPqrs;
    }

    
}
