package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance;  // Singleton instance
    private Properties properties;
    
    // Path to the configuration file
    private final String configFilePath = "src/test/resources/config.properties"; // Adjust this path if needed
    
    // Private constructor to prevent direct instantiation
    private ConfigReader() 
    {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(configFilePath)) 
        {
            properties.load(inputStream);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + configFilePath);
        }
    }

    // Public method to get the singleton instance
    public static ConfigReader getInstance() 
    {
        if (instance == null) {
            synchronized (ConfigReader.class) 
            {
                if (instance == null) 
                {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    // Method to get a property value by key
    public String getProperty(String key) 
    {
        return properties.getProperty(key);
    }

    // Method to get a property value by key with default value
    public String getProperty(String key, String defaultValue) 
    {
        return properties.getProperty(key, defaultValue);
    }

    // Method to get the WebDriver browser type from the config file
    public String getBrowser() 
    {
        return getProperty("browser", "chrome");  // Default to chrome if not specified
    }

    // Method to get the customer URL (you can add more specific methods for your config values)
    public String getCustomerURL() 
    {
        return getProperty("customerURL", "https://default.url.com");
    }

    // Add other getters as needed for your config properties
}
