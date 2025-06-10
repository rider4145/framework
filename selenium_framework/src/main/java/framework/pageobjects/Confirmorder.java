package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.abstractcomponents.AbstractComponent;


public class Confirmorder extends AbstractComponent{

	WebDriver driver;
	public Confirmorder(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	 		
			
	}
	@FindBy(css=".hero-primary") WebElement text;
	@FindBy(css="label.ng-star-inserted") WebElement id;
	
	public String confirmorder(String actual) 
	{
        String success = text.getText();
        Assert.assertTrue(actual.equalsIgnoreCase(success));
        System.out.println(success);
        String orderid = id.getText();
        System.out.println("orderid = "+" "+orderid);
        return orderid;
	}
	
	
}
