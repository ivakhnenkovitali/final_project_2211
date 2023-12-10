package by.itclass.model.db;

import lombok.experimental.UtilityClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@UtilityClass
public class PropertiesManager {
    public static Properties getProperties(String fileName) {
        var props = new Properties();
        try (var fr = new FileReader(Thread.currentThread()
                .getContextClassLoader().getResource(fileName).getPath())) {
            props.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
