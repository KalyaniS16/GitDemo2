package RSAcademy.abstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//products list of orders
	@FindBy(css="tr td:nth-child(3)")
	public List<WebElement> products;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = products.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;		
	}
	
}

