/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Part adjuntoPart = request.getPart("adjunto");

        // Guardar el archivo adjunto en el servidor (si se proporciona)
        String adjuntoFilename = null;
        if (adjuntoPart != null && adjuntoPart.getSize() > 0) {
            // Guardar el archivo adjunto en el servidor
            
            String adjuntoDirectory = getServletContext().getRealPath("adjuntos"); // Obtener la ruta real del directorio de adjuntos
            adjuntoFilename = adjuntoPart.getSubmittedFileName();

            // Ruta completa del archivo adjunto
            String adjuntoFilePath = adjuntoDirectory + File.separator + adjuntoFilename;
            System.out.println("tuta completa: "+adjuntoFilePath);

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
        GestionPQRS.agregarPQRS(titulo, descripcion, adjuntoFilename, "No leído");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
