package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.AbstractComponent;


public class Loginpage extends AbstractComponent{

	WebDriver driver;
	public Loginpage(WebDriver driver)
	{
		//using super keyword we can send the data from the child class to parent class by crearting a constructor in parent class
		//super constructor has to be defined for all child class in constructor
		super(driver);
		this.driver = driver;
		// the code will initialize driver and the locators run the test using this driver
		// we need to pass 2 elements in initalize elements. 1-> driver, 2-> "this" keyword which will refer to current class driver
		PageFactory.initElements(driver, this);	 		
			
	}
		
	//	WebElement mail = driver.findElement(By.id("userEmail"));


	@FindBy(id="userEmail")WebElement mail;					// the code is constructed using the page object factory and intialized using initelements
	@FindBy(id="userPassword")WebElement pass;
	@FindBy(id="login")WebElement login;
	@FindBy(css= "[class*='flyInOut']")WebElement errormsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public Addtocart loginapp(String usermail,String passw)
	{
		mail.sendKeys(usermail);
		pass.sendKeys(passw);
		login.click();
		Addtocart a = new Addtocart(driver);
		return a;
	}
	
	public String errormsg()
	{
		visiwebElement(errormsg);
		return errormsg.getText();
	}
	
}
