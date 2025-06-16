package RSAcademy.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RSAcademy.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
//	Thank you for the order message
	@FindBy(css="h1[class='hero-primary']")
	WebElement confirmationMessage;
	
	
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
