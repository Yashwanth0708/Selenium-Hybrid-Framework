package yashwanthgunapatiqa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import yashwanthgunapatiqa.globalcomponents.Wrapper;

public class Errorvalidation extends Wrapper {
	
	@Test (groups= {"ErrorHandling"}, retryAnalyzer = yashwanthgunapatiqa.testcomponents.ReExecuteFailedcase.class)
	public void loginErrorValidation() throws IOException {
		
		userLoginDetails("Yash@gmail.com", "Welcome@1");
		String actualError = loginErrorvalidation();
		Assert.assertEquals(actualError, "Incorrect email or password");
		
	
	}
	
	@Test
	public void productRemovalValidation() throws InterruptedException, IOException {
		
		userLoginDetails("Yashr@gmail.com", "Welcome@1");
		selectProduct("ZARA COAT 3");
		goToCart();		
		boolean isProductInCart = checkProdinCartPage("ZARA COAT 3");
		Assert.assertTrue(isProductInCart,"The Selected product is not in the Cart page");	
		String actualmessage = removeProductFromCart();
		Assert.assertEquals(actualmessage, "No Product in Your Cart");
	}

	@Test (groups= {"ErrorHandling"})
	public void checkOutWithOutCountry() throws InterruptedException, IOException {
		
		userLoginDetails("Yashr@gmail.com", "Welcome@1");
		selectProduct("ZARA COAT 3");
		goToCart();		
		boolean isProductInCart = checkProdinCartPage("ZARA COAT 3");
		Assert.assertTrue(isProductInCart,"The Selected product is not in the Cart page");		
		checkoutFromCart();
		placeTheOrder();
		String actualMesg = countrySelectionErrorValidation();
		Assert.assertEquals(actualMesg, "Please Enter Full Shipping Information");
	}
	
}
