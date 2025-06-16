package RSAcademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RSAcademy.TestComponents.BaseTest;
import RSAcademy.pageObjectModel.CartPage;
import RSAcademy.pageObjectModel.CheckOutPage;
import RSAcademy.pageObjectModel.ConfirmationPage;
import RSAcademy.pageObjectModel.LandingPage;
import RSAcademy.pageObjectModel.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage; 
	ProductCatalogue productCatalogue;
	ConfirmationPage cnfmpg;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();		   //launchApplication() is taken from BaseTest class 	
	}
	
	@Given("^Logged in with userEmail (.+) and password (.+)$")          //used regular expression so that username and password should be generic not hardcoaded. For that reg expression is used by adding ^before starting and $at last
	public void logged_in_inserting_userEmail_and_password(String userEmail, String password) {
		productCatalogue = landingPage.loginApplication(userEmail,password);
		System.out.println("LoggedIn Correctly");
	}

	 @When("^I add the (.+) from Cart$")
	 public void adding_list_of_product_to_Cart(String productName) {
		List<WebElement> products =	 productCatalogue.getProductList();	
		System.out.println("List Of products are:-");
		productCatalogue.getAllProductsByName();		
		System.out.println("Selected Product is:- ");
		productCatalogue.getProductByName(productName);
//		finding the product based on product name and add product to cart
		productCatalogue.addProductToCart(productName);
		System.out.println("Added product to Cart");			
	 }
	 
	 @When("^Checkout (.+) and submit the Order$")
	 public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		 CartPage cartPage = productCatalogue.goToCartPage();
			System.out.println("Go to cart page");
			Thread.sleep(3000);			
			Boolean match = cartPage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			System.out.println("Verify if the porduct is displayed");		
			CheckOutPage checkOutPge = cartPage.goToCheckOut();
			checkOutPge.selectCountry("India");
			System.out.println("India selected");			
			ConfirmationPage cnfmpg = checkOutPge.submitOrder();
			System.out.println("Place order");
	 }
//	 "THANKYOU FOR THE ORDER."
	  @Then("{string} message is displayed on Confirmation page")
	  public void message_is_displayed_on_confirmation_page(String string) {
		  String confirmationMessage = cnfmpg.getConfirmationMessage();
			Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));	
			System.out.println(confirmationMessage);
	  }
	  
	  @Then("{string} message is displayed")
	    public void something_message_is_displayed(String strArg1) throws Throwable {
	   
	    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
	    	driver.close();
	    }	
	
}
