/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import Mundo.PQRS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "SvCrearPQRS", urlPatterns = {"/SvCrearPQRS"})
@MultipartConfig
public class SvCrearPQRS extends HttpServlet {

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

        // Obtener los datos del formulario
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");

        // Obtener el archivo adjunto
        Part adjuntoPart = request.getPart("adjunto");

        // Obtener el ID del usuario desde la sesión
        HttpSession session = request.getSession(false);
        int idUsuario = -1; // Valor predeterminado en caso de que no se encuentre el ID del usuario
        if (session != null) {
            idUsuario = (int) session.getAttribute("idUsuario");
        }

        // Guardar el archivo adjunto en el servidor (si se proporciona)
        String adjuntoFilename = null;
        String adjuntoFilePath = null;
        if (adjuntoPart != null && adjuntoPart.getSize() > 0) {
            // Guardar el archivo adjunto en el servidor
            String adjuntoDirectory = getServletContext().getRealPath("/adjuntos");
            adjuntoFilename = adjuntoPart.getSubmittedFileName();
            adjuntoFilePath = adjuntoDirectory + File.separator + adjuntoFilename;

            // Guardar el archivo adjunto en el sistema de archivos
            try (InputStream input = adjuntoPart.getInputStream(); OutputStream output = new FileOutputStream(adjuntoFilePath)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Llamar al método para agregar la PQRS
        GestionPQRS.agregarPQRS(titulo, descripcion, adjuntoFilename, "No leído", categoria, String.valueOf(idUsuario));

        // Actualizar la lista de PQRS en la sesión
        List<PQRS> pqrss = GestionPQRS.obtenerPQRSporUsuario(String.valueOf(idUsuario));
        session.setAttribute("pqrss", pqrss);

        // Redireccionar a la página de usuario
        String script = "<script>alert('PQRS creada exitosamente'); window.location.href = 'templates/User.jsp';</script>";
        response.setContentType("text/html");
        response.getWriter().write(script);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
