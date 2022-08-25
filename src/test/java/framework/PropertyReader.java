package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final String defaultValuesPropertyPath = "src/test/resources/create_user_data.properties";

    public static String getTestData(String property, String defaultValue) {
        String propertyString;
        try {
            propertyString = System.getProperty(property, defaultValue);
        } catch (Exception e) {
            throw new PropertyIsNotSpecifiedProperly(property, e);
        }
        return propertyString;
    }

    public static String getUserDefaultValues(String propertyName) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(defaultValuesPropertyPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(propertyName);
    }
}
