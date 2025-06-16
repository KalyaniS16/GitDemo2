package RSAcademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RSAcademy.pageObjectModel.CartPage;

public class AbstractComponent {   //reusable code should be written in AbstractComponent
	
	WebDriver driver;

	//	Constructor has been created to catch the Super keyword from ChildClass. otherwise using driver wouldn't have been possible
	public AbstractComponent(WebDriver driver) {                   //scope of the driver is inside the method only
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='/cart']")     //cart button 
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='/myorders']")   //orders button
	WebElement ordersHeader;
	
//	waiting for particular Element to appear
	public void waitingForElementToAppear(By findBy) {	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));		
	}
	
//	waiting for particular WebElement to appear
	public void waitingForWebElementToAppear(WebElement findBy) {	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));		
	}
	
//	waiting for particular Element to disappear
//	public void waitingForElementToDisappear(WebElement element) throws InterruptedException {	
//		Thread.sleep(3000);		
//	}
	
	public void waitingForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}



	
//	Goto cart                                      //as this header is common for all the pages, we have added it into AbstractComponent
	public CartPage goToCartPage() {
		cartHeader.click();
		System.out.println("AbstractComponent -> Goto to cart page");
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		ordersHeader.click();
		System.out.println("AbstractComponent ->Goto my orders");
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
		
	}
}
