	package RSAcademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RSAcademy.TestComponents.BaseTest;
import RSAcademy.abstractComponents.OrdersPage;
import RSAcademy.pageObjectModel.CartPage;
import RSAcademy.pageObjectModel.CheckOutPage;
import RSAcademy.pageObjectModel.ConfirmationPage;
import RSAcademy.pageObjectModel.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {	

		//below code got shifted to BaseTest class so that the tests could run in diff browsers		
//		public void SubmitOrder(String userEmail,String userPassword, String productName) throws IOException, InterruptedException {		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();	
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		

		//		creating object of LandingPage class to access all the methods of it
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();		

		//		this object creation can be eliminated by moving it to BaseTest class
//		LandingPage landingPage= launchApplication();

		//		below code is moved to ErrorValidationsTest class
//		ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);    //this method is from landing page class		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userEmail"), input.get("userPassword"));
		System.out.println("LoggedIn Correctly");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("%%%%%%%%%%%%%%%%%");
		
//		creating object of ProductCatalogue class 
		List<WebElement> products =	 productCatalogue.getProductList();	
		System.out.println("List Of products are:-");
		productCatalogue.getAllProductsByName();
		
		System.out.println("Selected Product is:- ");
		productCatalogue.getProductByName(productName);
		
//		finding the product based on product name and add product to cart
		productCatalogue.addProductToCart(input.get("product"));
		System.out.println("Added product to Cart");
		
		CartPage cartPage = productCatalogue.goToCartPage();
		System.out.println("Go to cart page");
		Thread.sleep(3000);
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		System.out.println("Verify if the porduct is displayed");
		
		CheckOutPage checkOutPge = cartPage.goToCheckOut();
		checkOutPge.selectCountry("India");
		System.out.println("India selected");
		
		ConfirmationPage cnfmpg = checkOutPge.submitOrder();
		System.out.println("User has Placed the order");
		String confirmationMessage = cnfmpg.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));	
		System.out.println(confirmationMessage);
//		used below close method in BaseTest in after method
//		driver.close();		
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})       //depends on above method
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("kalyanis@gmail.com", "Welcome01");    //this method is from landing page class
		OrdersPage ordersPage= productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		System.out.println("Name of products in Order History has been verified");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{	
//		Below code is moved to PurchaseOrder.json file bcoz, if the users data is more, then json format file makes it easier to track 
//		Hashmap has been created bcoz there may be multiple data of objects, To make it simplier we can use Hashmap	
//		HashMap<String, String> user1Details = new HashMap<String, String>();
//		user1Details.put("userEmail", "kalyanis@gmail.com");
//		user1Details.put("userPassword", "Welcome01");
//		user1Details.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> user2Details = new HashMap<String, String>();
//		user2Details.put("userEmail", "prashantb@gmail.com");
//		user2Details.put("userPassword", "Welcome01");
//		user2Details.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RSAcademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
//		return new Object[][] {{"kalyanis@gmail.com","Welcome01","ADIDAS ORIGINAL"},{"prashantb@gmail.com", "Welcome01","ZARA COAT 3"}};	
	}
}
