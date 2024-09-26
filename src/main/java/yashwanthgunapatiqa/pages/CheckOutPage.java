package yashwanthgunapatiqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class CheckOutPage extends Utils {
	
	private WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='form-group']/input")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@aria-label='Please Enter Full Shipping Information']")
	WebElement counrtySelectionError;
	
	By suggestionBox = By.xpath("//div[@class='form-group']/section/button[2]");
	
	@FindBy(xpath="//div[@class='form-group']/section/button[2]")
	WebElement suggestCnty;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	public void selectCountry(String cntryName) {
		searchBox.sendKeys(cntryName);
		waitUntillElementisVisible(suggestionBox);
		suggestCnty.click();
	}
	
	public void placeOrder() throws InterruptedException {
		
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		waitUntillElementisClickable(placeOrder);
		Thread.sleep(1000);
		System.out.println("Waited");
		placeOrder.click();

	}
	
	public String countryErrorvalidation() {
		
		String actualError = counrtySelectionError.getText();
		System.out.println("The Actual Error message : "+actualError);
		return actualError;
		
	}

}
