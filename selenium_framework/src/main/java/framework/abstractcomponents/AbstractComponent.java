package framework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.pageobjects.Cartpage;
import framework.pageobjects.Orderspage;

public class AbstractComponent 
{
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']") WebElement carticon;
	@FindBy(css="[routerlink*='myorders']") WebElement ordersicon;
	
	public void visiElement(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void inviElement(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	public Cartpage menubar()
	{
		carticon.click();
		Cartpage c = new Cartpage(driver);
		return c;	
	}
	public void visiwebElement(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public Orderspage orderspage()
	{
		ordersicon.click();	
		Orderspage op = new Orderspage(driver);
		return op;
	}
	
}
