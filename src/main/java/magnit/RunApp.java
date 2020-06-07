package magnit;

import magnit.dao.OperationTable;
import magnit.dao.OperationTableTEST;
import magnit.fileWorker.FileWorker;
import magnit.logic.creator.CreatorRecord;
import magnit.logic.creator.CreatorRecord_1N;
import magnit.fileWorker.XMLworker;

import java.sql.SQLException;
import java.util.logging.Logger;

public class RunApp {
    //Field
    private final static Logger logger = Logger.getLogger(RunApp.class.getName());

    private static CreatorRecord creatorRecord = new CreatorRecord_1N();
    private static OperationTable operationTable = new OperationTableTEST();
    private static FileWorker fileWorker = new XMLworker();


    //Function
    public static void main(String[] args) throws SQLException {

        double start = System.nanoTime();

        operationTable.deleteFromDB();
        operationTable.insertToDB(creatorRecord.getTaskList());
        fileWorker.write(operationTable.selectFromDB());
        fileWorker.transformation();
        int sum = fileWorker.parseXML();
        System.out.println("Sum all field: " + sum);

        double finish = System.nanoTime();
        double time = (finish - start)/1000000000;
        logger.info("WORK TIME: " + time);






    }
}
