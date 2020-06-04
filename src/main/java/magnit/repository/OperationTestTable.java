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
    private static Connection connection = ConnectionDB.getConnection();


    //Constructor

    //Function
    public void insertOneToDB (int i) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setInt(1, i);
        statement.executeUpdate();
        statement.close();
    }

    public void insertManyToDB (int numberN) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT);
        for (int i = 1; i <= numberN; i++){
            statement.setInt(1, i);
            statement.executeUpdate();
        }
        statement.close();
    }

    public ResultSet selectFromDB () throws SQLException {
        return connection.createStatement().executeQuery(SELECT);
    }

    public int [] selectAllNumbersFromDB (int numberN) throws SQLException {
        ResultSet resultSet = selectFromDB();
        int [] numbers = new int [numberN];
        int i = 0;
        while (resultSet.next()){
            numbers [i] = (resultSet.getInt("FIELD"));
            i++;
        }
        return numbers;
    }

    public void deleteFromDB () throws SQLException {
        connection.createStatement().executeUpdate(DELETE);
    }

    //Getter and Setter
}
