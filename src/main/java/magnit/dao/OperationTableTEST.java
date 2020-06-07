package magnit.dao;

import magnit.fileWorker.XMLworker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OperationTableTEST implements OperationTable {
    //Field
    private final String SELECT = "SELECT * FROM TEST;";
    private final String INSERT = "INSERT INTO TEST (FIELD) VALUES (?);";
    private final String DELETE = "DELETE FROM TEST;";
    private static Connection connection = ConnectionDB.getConnection();
    private final static Logger logger = Logger.getLogger(OperationTableTEST.class.getName());

    //Constructor

    //Function

    public void insertToDB(List list) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT);
        for (int i = 0; i < list.size(); i++){
            statement.setObject(1, list.get(i));
            //statement.setInt(1, list.get(i));
            statement.executeUpdate();
        }
        logger.info("SEND RESULT TO DATABASE: " + list.size());
        statement.close();
    }

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


    //Getter and Setter
}
