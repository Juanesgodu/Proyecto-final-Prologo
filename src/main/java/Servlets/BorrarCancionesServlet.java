/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Utils.InfoCola;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class BorrarCancionesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar la codificación
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        InfoCola cola = new InfoCola();
        try {
            // Borrar todas las canciones
            cola.borrarTodasLasCanciones();
            response.sendRedirect("gestionCola");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error al eliminar las canciones: " + e.getMessage() + "</h3>");
        }
    }
}
