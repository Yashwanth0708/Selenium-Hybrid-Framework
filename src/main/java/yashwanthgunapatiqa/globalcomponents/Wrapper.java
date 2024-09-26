package yashwanthgunapatiqa.globalcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import yashwanthgunapatiqa.pages.CartPage;
import yashwanthgunapatiqa.pages.CheckOutPage;
import yashwanthgunapatiqa.pages.ConfirmationPage;
import yashwanthgunapatiqa.pages.LandingPage;
import yashwanthgunapatiqa.pages.OrdersPage;
import yashwanthgunapatiqa.pages.ProductCatalogue;

public class Wrapper extends BrowserComponents {


    public void initializeBrowser() throws IOException {
        setupBrowser();  // Initialize WebDriver and other properties
    }
    
    // LaunchApp method to setup browser and navigate to the website
    public void LaunchApp() throws IOException {
        // Ensure that the WebDriver is initialized properly
        if (driver == null) {
            initializeBrowser();  // Call setupBrowser() to initialize WebDriver
        }
        launchWebSite();  // Navigate to the application URL
    }

    // LaunchBrowser method annotated with @BeforeMethod to launch the app before each test
    @BeforeMethod
    public void LaunchBrowser() throws IOException {
        try {
            LaunchApp();  // Initialize browser and navigate to URL
        } catch (Exception e) {
            System.out.println("Error launching browser: " + e.getMessage());
            throw new RuntimeException("Failed to launch browser", e);
        }
    }

    // Teardown method to quit the browser after the test is executed
    @AfterMethod
    public void quitBrowser() {
        try {
            tearDown();  // Quit WebDriver safely
        } catch (Exception e) {
            System.out.println("Error quitting browser: " + e.getMessage());
        }
    }

    // tearDown method from BrowserComponents to quit the browser
    public void tearDown() throws IOException {
    	System.out.println("*******");
        quitBrowser();  // Quit the browser session
    }
    
    
    // Login method
    public void userLoginDetails(String emailID, String password) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        LandingPage landingPage = new LandingPage(driver);
        landingPage.userLoginDetails(emailID, password);
    }
    public String loginErrorvalidation() {
        LandingPage loginError = new LandingPage(driver);  // Pass WebDriver to LandingPage
        return loginError.errorValidation();
    }

    // Select product method
    public void selectProduct(String productName) {
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.selectProduct(productName);
    }

    // Go to cart method
    public void goToCart() throws InterruptedException {
        Utils cartOption = new Utils(driver);
        cartOption.navigateToCart();
    }
    
    // Go to Home page method
    public void goToHome() throws InterruptedException {
        Utils homeOption = new Utils(driver);
        homeOption.navigateToHome();
    }
    
    // Go to Orders page method
    public void goToOrders() throws InterruptedException {
        Utils ordersOption = new Utils(driver);
        ordersOption.navigateToOrders();
    }
    
    // SignOut from application
    public void signOutfromAPP() throws InterruptedException {
        Utils signoutOption = new Utils(driver);
        signoutOption.signOutfromApp();
    }

    // Check product in cart method
    public boolean checkProdinCartPage(String productName) {
        CartPage cartPage = new CartPage(driver);
        return cartPage.checkProdinCart(productName);
    }

    // Checkout from cart
    public void checkoutFromCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.checkOutfromCart();
    }
    
    // Remove Product from cart
    public String removeProductFromCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        return cartPage.removeProdFromcart();
    }

    // Select country from checkout page
    public void selectCountryfromCheckOutPage(String countryName) {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.selectCountry(countryName);
    }

    // Place the order
    public void placeTheOrder() throws InterruptedException {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.placeOrder();
    }
    // Place the order
    public String countrySelectionErrorValidation() throws InterruptedException {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
       return checkOutPage.countryErrorvalidation();
    }

    // Validate confirmation message
    public String validateConfirmationMessage() throws InterruptedException {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage.confirmationMessage();
    }
    // Validate Orders Page
    public boolean getOrderDetails(String prodName) throws InterruptedException {
        OrdersPage OrderPage = new OrdersPage(driver);
        return OrderPage.getProductDetails(prodName);
    }
}
