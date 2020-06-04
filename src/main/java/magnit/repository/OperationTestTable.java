package magnit.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationTestTable {
    //Field
    private final String SELECT = "SELECT * FROM TEST;";
    private final String INSERT = "INSERT INTO TEST (FIELD) VALUES (?);";
    private final String DELETE = "DELETE FROM TEST;";


    //Constructor

    //Function
    public void insertOneToDB (Connection connection, int i) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setInt(1, i);
        statement.executeUpdate();
        statement.close();
    }

    public void insertManyToDB (Connection connection, int numberN) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT);
        for (int i = 1; i <= numberN; i++){
            statement.setInt(1, i);
            statement.executeUpdate();
        }
        statement.close();
    }

    public ResultSet selectFromDB (Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(SELECT);
    }

    public void deleteFromDB (Connection connection) throws SQLException {
        connection.createStatement().executeUpdate(DELETE);
    }

    //Getter and Setter
}
