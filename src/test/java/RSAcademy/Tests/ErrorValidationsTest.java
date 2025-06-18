package RSAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RSAcademy.TestComponents.BaseTest;
import RSAcademy.TestComponents.Retry;
import RSAcademy.pageObjectModel.CartPage;
import RSAcademy.pageObjectModel.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
//	All the negative test cases are included in this class
	@Test(groups="ErrorHandlingGroup", retryAnalyzer = Retry.class)         //this retryAnalyzer is used to rerun the failed test more than 1 time. Please check Retry class once.
	public  void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";		
		ProductCatalogue productCatalogue = landingPage.loginApplication("kalyanis@gmail.com", "Welcome");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());;
	}
	
	@Test	
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";		
		ProductCatalogue productCatalogue = landingPage.loginApplication("prashantb@gmail.com", "Welcome01");		
		List<WebElement> products =	 productCatalogue.getProductList();		
		productCatalogue.addProductToCart(productName);			
		CartPage cartPage = productCatalogue.goToCartPage();		
		Boolean match = cartPage.verifyProductDisplay("ADIDAS"); 
		Assert.assertFalse(match);
		System.out.println("*********CHECK*************");
	}
}
