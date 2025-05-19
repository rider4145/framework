package framework.pageobjects;

import java.util.List;

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

public class Cartpage extends AbstractComponent{

	WebDriver driver;
	public Cartpage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3") List<WebElement> cartlist;
	@FindBy(css=".totalRow button") WebElement submit;
	
	public Boolean validate(String pr)  
	{
		Boolean cond =cartlist.stream().anyMatch(s->s.getText().equalsIgnoreCase(pr));
        return cond;
	}
	
	public void checkout()
	{
		submit.click();
	}
}
