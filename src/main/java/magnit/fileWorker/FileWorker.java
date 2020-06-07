package magnit.fileWorker;

import java.util.List;

public interface FileWorker {
    void write(List list);
    void transformation();
    int parseXML();
}
