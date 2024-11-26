
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("usuario");
        String password = request.getParameter("contrasena");

        // Verificar credenciales
        if ("Prologo".equals(username) && "Prologo_1234".equals(password)) {
            // Crear sesión y almacenar el usuario
            HttpSession session = request.getSession();
            session.setAttribute("usuario", username);
            session.setMaxInactiveInterval(15 * 60); // Expiración: 15 minutos

            response.sendRedirect("gestionCola");
        } else {
            response.getWriter().println("<h3>Usuario o contraseña incorrectos</h3>");
        }
    }
}
