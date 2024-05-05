/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.gestionUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danie
 */
@WebServlet(name = "SvVerificarUsuario", urlPatterns = {"/SvVerificarUsuario"})
public class SvVerificarUsuario extends HttpServlet {

   
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
        String nombreCompleto = request.getParameter("nombreCompleto");
        String contrasenia = request.getParameter("contrasenia");
        
        if (gestionUsuario.verificarUsuario(nombreCompleto, contrasenia)) {
            // El usuario está registrado, redirigir a la página principal
            String script = "<script>alert('Usuario encontrado'); window.location.href = 'templates/User.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else {
            // El usuario no está registrado, mostrar un mensaje de error
        
            String script = "<script>alert('Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.'); window.location.href = 'User.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
