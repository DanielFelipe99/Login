/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.Usuario;
import Mundo.gestionUsuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danie
 */
@WebServlet(name = "SvActualizarUsuario", urlPatterns = {"/SvActualizarUsuario"})
public class SvActualizarUsuario extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ya vamos a editar usuario");

        String idUsuario = request.getParameter("idUsuario");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String numeroTelefono = request.getParameter("numeroTelefono");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        String cedula = request.getParameter("cedula");

        System.out.println("USUARIO EN EL GET: " + idUsuario);
      
        
        gestionUsuario usuarios = new gestionUsuario();
        System.out.println("Antes de entrar al metodo");
        System.out.println("usuario: " + idUsuario);
        boolean actualizacionExitosa = usuarios.actualizarUsuario(idUsuario, nombreCompleto, numeroTelefono, correo, contrasenia, cedula);
        System.out.println("Actualizacion" + actualizacionExitosa);
        if (actualizacionExitosa){
            // Obtener la lista actualizada de PQRS después de eliminar el elemento
            HttpSession session = request.getSession();
            int idUser = (int) session.getAttribute("idUsuario"); // Obtener el ID del usuario de la sesión
            List<Usuario> users = gestionUsuario.obtenerUsuario(String.valueOf(idUser)); // Obtener los PQRS actualizados

            // Almacenar la lista actualizada en la sesión
            session.setAttribute("users", users);
            String script = "<script>alert('Se actualizo correctamente.'); window.location.href = 'templates/User.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else {
            String script = "<script>alert('Error al actualizar.'); window.location.href = 'templates/User.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String idUsuario = request.getParameter("idUser");
        System.out.println("llegamos " + idUsuario);

        gestionUsuario usuario = new gestionUsuario();
        System.out.println("antes del obtener");
        Usuario encontrado = usuario.obtenerId(idUsuario);
        System.out.println("Despues del obtener"+encontrado.getNombreCompleto());
        
        if (usuario != null) {
            // Convertir el objeto PQRS a JSON
            Gson gson = new Gson();
            String pqrsJson = gson.toJson(encontrado);

            // Configurar la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //System.out.println("id ha envbiar " + encontrado.getIdPqrs());
            System.out.println("id usaurio enviar  " + encontrado.getIdUsuario());

           response.getWriter().write(pqrsJson);
        } else {
            // Si no se encontró el PQRS, enviar una respuesta con un mensaje de error
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("No se encontró ningún User con el ID: " + idUsuario);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
