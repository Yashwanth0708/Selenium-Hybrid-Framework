package yashwanthgunapatiqa.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yashwanthgunapatiqa.globalcomponents.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import yashwanthgunapatiqa.globalcomponents.Wrapper;

@Test 
public class SubmitOrder_E2E_TestCase extends Wrapper {

	@Test (dataProvider= "getDataforPurchase")
	public void checkOutProduct(HashMap<String,String> input) throws InterruptedException, IOException {
		
		userLoginDetails(input.get("email"), input.get("password"));
		selectProduct(input.get("prodctName"));
		goToCart();		
		boolean isProductInCart = checkProdinCartPage(input.get("prodctName"));
		Assert.assertTrue(isProductInCart,"The Selected product is not in the Cart page");		
		checkoutFromCart();
		selectCountryfromCheckOutPage(input.get("country"));
		placeTheOrder();
		String validateMsg = validateConfirmationMessage();
		Assert.assertEquals(validateMsg, input.get("expctMessage"));
		
		
	}
	@Test(dependsOnMethods = "checkOutProduct", dataProvider= "getDataforOrders")
	public void checkOrderConfirmationofProduct(HashMap<String,String> input) throws InterruptedException, IOException {
		
		userLoginDetails(input.get("email"), input.get("password"));
		goToOrders();
		boolean isProductOrdered = getOrderDetails(input.get("prodctName"));
		Assert.assertTrue(isProductOrdered,"The Selected product is not in the Cart page");
		
		
	}
	
	@DataProvider
	public Object[][] getDataforPurchase() throws IOException {		
		
		JsonUtils mapper = new JsonUtils();
		List<HashMap<String,String>> dataMapper = mapper.getJsonData("PurchaseProductData");
		return new Object[][] {{dataMapper.get(0)},{dataMapper.get(1)},{dataMapper.get(2)}};
		
	}
	@DataProvider
	public Object[][] getDataforOrders() throws IOException {
		
		
		JsonUtils mapper = new JsonUtils();
		List<HashMap<String,String>> dataMapper = mapper.getJsonData("OrderCheckData");
		return new Object[][] {{dataMapper.get(0)},{dataMapper.get(1)},{dataMapper.get(2)}};


	}
	
}
