
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive test of submitting the order
  
    Given Logged in with userEmail <userEmail> and password <password>
    When I add the <productName> from Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." is displayed on Confirmation page

    Examples: 
      | userEmail           | password   | productName
      | kalyanis@gmail.com  | Welcome01  | ZARA COAT 3
