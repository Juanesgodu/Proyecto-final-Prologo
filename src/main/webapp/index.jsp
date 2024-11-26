<%-- 
    Document   : index
    Created on : 21/11/2024, 2:27:31?p. m.
    Author     : L E N O V O
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Formulario de Registro</title>
</head>
<body>

<h2>Formulario de Registro</h2>

<form action="register.jsp" method="POST">
    <label for="nombre">Nombre/Apodo:</label>
    <input type="text" name="nombre" required/><br><br>

    <label for="nCancion">Nombre de la Canción:</label>
    <input type="text" name="nCancion" required/><br><br>

    <label for="artista">Artista:</label>
    <input type="text" name="artista" required/><br><br>

    <label for="URLSpotify">URL Spotify:</label>
    <input type="text" name="URLSpotify"/><br><br>

    <input type="submit" value="Registrar"/>
</form>

</body>
</html>
<%
    // Lógica de backend (inserción en base de datos, etc.)
    response.sendRedirect("register.jsp");  // Redirigir a index.html después de procesar el formulario
%>