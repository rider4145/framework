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

public class Orderspage extends AbstractComponent{

	WebDriver driver;
	public Orderspage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button") WebElement submit;
	@FindBy(xpath="//tbody/tr[2]/td[2]") List<WebElement> product;		// find list of products using xpath
	@FindBy(css="tr td:nth-child(3)") List<WebElement> product1;		// find list of products using css
    @FindBy(xpath = "//table/tbody/tr[1]/th")List<WebElement> orderids;
	
	public Boolean verifyorders(String pr, String orderid) 
	{
		Boolean cond =product.stream().anyMatch(s->s.getText().equalsIgnoreCase(pr));
		Boolean cond1 = orderids.stream().anyMatch(s1->s1.getText().equalsIgnoreCase(orderid));
		
		if(cond && cond1)
		{
			return true;
		}
		else
		{	
			return false;
		}
	}
	
}
