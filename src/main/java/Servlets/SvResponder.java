/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import Mundo.PQRS;
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
 * @author danie
 */
@WebServlet(name = "SvResponder", urlPatterns = {"/SvResponder"})
public class SvResponder extends HttpServlet {

   
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
        
        String idPQRS = request.getParameter("idPQRS");
        
       

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String adjunto = request.getParameter("estado");
      
        String tipoId = request.getParameter("tipo_id");
        
  
        
         // Obtener el parámetro de la respuesta desde el formulario
        String respuesta = request.getParameter("respuesta");
     
        GestionPQRS.actualizarEstado(idPQRS, "Revisado");
        // Aquí puedes llamar al método que has creado para manejar la respuesta
        // Supongamos que el método se llama "responderPQRS" y está en la clase "GestionPQRS"
        System.out.println("El id pqrs : "+idPQRS);
        GestionPQRS.responderPQRS(respuesta,idPQRS);
        
        String script = "<script>alert('Respuesta realizada correctamente.'); window.location.href = 'templates/Admin.jsp';</script>";
        response.setContentType("text/html");
        response.getWriter().write(script);
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
