package magnit.dao;

import magnit.fileWorker.XMLworker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OperationTableTEST implements OperationTable {
    //Field
    private final String SELECT = "SELECT * FROM TEST;";
    private final String DELETE = "DELETE FROM TEST;";
    private static Connection connection = ConnectionDB.getConnection();
    private final static Logger logger = Logger.getLogger(OperationTableTEST.class.getName());

    //Constructor

    //Function

    public List selectFromDB() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT);
        List list = new ArrayList();
        while (resultSet.next()){
            list.add(resultSet.getObject("FIELD"));
        }
        resultSet.close();
        logger.info("GET RESULT FROM DATABASE: " + list.size());
        return list;
    }

    public void deleteFromDB() throws SQLException {
        connection.createStatement().executeUpdate(DELETE);
        logger.info("TABLE CLEARED");
    }

    public void insertToDB (List list) throws SQLException {
        Statement statement = connection.createStatement();
        for (int i = 0; i < list.size(); i++){
            statement.addBatch("INSERT INTO TEST (FIELD) VALUES ('"+ i + "');");

        }
        statement.executeBatch();
        logger.info("SEND RESULT TO DATABASE: " + list.size());
        statement.close();
    }

    //Getter and Setter
}
