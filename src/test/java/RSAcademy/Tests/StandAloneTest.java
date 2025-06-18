package RSAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RSAcademy.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		LandingPage landingPage = new LandingPage(driver);
		
		String productName = "ADIDAS ORIGINAL";
//		String productName2 = "ZARA COAT 3";
		
		driver.findElement(By.id("userEmail")).sendKeys("kalyanis@gmail.com");		
		driver.findElement(By.id("userPassword")).sendKeys("Welcome01");
		driver.findElement(By.id("login")).click();
		System.out.println("Successfully logged In");
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("!!!!!&&&&&&&&&&&&&&&&&!!!!!!");
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));		
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);		
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		System.out.println("Clicked on the items");
	
		// to create css from id, #id thus, #toast-container
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		System.out.println(driver.findElement(By.cssSelector(".ng-animating")).getText());
		
		driver.findElement(By.cssSelector("button[routerlink*='/cart']")).click();
		System.out.println("Added to cart");
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
//		System.out.println(driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).getText());
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
//		to select 2nd item in the searched option 
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
//		OR
//		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		System.out.println("Selected India from Counrty list");
		
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		System.out.println("Order Placed");
		
		String confirmation = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		
		Assert.assertTrue(confirmation.equalsIgnoreCase("Thankyou for the order."));
		
	}
}
