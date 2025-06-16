package RSAcademy.pageObjectModel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RSAcademy.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
//		Initialization
		super(driver);        //to send driver of ProductCatalogue to AbstractComponent means from childClass to ParentClass
		this.driver = driver;
		PageFactory.initElements(driver, this);     //pageFactory is used wherever there is driver.findElement
	}
	
//	Waiting for particular webElement to display and Listing all the products present in the list
	
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));		
//	List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
//	instead of ABOVE Lines use below @FindBy	
	@FindBy(css=".mb-3")
	List<WebElement> products;	
	
	@FindBy(xpath="//div[@class='card-body']/h5/b")
	List<WebElement> allProductsInList;
	
//	@FindBy(css=".ng-animating")    //product added to cart popup when add to cart button is hit
//	WebElement spinner;	
	
	By productsBy = By.cssSelector(".mb-3");          //list of product
	By addToCart = By.cssSelector(".btn.w-10.rounded");         //add to cart button
	By toastMessage =By.cssSelector("#toast-container");        //toast container //product added to cart popup
	
	By allProducts = By.xpath("//div[@class='card-body']/h5/b");
	
//	Listing all the products present in the list
	public List<WebElement> getProductList(){
		waitingForElementToAppear(productsBy);         //waitingForElementToAppear() is used from AbstractComponent
		return products;				
	}
	
//	printing all the products present in the list
	public List<WebElement> getAllProductsByName(){
		ArrayList<String> listOfProds = new ArrayList<String>();
		for (WebElement allProdsList : allProductsInList) {
			listOfProds.add(allProdsList.getText());
		}
		System.out.println(listOfProds);
		return allProductsInList;		
	}
	
	
//	below 2 methods getProductByName,addProductToCart CODE are transfered from SubmitOrderTest class
//	finding the product based on product name
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);		
		return prod;	
	}
	
//	adding product to cart 
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		System.out.println("ProductCatalogue -> Clicked on the product");
			
		waitingForElementToAppear(toastMessage);
		System.out.println("ToastMessage for Product added:- " +driver.findElement(By.xpath("//div[contains(@class, 'toast-message') and contains(text(), 'Product Added To Cart')]")).getText());
		
//		waitingForElementToDisappear(spinner);
//		System.out.println(driver.findElement(By.cssSelector(".ng-animating")).getText());
		
		// Wait for spinner to disappear safely
		try {
			waitingForElementToDisappear(By.cssSelector(".ng-animating"));
			System.out.println("Spinner disappeared");
		} catch (Exception e) {
			System.out.println("Spinner not found or already gone");
		}
	}
	
	
}
