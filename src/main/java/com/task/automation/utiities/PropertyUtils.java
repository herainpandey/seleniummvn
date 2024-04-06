package com.task.automation.utiities;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

  // Singalton
    private static PropertyUtils propertyUtils = null;

    private PropertyUtils() throws IOException {
        properties = PropertyUtils.propertyLoader("config.properties");
    }

    public static PropertyUtils getInstance() throws IOException {
        if(propertyUtils == null){
            propertyUtils = new PropertyUtils();
        }
        return  propertyUtils;
    }

    private Properties properties;

    private static Properties  propertyLoader(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream stream = PropertyUtils.class.getResourceAsStream("/config/"+fileName);
        properties.load(stream);
        return properties;
    }

    public String getProperty(String key){
        return properties.getProperty(key).toString();
    }


}
