package magnit.config;

import magnit.fileWorker.XMLworker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

    private static Properties properties = new Properties();
    private final static Logger logger = Logger.getLogger(Config.class.getName());

    public Config() {
    }

    public static Properties getProperties(){
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return properties;

    }

}
