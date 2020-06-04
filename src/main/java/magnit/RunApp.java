package magnit;

import magnit.repository.ConnectionDB;
import magnit.repository.OperationTestTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunApp {
    //Field
    private static Connection connection = ConnectionDB.getConnection();
    private static int numberN = 30000;
    private static OperationTestTable operationTestTable = new OperationTestTable();

    //Function
    public static void main(String[] args) throws SQLException {

        operationTestTable.deleteFromDB(connection);
        operationTestTable.insertManyToDB(connection, numberN);

        ResultSet resultSet = operationTestTable.selectFromDB(connection);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("FIELD"));
        }



    }
}
