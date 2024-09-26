package yashwanthgunapatiqa.pages;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class OrdersPage {
	
	private WebDriver driver;
	private Utils utils;
	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[preceding-sibling::td/img and following-sibling::td[contains(., '$')]]")
	List<WebElement> getProduct;
	
	@FindBy(xpath="//td[contains(.,'Delete')]")
	List<WebElement> deleteProduct;
	
	
	@FindBy(xpath="//td[contains(text(), '$')]/preceding-sibling::th")
	List<WebElement> getOrderID;
	
	@FindBy(xpath="//div[@aria-label='Orders Deleted Successfully']")
	WebElement deleteMessage;
	
	public String prodName;
	public boolean getProductDetails(String expProdName) {
	    boolean match = false;
	    
	    System.out.println("Number of product Names : "+getProduct.size());
	    System.out.println("Number of product ID's : "+getOrderID.size());
	    
	    // Ensure both lists have the same size
	    if (getProduct.size() != getOrderID.size()) {
	        System.out.println("Error: Product list and Order ID list sizes do not match.");
	        return false;  // Exit early if the lists are not aligned
	    }
	    
	    for (int i = 0; i < getProduct.size(); i++) {
	        prodName = getProduct.get(i).getText();  // Fetch each product name
	        System.out.println("Name of the Ordered Product is: " + prodName);
	        
	        if (prodName.equalsIgnoreCase(expProdName)) {  // Use equalsIgnoreCase for comparison
	            System.out.println("Expected Product Found: " + prodName);
	            
	            // Fetch Order ID from the same index (ensure index consistency)
	            String orderId = getOrderID.get(i).getText();
	            System.out.println("Order ID for " + prodName + " is: " + orderId);
	            
	            //Delete the product from orders list
	            deleteProduct.get(i).click();
	            utils.waitUntillElementisVisible(deleteMessage);
	            System.out.println(deleteMessage.getText());
	            
	            match = true;
	            break;  // Break the loop once the product is found
	        }
	    }
	    
	    if (!match) {
	        System.out.println("Expected Product not found in the orders.");
	    }
	    
	    return match;
	}
}
