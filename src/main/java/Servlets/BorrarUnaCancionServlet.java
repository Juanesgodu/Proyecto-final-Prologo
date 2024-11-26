/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Utils.InfoCola;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BorrarUnaCancionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCancion = request.getParameter("idCancion");

        InfoCola cola = new InfoCola();
        try {
            // Llamamos al método para borrar la canción
            cola.borrarCancion(idCancion);
            // Redirigimos con el parámetro "borrado=true" para que la página se refresque automáticamente
            response.sendRedirect("ConsultarCanciones");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error al borrar la canción: " + e.getMessage() + "</h3>");
        }
    }
}