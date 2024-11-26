/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import godu.beans.BeanCancion;
import Utils.InfoCola;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ConsultarCancionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar la codificación
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        InfoCola cola = new InfoCola();
        try {
            // Consultar todas las canciones
            List<BeanCancion> canciones = cola.consultarColaCanciones();

            // Escribir el HTML con estilo para mostrar las canciones
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<style>");
            response.getWriter().println("body { font-family: Poppins, sans-serif; background-color: #000000; color: #f4f4f4; margin: 0; padding: 0; }");
            response.getWriter().println("h3 { text-align: center; margin-top: 20px; color: #ba4a00; }");
            response.getWriter().println("table { width: 80%; margin: 20px auto; border-collapse: collapse; border-radius: 10px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); }");
            response.getWriter().println("th, td { padding: 12px 15px; text-align: left; color: #f4f4f4; }");
            response.getWriter().println("th { background-color: #ba4a00; color: white; font-size: 16px; }");
            response.getWriter().println("tr:nth-child(even) { background-color: #333333; }");
            response.getWriter().println("tr:hover { background-color: #444444; }");
            response.getWriter().println("a { color: #ba4a00; text-decoration: none; }");
            response.getWriter().println("a:hover { text-decoration: underline; }");
            response.getWriter().println("</style>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");

            // Título
            response.getWriter().println("<h3>Lista de Canciones en la Cola de Reproducción</h3>");

            // Mostrar la tabla
            response.getWriter().println("<table>");
            response.getWriter().println("<tr><th>Nombre Canción</th><th>Artista</th><th>URL Spotify</th><th>Acciones</th></tr>");
            for (BeanCancion cancion : canciones) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + cancion.getnCancion() + "</td>");
                response.getWriter().println("<td>" + cancion.getArtista() + "</td>");
                response.getWriter().println("<td><a href='" + cancion.getURLSpotify() + "' target='_blank'>Ir a Spotify</a></td>");
                // Formulario para borrar canción
                response.getWriter().println("<td>");
                response.getWriter().println("<form action='BorrarUnaCancionServlet' method='POST'>");
                response.getWriter().println("<input type='hidden' name='idCancion' value='" + cancion.getnCancion() + "'>");
                response.getWriter().println("<button type='submit' style='background-color: #ba4a00; color: white; border: none; padding: 5px 10px; cursor: pointer;'>Borrar</button>");
                response.getWriter().println("</form>");
                response.getWriter().println("</td>");
                response.getWriter().println("</tr>");
            }
            response.getWriter().println("</table>");
            response.getWriter().println("<form action='gestionCola'>");
            response.getWriter().println("<button type='submit' style='background-color: #ba4a00; color: white; border: none; padding: 20px 10px; cursor: pointer;'>Volver</button>");
            response.getWriter().println("</form>");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error al obtener las canciones: " + e.getMessage() + "</h3>");
        }
    }
}
