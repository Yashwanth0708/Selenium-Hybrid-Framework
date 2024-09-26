@PurchaseOrder
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file

  Background:
    Given Launch the website of Ecommerce 

  @checkoutproduct
  Scenario Outline: Check out a product testing end to end test scenario
    Given Login with provided username "<username>" and password "<password>"
    When User adds the product "<productName>" from the product catalog to cart
    Then Navigate to cart section
    And Verify that product "<productName>" is present in the cart
    Then Checkout the product from cart
    And Select country "<country>" from checkout page
    Then Place the order
    And Verify that "<confirmationMessage>" is displayed

    Examples: 
      | username          | password    | productName     | country | confirmationMessage        |
      | Yashr@gmail.com   | Welcome@1   | IPHONE 13 PRO   | india   | THANKYOU FOR THE ORDER.    |
      | nishu@gmail.com   | Welcome@1   | ADIDAS ORIGINAL | india   | THANKYOU FOR THE ORDER.    |
      | avanthi@gmail.com | Welcome@1   | ZARA COAT 3     | india   | THANKYOU FOR THE ORDER.    |
      
     
  @CheckOrderInformation
  Scenario Outline: Check the order details of the purchased product from orders page
    Given Login with provided username "<username>" and password "<password>"
    Then Navigate to orders section
    And verify the order details of the product "<productName>" and delete the order from orders page

    Examples: 
      | username          | password    | productName     |
      | Yashr@gmail.com   | Welcome@1   | IPHONE 13 PRO   | 
      | nishu@gmail.com   | Welcome@1   | ADIDAS ORIGINAL | 
      | avanthi@gmail.com | Welcome@1   | ZARA COAT 3     | 