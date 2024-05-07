/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import Mundo.PQRS;
import Mundo.gestionUsuario;
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

        int idUsuario = gestionUsuario.verificarUsuario(nombreCompleto, contrasenia, "usuario");
        int idAdmin = gestionUsuario.verificarUsuario(nombreCompleto, contrasenia, "administrador");

        if (idUsuario != -1) {
            // Usuario encontrado, redirigir a la página de usuario
            List<PQRS> pqrss = GestionPQRS.obtenerPQRSporUsuario(String.valueOf(idUsuario));
            System.out.println("Contenido de la lista pqrsList antes de enviar:");
            for (PQRS pqrs : pqrss) {
                System.out.println(pqrs.getTitulo());
            }
            HttpSession session = request.getSession();
            session.setAttribute("pqrss", pqrss);
            session.setAttribute("idUsuario", idUsuario);
            session.setAttribute("nombreCompleto", nombreCompleto);
            session.setAttribute("tipoUsuario", "usuario"); // esta línea para usuarios normales
            
            String script = "<script>alert('Usuario encontrado'); window.location.href = 'templates/User.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else if (idAdmin != -1) {
            // Administrador encontrado, redirigir a la página de administrador
            HttpSession session = request.getSession();
            session.setAttribute("idAdmin", idAdmin);
            session.setAttribute("nombreAdmin", nombreCompleto);
            session.setAttribute("tipoUsuario", "administrador"); //tipo de usuario como administrador
            
            String script = "<script>alert('Administrador encontrado'); window.location.href = 'templates/Admin.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        } else {
            // Ningún usuario ni administrador encontrado, mostrar mensaje de error
            String script = "<script>alert('Usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.'); window.location.href = 'index.jsp';</script>";
            response.setContentType("text/html");
            response.getWriter().write(script);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
