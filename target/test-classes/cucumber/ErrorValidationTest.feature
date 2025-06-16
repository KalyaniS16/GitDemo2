
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on Ecommerce Page
   When Logged in with userEmail <userEmail> and password <password>
   Then "Incorrect email or password." message is displayed

     Examples: 
      | userEmail           | password    | productName
      | kalyanis@gmail.com  | Welcome101  | ZARA COAT 3
