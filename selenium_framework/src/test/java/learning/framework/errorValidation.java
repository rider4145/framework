package learning.framework;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pageobjects.Addtocart;
import framework.pageobjects.Cartpage;
import learning.framework.testcomponents.Base;

public class errorValidation extends Base {

		public String pr = "ADIDAS ORIGINAL";
		public String country = "India";
		public String pr1 = "ZARA COAT 2";
		
		
		@Test(groups= {"Errorvalidation"})
		public void login_1_error() throws IOException
		{
			
			l.loginapp("ajithmay_01@gmail.com", "Ajith@1234");
			Assert.assertEquals("Incorrect email or password.", l.errormsg());
			System.out.println("Invalid username" +" "+ l.errormsg());
			
		}
		
		@Test(groups= {"Errorvalidation"})
		public void login_2_error() throws IOException
		{
			
			l.loginapp("ajith_may_01@gmail.com", "jith");
			Assert.assertEquals("Incorrect email password.", l.errormsg());
			System.out.println("Invalid password" +" "+ l.errormsg());
			
		}
		
		@Test
		public void product_error() throws IOException
		{
			
			Addtocart a = l.loginapp("ajith_may_01@gmail.com", "Ajith@1234");
			List<WebElement> items = a.cart();
			a.addcart(pr);
			Cartpage c = a.menubar();
			Boolean cond = c.validate(pr1);
			Assert.assertFalse(cond);
			System.out.println("Error validation" +" "+ cond); 
			
			
		}
             
}
