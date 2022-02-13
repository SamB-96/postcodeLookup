package dbSetup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFile {

    /**
     * @param filepath - String - filepath to .properties file with delivery data
     * @return Properties - returns properties from the given file
     */
    public static Properties readFile(String filepath) {
        Properties prop = null;
        ClassLoader classLoader = ReadFile.class.getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(filepath)) {
                prop = new Properties();
                prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
}
