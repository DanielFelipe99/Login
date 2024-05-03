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
public class Usuario extends Personas {


    

    /*-----------------------------------
     *Atributos  
     *----------------------------------*/
    private int idUsuario;
    private List<Personas> listaUsuarios;

    
    /*-----------------------------------------------
     *Metodso 
     *---------------------------------------------*/


    public Usuario(int idUsuario,String nombreCompleto, String numeroTelefono, String cedula, String correo) {
        super(nombreCompleto, numeroTelefono, cedula, correo);
        this.idUsuario = idUsuario;
    }

    public void registrarUsuario(Usuario usuario) {
        
    }
    
    
    
    public Usuario buscarUsuarioPorCedula(String cedula) {
     return null;
    }
    

    public void eliminarUsuario(String cedula) {
        
    }
    
    public void actualizarUsuario(Usuario usuario) {
       
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
