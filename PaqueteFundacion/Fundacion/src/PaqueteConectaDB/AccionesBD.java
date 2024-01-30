package PaqueteConectaDB;

import java.sql.Connection;
import java.sql.DriverManager;


public class AccionesBD {
    public AccionesBD(){}
    public static Connection establecerConexion () {
        Connection conectar = null;
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/FundacionDB", "postgres", "Go37Rd42");

        } catch (Exception e) {
            System.out.println("La conexión ha fallado");
        }
        return conectar;
    }
}
