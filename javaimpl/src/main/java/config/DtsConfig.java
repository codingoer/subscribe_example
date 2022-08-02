package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

public class DtsConfig {

    private static final Logger log = LoggerFactory.getLogger(DtsConfig.class);

    public static final String FILE_NAME = "dtsConfig";

//    public static void main(String[] args) {
//        getConfig();
//        printProperties(getConfig());
//    }

    public static InputStream getInput() {
        URL url = Objects.requireNonNull(DtsConfig.class.getClassLoader().getResource(FILE_NAME));
        String path = url.getPath();
        log.info("DTS Config use path is {}", path);

        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            log.error("Get Dts config file error, {}", e.getMessage(), e);
            e.printStackTrace();
        }

        throw new DtsConfigException();
    }

    /**
     * {@link recordgenerator.Names}
     */
    public static Properties getConfig() {
        Properties properties = new Properties();

        try {
            properties.load(getInput());
        } catch (IOException e) {
            log.error("Get Dts config properties error, {}", e.getMessage(), e);
            throw new DtsConfigException();
        }

        return properties;
    }

    public static void printProperties(Properties properties) {
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String o = (String) enumeration.nextElement();
            log.info("Get properties key is {}", o);
        }
    }
}
