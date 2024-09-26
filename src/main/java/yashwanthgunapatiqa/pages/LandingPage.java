package yashwanthgunapatiqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class LandingPage {

	private WebDriver driver;
	private Utils utils;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(driver, this);
	}

    // Web element locators
    @FindBy(id = "userEmail")
    WebElement userEmailField;

    @FindBy(id = "userPassword")
    WebElement userPasswordField;

    @FindBy(id = "login")
    WebElement loginButton;
    
    @FindBy(xpath = "//div[@id='toast-container']/div/div")
    WebElement loginError;

    // Method to enter login details
    public void userLoginDetails(String emailID, String password) {
    	
        userEmailField.sendKeys(emailID); // Enter email
        userPasswordField.sendKeys(password); // Enter password
        loginButton.click(); // Click login
    }
    
    public String errorValidation() {
    	
    	utils.waitUntillElementisVisible(loginError);
    	String actuatlError = loginError.getText();
    	System.out.println("Actual Text message : "+actuatlError);
    	return actuatlError;
    	
    }
    
}