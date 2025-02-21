package configs;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Reporter;

public class ConfigReader {
	String path = "./Config/configuration.properties";
	private Properties properties;
	public ConfigReader() {
		properties = new Properties();
		File file = new File(path);
		try {
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
		}
		catch(Exception e) {
			Reporter.log("Issues with loading the config file for reading config values"+e.getMessage(),true);
			
		}
		Reporter.log("Configuration file loaded in memory for reading properties",true);
	}

	public String readUrl() {
		return properties.getProperty("appUrl");
	}
}
