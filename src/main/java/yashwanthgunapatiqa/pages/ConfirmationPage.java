package yashwanthgunapatiqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class ConfirmationPage extends Utils {
	
	private WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
 }
	@FindBy(css=(".hero-primary"))
	WebElement actualConfirmationText;
	
	public String confirmationMessage() throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actualConfirmationText);
		String actualText = actualConfirmationText.getText();
		actualText.equalsIgnoreCase(actualText);
		System.out.println(actualText);
		return actualText;
		
	}
}
