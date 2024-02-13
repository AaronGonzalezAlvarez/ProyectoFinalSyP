package coneccion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Coneccion {
    
    public static final String BD = "hundirlaflota";
    public static final String URL = "jdbc:mysql://localhost:3306/" + BD +"?autoReconnet=true&useSSL=false";
    public static final String usuario = "root";
    public static final String passwd = "usuario";
    
    public Connection getConnection() {
        Connection conexion = null;
        //conexion
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, passwd);
            System.out.println("Todo ok");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return conexion;
    }
    
}
