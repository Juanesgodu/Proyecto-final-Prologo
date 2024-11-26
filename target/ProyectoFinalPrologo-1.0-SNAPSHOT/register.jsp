<%-- 
    Document   : register
    Created on : 21/11/2024, 2:18:53?p. m.
    Author     : L E N O V O
--%>

<%@page import="godu.beans.BeanCancion"%>
<%@page import="godu.beans.BeanPersona"%>
<%@ page import="godu.beans.InfoCola" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

<%
    String nombre = request.getParameter("nombre");
    String nCancion = request.getParameter("nCancion");
    String artista = request.getParameter("artista");
    String URLSpotify = request.getParameter("URLSpotify");

    if (nombre != null && nCancion != null && artista != null) {
        // Crear objeto de la clase InfoCola para guardar la canción
        BeanPersona persona = new BeanPersona();
        BeanCancion cancion = new BeanCancion();
        
        cancion.setnCancion(nCancion);
        cancion.setArtista(artista);
        cancion.setURLSpotify(URLSpotify);
        persona.setNombre(nombre);

        InfoCola cola = new InfoCola();
        cola.guardarCancion(cancion, persona);

        // Redirigir a una página de confirmación
        response.sendRedirect("confirmacion.jsp");
    }
%>
