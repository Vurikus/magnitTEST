package magnit;

import magnit.molder.XMLmolder;
import magnit.repository.ConnectionDB;
import magnit.repository.OperationTestTable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunApp {
    //Field
    private static int numberN = 7;
    private static OperationTestTable operationTestTable = new OperationTestTable();
    private static XMLmolder molder = new XMLmolder();


    //Function
    public static void main(String[] args) throws SQLException, TransformerException, ParserConfigurationException {

        operationTestTable.deleteFromDB();
        operationTestTable.insertManyToDB(numberN);
        int [] numbers = operationTestTable.selectAllNumbersFromDB(numberN);
        molder.writeXML(numbers);





    }
}
