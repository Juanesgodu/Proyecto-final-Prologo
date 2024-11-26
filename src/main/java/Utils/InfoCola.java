package Utils;

import javax.sql.DataSource;
import Utils.DatabaseConnection;
import godu.beans.BeanCancion;
import godu.beans.BeanPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class InfoCola {
    private Connection getConnection() throws SQLException {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/miDataSource"); // Ruta JNDI
            return ds.getConnection();
        } catch (NamingException e) {
            throw new SQLException("Error al obtener el DataSource", e);
        }
    }

    // Método para guardar una canción
    public void guardarCancion(BeanCancion cancion, BeanPersona persona) throws SQLException {
    String sqlInsert = "INSERT INTO infocancion (nombre, nombreCancion, artista, URLSpotify) VALUES (?, ?, ?, ?)";
    String sqlSelect = "SELECT * FROM infocancion WHERE nombreCancion = ?";  // Consulta para verificar la inserción

    Connection conn = null;
    PreparedStatement stmtInsert = null;
    PreparedStatement stmtSelect = null;
    ResultSet rs = null;

    try {
        // Obtener conexión
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);  // Desactivar auto-commit para manejar transacciones manualmente

        // Preparar las sentencias SQL
        stmtInsert = conn.prepareStatement(sqlInsert);
        stmtInsert.setString(1, persona.getNombre());
        stmtInsert.setString(2, cancion.getnCancion());
        stmtInsert.setString(3, cancion.getArtista());
        stmtInsert.setString(4, cancion.getURLSpotify());
        stmtInsert.executeUpdate();

        // Hacer commit de la transacción
        conn.commit();

        // Consultar si la canción se guardó correctamente
        stmtSelect = conn.prepareStatement(sqlSelect);
        stmtSelect.setString(1, cancion.getnCancion());
        rs = stmtSelect.executeQuery();

        // Verificar la inserción
        while (rs.next()) {
            String nombreCancion = rs.getString("nombreCancion");
            String artista = rs.getString("artista");
            String urlSpotify = rs.getString("URLSpotify");
            System.out.println("Canción registrada: " + nombreCancion + " de " + artista + " - " + urlSpotify);
        }

    } catch (SQLException e) {
        // En caso de error, hacer rollback
        if (conn != null) {
            conn.rollback();
        }
        System.out.println("Error al guardar la canción");
        e.printStackTrace();
        throw new SQLException("Error al guardar la canción", e);

    } finally {
        // Cerrar los recursos
        try {
            if (rs != null) rs.close();
            if (stmtInsert != null) stmtInsert.close();
            if (stmtSelect != null) stmtSelect.close();
            if (conn != null) conn.setAutoCommit(true);  // Restaurar auto-commit
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    // Método para consultar todas las canciones en la base de datos
    public List<BeanCancion> consultarColaCanciones() throws SQLException {
        List<BeanCancion> canciones = new ArrayList<>();
        String sql = "SELECT nombreCancion, artista, URLSpotify FROM infocancion";
        
        // Usamos try-with-resources para cerrar automáticamente la conexión, el PreparedStatement y el ResultSet
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Procesamos los resultados de la consulta
            while (rs.next()) {
                BeanCancion cancion = new BeanCancion();
                cancion.setnCancion(rs.getString("nombreCancion"));
                cancion.setArtista(rs.getString("artista"));
                cancion.setURLSpotify(rs.getString("URLSpotify"));
                canciones.add(cancion);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            throw new SQLException("Error al consultar la cola de canciones", e);
        }
        return canciones;
    }

    // Método para borrar todas las canciones en la base de datos
    public void borrarTodasLasCanciones() throws SQLException {
        String sqlDelete = "DELETE FROM infocancion";
        String sqlReorg = "CALL reajustar_ids()";
        PreparedStatement stmtDelete = null;
        PreparedStatement stmtReorg = null;
        ResultSet rs = null;
        // Usamos try-with-resources para cerrar automáticamente la conexión y el PreparedStatement
        try (Connection conn = DatabaseConnection.getConnection();
             ) {
            stmtDelete = conn.prepareStatement(sqlDelete);
            stmtDelete.executeUpdate();
            
            stmtReorg = conn.prepareStatement(sqlReorg);
            rs = stmtReorg.executeQuery();
            
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            throw new SQLException("Error al borrar las canciones", e);
        }
    }
    
    public void borrarCancion(String idCancion) throws SQLException {
    String sqlDelete = "DELETE FROM infocancion WHERE nombreCancion = ?";
    String sqlReorg = "CALL reajustar_ids()";
    PreparedStatement stmtDelete = null;
    PreparedStatement stmtReorg = null;
    ResultSet rs = null;
    
    try (Connection conn = DatabaseConnection.getConnection();
         ) {
        stmtDelete = conn.prepareStatement(sqlDelete);
        stmtDelete.setString(1, idCancion);
        stmtDelete.executeUpdate();
        
        stmtDelete.executeUpdate();
            
        stmtReorg = conn.prepareStatement(sqlReorg);
        rs = stmtReorg.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al borrar la canción", e);
    }
}

}
