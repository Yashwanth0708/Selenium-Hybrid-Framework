@ErrorValidation
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file
  
  Background:
    Given Launch the website of Ecommerce 
    
  @InvalidLoginCredentials
  Scenario Outline: Check out a product testing end to end test scenario
    Given Login with provided username "<username>" and password "<password>"
    And Verify the login error message "<errorMessage>" upon providing incorrect credentials


    Examples: 
      | username         | password  | errorMessage        	       	|
      | Yashrr@gmail.com | Welcome@1 | Incorrect email or password.  |
      | Yashrr@gmail.com | Welc@1    | Incorrect email or password.  |

  @ProductRemovalValidation
  Scenario Outline: Check out a product testing end to end test scenario
    Given Login with provided username "<username>" and password "<password>"
    When User adds the product "<productName>" from the product catalog to cart
    Then Navigate to cart section
    And Verify that product "<productName>" is present in the cart
 		And Verify the error message "<errorMessage>" upon removing the product from cart page

    Examples: 
      | username         | password  | productName      | errorMessage        	  |
      | Yashrr@gmail.com | Welcome@1 | ZARA COAT 3      | No Product in Your Cart |
      | nishu@gmail.com  | Welcome@1 | ADIDAS ORIGINAL  | No Product in Your Cart |
      
  @PlaceOrderWithoutCountry
  Scenario Outline: Try to Check out the product without selecting the country on checkout page
    Given Login with provided username "<username>" and password "<password>"
    When User adds the product "<productName>" from the product catalog to cart
    Then Navigate to cart section
    And Verify that product "<productName>" is present in the cart
    Then Checkout the product from cart
    Then Place the order
    And verify the error message "<errorMessage>" of not selecting the country

    Examples: 
      | username          | password  | productName      | errorMessage        	 								  |
      | avanthi@gmail.com | Welcome@1 | ZARA COAT 3      | Please Enter Full Shipping Information |

      
      