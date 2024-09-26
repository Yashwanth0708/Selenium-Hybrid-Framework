package yashwanthgunapatiqa.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import yashwanthgunapatiqa.globalcomponents.BrowserComponents;
import yashwanthgunapatiqa.globalcomponents.Wrapper;
import io.cucumber.java.en.*;

public class StepDefinitions{

	
	public Wrapper wrapper = new Wrapper();
	 
	@BeforeMethod
	 @Given("Launch the website of Ecommerce") public void
	  Launch_the_website_of_Ecommerce() throws IOException { 
			System.out.println("****************");
		 wrapper.LaunchApp(); 
		 }
	 
	@AfterMethod
	@After
	@Then("Close the browser") public void
	 Close_the_browser() throws IOException { 
			System.out.println("---****-----");
		 wrapper.tearDown();
		 }

	@Given("Login with provided username {string} and password {string}")
	public void login_with_provided_username_and_password(String email, String password) {
		wrapper.userLoginDetails(email, password);
	}

	@When("User adds the product {string} from the product catalog to cart")
	public void select_the_product_from_productCatelouge(String productName) {
		wrapper.selectProduct(productName);
	}

	@Then("Navigate to cart section")
	public void navigate_to_cart() throws InterruptedException {
		wrapper.goToCart();
	}

	@Then("Navigate to orders section")
	public void navigate_to_orders() throws InterruptedException {
		wrapper.goToOrders();
	}

	@And("Verify that product {string} is present in the cart")
	public void verify_product_added_in_cart(String productName) {
		boolean isProductInCart = wrapper.checkProdinCartPage(productName);
		Assert.assertTrue(isProductInCart, "The Selected product is not in the Cart page");
	}

	@And("verify the order details of the product {string} and delete the order from orders page")
	public void verify_order_added_in_orders(String productName) throws InterruptedException {

		boolean isProductOrdered = wrapper.getOrderDetails(productName);
		Assert.assertTrue(isProductOrdered, "The Selected product is not in the Cart page");
	}

	@Then("Checkout the product from cart")
	public void checkout_product_from_cart() throws InterruptedException {
		wrapper.checkoutFromCart();
	}

	@And("Select country {string} from checkout page")
	public void select_country_in_checkoutPage(String countryName) {
		wrapper.selectCountryfromCheckOutPage(countryName);
	}

	@Then("Place the order")
	public void place_the_order() throws InterruptedException {
		wrapper.placeTheOrder();
	}

	@And("Verify that {string} is displayed")
	public void verify_confirmation_message(String messsage) throws InterruptedException {
		String validateMsg = wrapper.validateConfirmationMessage();
		Assert.assertEquals(validateMsg, messsage);
	}

	@And("Verify the login error message {string} upon providing incorrect credentials")
	public void verify_login_error_message(String messsage) throws InterruptedException {
		String actualError = wrapper.loginErrorvalidation();
		Assert.assertEquals(actualError, messsage);
	}

	@And("Verify the error message {string} upon removing the product from cart page")
	public void verify_error_message_productRemoval(String messsage) throws InterruptedException {
		String actualmessage = wrapper.removeProductFromCart();
		Assert.assertEquals(actualmessage, messsage);
	}

	@And("verify the error message {string} of not selecting the country")
	public void verify_error_message_notSelectingCountry(String messsage) throws InterruptedException {
		String actualMesg = wrapper.countrySelectionErrorValidation();
		Assert.assertEquals(actualMesg, messsage);
	}
}
