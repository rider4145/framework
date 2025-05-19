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


public class Placeorder extends AbstractComponent{

	WebDriver driver;
	public Placeorder(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	 		
			
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']") WebElement selectcountry;
	@FindBy(css=".ta-item:nth-child(3)") WebElement selitem;
	@FindBy(css=".btnn.action__submit.ng-star-inserted")WebElement action;
	
	By countrylist = By.cssSelector(".ta-results");
	
	public void order(String country)
	{
		Actions a = new Actions(driver);
        a.sendKeys(selectcountry, country).build().perform();
        visiElement(countrylist);
        selitem.click();
        action.click();
		
	}
	
}
