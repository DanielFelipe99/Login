/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import Mundo.PQRS;
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
 * @author jonathan
 */
@WebServlet(name = "SvActualizar", urlPatterns = {"/SvActualizar"})
public class SvActualizar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ya vamos a deitar");

        String idPQRS = request.getParameter("idPQRS");

        GestionPQRS pqrs = new GestionPQRS();
        PQRS encontrado = pqrs.obtenerPQRSId(idPQRS);

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String adjunto = request.getParameter("estado");
        String estado = encontrado.getEstado();
        String tipoId = request.getParameter("tipo_id");
        String usuario_id = encontrado.getUsuraio_id();

        if (tipoId == null) {
            tipoId = encontrado.getTipo();

        }
        if (adjunto == null) {
            adjunto = encontrado.getAdjuntos();
        }

        GestionPQRS pqrsd = new GestionPQRS();
        boolean actualizacionExitosa = pqrsd.actualizarPQRS(idPQRS, titulo, descripcion, adjunto, estado, tipoId, usuario_id);

        if (actualizacionExitosa) {
            // Obtener la lista actualizada de PQRS después de eliminar el elemento
            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("idUsuario"); // Obtener el ID del usuario de la sesión
            List<PQRS> pqrss = GestionPQRS.obtenerPQRSporUsuario(String.valueOf(idUsuario)); // Obtener los PQRS actualizados

            // Almacenar la lista actualizada en la sesión
            session.setAttribute("pqrss", pqrss);
            String script = "<script>window.location.href = 'templates/User.jsp';</script>";
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
        String idPQRS = request.getParameter("id");
        System.out.println("llegamos " + idPQRS);

        GestionPQRS pqrs = new GestionPQRS();
        PQRS encontrado = pqrs.obtenerPQRSId(idPQRS);

        if (pqrs != null) {
            // Convertir el objeto PQRS a JSON
            Gson gson = new Gson();
            String pqrsJson = gson.toJson(encontrado);

            // Configurar la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            System.out.println("id ha envbiar " + encontrado.getIdPqrs());
            System.out.println("id usaurio enviar  " + encontrado.getUsuraio_id());

            response.getWriter().write(pqrsJson);
        } else {
            // Si no se encontró el PQRS, enviar una respuesta con un mensaje de error
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("No se encontró ningún PQRS con el ID: " + idPQRS);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
