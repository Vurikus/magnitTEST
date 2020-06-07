package magnit.dao;

import java.sql.SQLException;
import java.util.List;

public interface OperationTable {
    void insertToDB(List list) throws SQLException;
    List selectFromDB() throws SQLException;
    void deleteFromDB() throws SQLException;

}
