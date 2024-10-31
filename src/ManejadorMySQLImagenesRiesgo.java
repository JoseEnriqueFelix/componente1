import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ManejadorMySQLImagenesRiesgo {

    private final static String URL = "jdbc:mysql://localhost:3306/imagenesriesgo";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "contraseña";
    private Connection connection = null;
    private CallableStatement callableStatement = null;

    public ManejadorMySQLImagenesRiesgo() {
        conectarBaseDeDatos();
    }

    public void conectarBaseDeDatos() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Exito");
        } catch (SQLException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    public String query(String texto) {
        String s = "";
        try {
            String query = "SELECT nombreImagen, extension FROM IMAGENES WHERE nombreImagen = '" + texto + "'";
            System.out.println(query);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            s = rs.getString(1) + "." + rs.getString(2);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return s;
        }
        return s;
    }

    public boolean evaluarExistencia(String texto) {
        try {
            callableStatement = connection.prepareCall("{? = CALL existeImagen(?)}");
            callableStatement.registerOutParameter(1, java.sql.Types.BOOLEAN);
            callableStatement.setString(2, texto); 
            callableStatement.execute();
            return callableStatement.getBoolean(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
