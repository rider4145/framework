package learning.framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.pageobjects.Addtocart;
import framework.pageobjects.Cartpage;
import framework.pageobjects.Confirmorder;
import framework.pageobjects.Loginpage;
import framework.pageobjects.Placeorder;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class standAloneTest2 {

	public static void main(String[] args)
	{
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();									// create an object for chromedriver
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		//implict wait
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));	//explict wait

		String pr = "ADIDAS ORIGINAL";
		String country = "India";
		
		Loginpage l = new Loginpage(driver);									// creating an object to call the constructor method in loginpage	
		l.goTo();																// goto url
		l.loginapp("ajith_may_01@gmail.com", "Ajith@1234");						// calling the method in child class using object keyword
		
		Addtocart a = new Addtocart(driver);
		List<WebElement> items = a.cart();
		a.addcart(pr);
		a.menubar();
		
		Cartpage c = new Cartpage(driver);
		Boolean cond = c.validate(pr);
        Assert.assertTrue(cond);
        c.checkout();
        
        Placeorder po = new Placeorder(driver);
		po.order(country);
        
		Confirmorder co = new Confirmorder(driver);
        String actual = "Thankyou for the order.";
		co.confirmorder(actual);
             
	}

}
