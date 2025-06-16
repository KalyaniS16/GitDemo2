package RSAcademy.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RSAcademy.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
 
	@FindBy(css=".cartSection h3")           //name of product in Cart page
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")          //checkout button in Cart page
	WebElement checkOutEle;
	
	public Boolean verifyProductDisplay(String productName){
		Boolean match= cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() {         //checkout button in Cart page 
		checkOutEle.click();
//		System.out.println(driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).getText());
		return new CheckOutPage(driver);
	}
	
}
