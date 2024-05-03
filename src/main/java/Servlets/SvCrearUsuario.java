/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.gestionUsuario;
import config.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "SvCrearUsuario", urlPatterns = {"/SvCrearUsuario"})
public class SvCrearUsuario extends HttpServlet {
    conexion conexion = new conexion();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Connection establecerConexion = conexion.getConexion();
          System.out.println("Estoy en el servlet");
        if(establecerConexion != null){
            
            String nombreCompleto = request.getParameter("nombreCompleto");
            String numeroTelefono = request.getParameter("numeroTelefono");
            String cedula = request.getParameter("cedula");
            String correo = request.getParameter("correo");
            String contrasenia = request.getParameter("contrasenia");
    
            gestionUsuario.agregarUsuario(nombreCompleto, numeroTelefono, cedula, correo, contrasenia);
            String script = "<script>alert('El usuario se ha agregado correctamente.'); window.location.href = 'index.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
            //response.sendRedirect("index.jsp");
            
            
        }else{
            response.getWriter().println("Error:No se pudo establecer la conexion, paila");
            String script = "<script>alert('El usuario NO SE agregó correctamente.'); window.location.href = 'index.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
