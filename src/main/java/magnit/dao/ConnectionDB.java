package magnit.dao;

import magnit.config.Config;
import magnit.fileWorker.XMLworker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    //Field
    private static String URL;
    private static String login;
    private static String password;

    private final static Logger logger = Logger.getLogger(ConnectionDB.class.getName());

    //Constructor
    public ConnectionDB() {
    }

    //Function
    public static Connection getConnection (){
        getProperties();
        try {
            Connection connection = DriverManager.getConnection(URL, login, password);
            return connection;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, null, e);
            return null;
        }
    }

    private static void getProperties(){
        URL = Config.getProperties().getProperty("url");
        login = Config.getProperties().getProperty("login");
        password = Config.getProperties().getProperty("password");
    }


    //Getter and Setter
}
