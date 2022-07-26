package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private static  final Connector CONNECTOR = new Connector();

    private Connector(){}

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "lemoor@mail.ru";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/vetclinic";

    private static Connection connection;
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

}
