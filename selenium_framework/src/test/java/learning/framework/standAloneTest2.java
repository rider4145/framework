package learning.framework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pageobjects.Addtocart;
import framework.pageobjects.Cartpage;
import framework.pageobjects.Confirmorder;
import framework.pageobjects.Loginpage;
import framework.pageobjects.Orderspage;
import framework.pageobjects.Placeorder;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import learning.framework.testcomponents.Base;

public class standAloneTest2 extends Base {

		public String pr = "ADIDAS ORIGINAL";
		public String country = "India";
		public String orderid ;
		
		// Additemtocart->veriy->checkout->submitorder
		@Test(dataProvider="data", groups ="purchase")						// pass the method name where the data provider is created
//		public void submitorder(String email, String pass, String pr) throws IOException	// fetching the values directly from dataprovider method
		public void submitorder(HashMap<String,String> input) throws IOException	//  fetching the values directly from dataprovider method using hashmap
		{
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();									// create an object for chromedriver	
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));		//implict wait
//		driver.manage().window().maximize();
//		Loginpage l =launchapp();												// it is declared gloablly in the base java class
			
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));	//explict wait

//		String pr = "ADIDAS ORIGINAL";											//defined outside method to access over other methods
//		String country = "India";												//defined outside method to access over other methods
		
		
//		Loginpage l = new Loginpage(driver);									// creating an object to call the constructor method in loginpage	
//		l.goTo();																// goto url
//		Addtocart a = l.loginapp("ajith_may_01@gmail.com", "Ajith@1234");		// calling the method in child class using object keyword
		
//		Addtocart a = l.loginapp(email, pass);									// passing the values normally using dataprovider
		
		Addtocart a = l.loginapp(input.get("email"), input.get("pass"));		// passing values using hashmap
		
//		Addtocart a = new Addtocart(driver);									// it is initalized in the loginapp()
		List<WebElement> items = a.cart();
//		a.addcart(pr);															// passing values normally using dataprovider
		a.addcart(input.get("product"));										// passing values using hashmap
		Cartpage c = a.menubar();
		
//		Cartpage c = new Cartpage(driver);										// it is initalized in menubar()
//		Boolean cond = c.validate(pr);											// passing values normally using dataprovider
		Boolean cond = c.validate(input.get("product"));						// passing values using hashmap
        Assert.assertTrue(cond);
        Placeorder po = c.checkout();
        
//      Placeorder po = new Placeorder(driver);									// it is  initalized in checkout()
		po.order(country);
        
		Confirmorder co = new Confirmorder(driver);
        String actual = "Thankyou for the order.";
		orderid = co.confirmorder(actual);
		}
		
		
		// To verify the Product is displaying in the orders page
		@Test(dependsOnMethods= {"submitorder()"})
		public void orderHistory()
		{
			Addtocart a = l.loginapp("ajith_may_01@gmail.com", "Ajith@1234");
			Orderspage op = a.orderspage();
			Assert.assertTrue(op.verifyorders(pr,orderid));
			
		}

		// using Hashmap 
/*		@DataProvider							// set of values is stored in this method and passed over other methods by calling this data provider
		public Object[][] data()
		{	
			HashMap<Object,Object> map = new HashMap<>();
			map.put("email", "ajith_may_01@gmail.com");
			map.put("pass", "Ajith@1234");
			map.put("product", "ADIDAS ORIGINAL");
			
			HashMap<Object,Object> map1 = new HashMap<>();				// defining multiple hashmap to send another set of values
			map1.put("email", "ajith_may_01@gmail.com");
			map1.put("pass", "Ajith@1234");
			map1.put("product", "ZARA COAT 3");
			
			return new Object[][] {{map},{map1}};						// pass the values using hashmap
		}
*/
		
		
		// using normal dataprovider set the values
/*		@DataProvider							
		public Object[][] data()
		{
			
			return new Object[][] {{"ajith_may_01@gmail.com","Ajith@1234","ADIDAS ORIGINAL"},{"ajith_may_01@gmail.com","Ajith@1234","ZARA COAT 3"}};
		}
*/        
		
		// using json file in the dataprovider class
		@DataProvider							
		public Object[][] data() throws IOException
		{
			String json_path = System.getProperty("user.dir") +"\\src\\test\\java\\learning\\framework\\data\\Purchaseorder.json";
			 List<HashMap<String, String>> data = getJsonData(json_path);
			// we are inheriting the base class, so the getJsonData() method will be written inside
			return new Object[][] {{data.get(0)},{data.get(1)}};
			// retrive the data from list of hashmap using data.get(index).In this case index will 0 and 1, becoz we have 2 hashmaps
		}
        
		
		
}
