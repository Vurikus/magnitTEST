package magnit.repository;

import org.postgresql.Driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.postgresql.Driver.*;

public class ConnectionDB {
    //Field
    private static String URL;
    private static String login;
    private static String password;

    //Constructor
    public ConnectionDB() {
    }

    //Function
    private static void getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/dataBase.properties"));
            URL = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection (){
        getProperties();
        try {
            Connection connection = DriverManager.getConnection(URL, login, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Getter and Setter
}
