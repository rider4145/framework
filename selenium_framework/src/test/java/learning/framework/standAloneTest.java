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

import framework.pageobjects.Loginpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
//		System.setProperty("WebDriver.chrome.driver", "C://Users//Sudha//Downloads//rahul//chromedriver//chromedriver.exe");	// Invoke chromdriver from local storage
		WebDriverManager.chromedriver().setup();					// invoking the chromedriver using WebDrivermanager Dependency
		WebDriver driver = new ChromeDriver();						// create an object for chromedriver
		
		Loginpage l = new Loginpage(driver);						// creating an object to call the constructor method in loginpage
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		//implict wait
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		
		driver.findElement(By.id("userEmail")).sendKeys("ajith_may_01@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ajith@1234");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));			// wait until the all the elements are loaded
		List<WebElement> product = driver.findElements(By.cssSelector(".col-lg-4.col-md-6.col-sm-10.offset-md-0.offset-sm-1.mb-3.ng-star-inserted"));
		
		String pr = "ADIDAS ORIGINAL";
		
		WebElement name = product.stream().filter(prod->prod.findElement(By.cssSelector("b")).getText().equals(pr)).findFirst().orElse(null); //filter the element based using streams
		name.findElement(By.cssSelector(".card-body button:last-of-type")).click();	// Finding element in specific webelement
		
		// To handle the toast container or toaster container
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));			// wait until the toast container is loaded
        
        //ng-animating -> this is the class name of the disappearing element. Real time get the element details from developer
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        
        driver.findElement(By.cssSelector(".btn.btn-custom")).click();
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        List<WebElement> cartlist = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean cond =cartlist.stream().anyMatch(s->s.getText().equalsIgnoreCase(pr));
        Thread.sleep(2000);
        Assert.assertTrue(cond);
        
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
        
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
        
        String actual = "Thankyou for the order.";
        String success = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(actual.equalsIgnoreCase(success));
        
	}

}
