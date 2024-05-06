/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestionPQRS;
import Mundo.PQRS;
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
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el ID del PQRS a eliminar
        String idPQRS = request.getParameter("id");
        System.out.println("ID del PQRS a eliminar: " + idPQRS);

        // Crear una instancia de GestionPQRS
        GestionPQRS gestionPQRS = new GestionPQRS();

        // Eliminar el PQRS y obtener un booleano indicando si fue eliminado exitosamente
        boolean eliminado = gestionPQRS.eliminarPQRS(Integer.parseInt(idPQRS));

        // Obtener la lista actualizada de PQRS después de eliminar el elemento
        HttpSession session = request.getSession();
        int idUsuario = (int) session.getAttribute("idUsuario"); // Obtener el ID del usuario de la sesión
        List<PQRS> pqrss = GestionPQRS.obtenerPQRSporUsuario(String.valueOf(idUsuario)); // Obtener los PQRS actualizados

        // Almacenar la lista actualizada en la sesión
        session.setAttribute("pqrss", pqrss);

        // Enviar una respuesta al cliente
        if (eliminado) {
            response.getWriter().write("success"); // Enviar 'success' si el PQRS se eliminó correctamente
        } else {
            response.getWriter().write("error"); // Enviar 'error' si hubo un problema al eliminar el PQRS
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
