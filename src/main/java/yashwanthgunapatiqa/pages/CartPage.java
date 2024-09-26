package yashwanthgunapatiqa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class CartPage extends Utils {
	
	private WebDriver driver;
	private Utils utils;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> prodsInCart;
	
	@FindBy(xpath="//div/ul/li/button")
	WebElement chckout;
	
	@FindBy(xpath="//div/button/i")
	WebElement removeProd;
	
	@FindBy(css="div[aria-label='No Product in Your Cart']")
	WebElement removeConfrm;
	
	
	By prodList = By.cssSelector(".cartSection h3");
	
    public boolean checkProdinCart(String itemName) {
        System.out.println("Waiting for the cart page to load...");

        // Wait until the cart items are visible
        waitUntillElementisVisible(prodList);

        // If the product list is still empty, log it
        if (prodsInCart.isEmpty()) {
            System.out.println("No products found in the cart.");
            return false;
        }

        System.out.println("Products found in the cart: " + prodsInCart.size());

        // Check if the product exists in the cart and log the names
        boolean match = prodsInCart.stream().anyMatch(prod -> {
            String cartProductName = prod.getText();
            System.out.println("Product in cart: " + cartProductName);
            return cartProductName.equalsIgnoreCase(itemName);
        });

        return match;
    }
    
    public void checkOutfromCart() throws InterruptedException {
    	
    	chckout.click();
    	
    }
    public String removeProdFromcart() {
    	
    	removeProd.click();
    	utils.waitUntillElementisVisible(removeConfrm);
    	String actualConfrm = removeConfrm.getText();
    	System.out.println("Actual Confirmation message of product removal is : "+actualConfrm);
    	return actualConfrm;
    }
	

}
