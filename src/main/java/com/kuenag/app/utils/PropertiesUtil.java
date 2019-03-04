package com.kuenag.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtil {

    static Logger logger = Logger.getLogger(PropertiesUtil.class.getName());

    public static Properties defaultProps = new Properties();
    static {
        try {
            FileInputStream in = new FileInputStream("src/main/resources/application.properties");
            defaultProps.load(in);
            in.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE ,String.format("Error reading property file: %s",e.getLocalizedMessage()));
        }
    }
    public static String getProperty(String key) {
        return defaultProps.getProperty(key);
    }
}