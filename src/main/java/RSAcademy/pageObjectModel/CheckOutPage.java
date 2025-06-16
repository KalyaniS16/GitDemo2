package RSAcademy.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RSAcademy.abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);	
	}
	
	@FindBy(css="input[placeholder='Select Country']")         //SELECT COUNTRY INPUT BOX
	private WebElement country;
		
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")      //selecting INDIA from dropdown
	private WebElement selectCountry;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")      //place order button
	private WebElement submit;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();	
		
		waitingForElementToAppear(results);
		
//		to select 2nd item in the searched option 		
		selectCountry.click();
		System.out.println("Selected India from Country list");			
	}
	
	public ConfirmationPage submitOrder() {		
		submit.click();
		return new ConfirmationPage(driver);

	}
}
