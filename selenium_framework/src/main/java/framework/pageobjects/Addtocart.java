package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import framework.abstractcomponents.AbstractComponent;

public class Addtocart extends AbstractComponent{

	WebDriver driver;
	public Addtocart(WebDriver driver)
	{	
		super(driver);						//super constructor has to be defined for all child class in constructor
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
		
	@FindBy(css=".mb-3") List<WebElement> product;			// find all the elements but there is no wait time
	By pro = By.cssSelector(".mb-3");
	By add = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");
	By disappear = By.cssSelector(".ng-animating");
	
	
	public List<WebElement> cart()
	{
		visiElement(pro);						// wait until all the elements is appeared
		return product;
	}
	
	public WebElement getproduct(String pr)					// someone issue in this method because of streams
	{
		WebElement item = cart().stream().filter(prod->prod.findElement(By.cssSelector("b")).getText().equals(pr)).findFirst().orElse(null);
		//  By calling cart method instead of product webelement we will wait until all the elements are appeared in screen
		return item;
	}
	
	public void addcart(String pr)
	{
		WebElement prod = getproduct(pr);
		prod.findElement(add).click();
		visiElement(toast);	
		inviElement(disappear);
		
	}
	
}
