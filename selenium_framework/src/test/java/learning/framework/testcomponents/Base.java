package learning.framework.testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public void initalize() throws IOException
	{
		// properties class is used to read the global properties
		Properties prop = new Properties();
		
		// to convert file to input stream object
		FileInputStream files = new FileInputStream("C:\\Users\\Sudha\\Downloads\\rahul\\selenium_framework\\src\\main\\java\\framework\\resources\\GlobalData.properties"); 
		
		// to reduce the properties file path
		FileInputStream files1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\framework\\resources\\GlobalData.properties");
		
		prop.load(files);				// once the property is set, we can get the data from global properties
		
		String name = prop.getProperty("browser");
		
		if(name.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();									

		}
		else if(name.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver = new FirefoxDriver();											
		}
		else if(name.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			WebDriver driver = new EdgeDriver();										
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		
		driver.manage().window().maximize();

		
	}
}
