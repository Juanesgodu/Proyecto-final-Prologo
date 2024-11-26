/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import godu.beans.BeanCancion;
import godu.beans.BeanPersona;
import Utils.InfoCola;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegistrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar la codificación
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Obtener datos del formulario
        String nombre = request.getParameter("nombre");
        String nCancion = request.getParameter("nCancion");
        String artista = request.getParameter("artista");
        String urlSpotify = request.getParameter("URLSpotify");

        BeanPersona persona = new BeanPersona();
        BeanCancion cancion = new BeanCancion();

        persona.setNombre(nombre);
        cancion.setnCancion(nCancion);
        cancion.setArtista(artista);
        cancion.setURLSpotify(urlSpotify);

        InfoCola cola = new InfoCola();

        try (PrintWriter out = response.getWriter()) {
        cola.guardarCancion(cancion, persona);
        // Mostrar mensaje de éxito
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Canción registrada exitosamente.');");
        out.println("window.history.back();"); // Volver a la página anterior
        out.println("</script>");
        response.sendRedirect("index.html");
        } catch (SQLException e) {
        e.printStackTrace();
        try (PrintWriter out = response.getWriter()) {
            // Mostrar mensaje de error
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error al registrar la canción: " + e.getMessage().replace("'", "\\'") + "');");
            out.println("window.history.back();"); // Volver a la página anterior
            out.println("</script>");
        }
    }
    }
    
}
