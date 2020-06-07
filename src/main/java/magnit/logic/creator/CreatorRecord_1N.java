package magnit.logic.creator;

import magnit.config.Config;
import magnit.fileWorker.XMLworker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CreatorRecord_1N implements CreatorRecord {
    //Field
    private List list;
    private int numberN = Integer.parseInt(Config.getProperties().getProperty("value"));
    private final static Logger logger = Logger.getLogger(CreatorRecord_1N.class.getName());

    //Constructor
    public CreatorRecord_1N() {
        addDependence(numberN);
    }

    //Function
    public List getTaskList(){
        return list;
    }

    private void addDependence(int numberN){
        list = new ArrayList();
        for (int i = 1; i <= numberN; i++){
            list.add(new Integer(i));
        }
    }

    //Getter and Setter

}
