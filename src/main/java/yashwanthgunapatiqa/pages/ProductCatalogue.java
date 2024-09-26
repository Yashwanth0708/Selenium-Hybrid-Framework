package yashwanthgunapatiqa.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yashwanthgunapatiqa.globalcomponents.Utils;

public class ProductCatalogue {

    private WebDriver driver;
    private Utils utils;
    
    // Constructor to initialize WebDriver and WebElements using PageFactory
    public ProductCatalogue(WebDriver driver) {
    	
    	
        this.driver = driver; // Ensure the WebDriver is passed correctly
        this.utils = new Utils(driver);
        PageFactory.initElements(driver, this); // Initialize elements
    }

    @FindBy(xpath = "//div[@class='container']/div[@class='row']/div")
    List<WebElement> productsList;
    
    @FindBy(xpath = "//div[@class='overlay-container']/div")
    WebElement prodConf;

    By productsLoc = By.xpath("//div[@class='container']/div[@class='row']");
    By itemID = By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductList() {
    	
        utils.waitUntillElementisVisible(productsLoc); // Wait until products are visible
        System.out.println("Getting the product list, total products: " + productsList.size());
        
        return productsList;
    }
    public WebElement getProductName(String prodName) {
        WebElement productName = getProductList()
                .stream()
                .filter(product -> {
                    // Get the product name text
                    String name = product.findElement(By.xpath(".//div[@class='card-body']/h5")).getText().trim();
                    System.out.println("Checking product: " + name); // Debugging log
                    // Check if it matches the desired product name (ignore case and trim whitespaces)
                    return name.equalsIgnoreCase(prodName.trim());
                })
                .findFirst()
                .orElse(null);

        if (productName == null) {
            System.out.println("Product not found: " + prodName);
        } else {
            String foundProductName = productName.findElement(By.xpath(".//div[@class='card-body']/h5")).getText();
            System.out.println("Product found: " + foundProductName);
        }

        return productName;
    }
    
    public String selectProduct(String item) {
        WebElement itemN = getProductName(item);
        
        if (itemN == null) {
            System.out.println("Cannot select product: " + item);
            return null;
        }

        // Clicking the product's "Add to Cart" button
        itemN.findElement(itemID).click();
        
        // Wait for confirmation overlay to appear and disappear
        utils.waitUntillElementisVisible(By.xpath("//div[@class='overlay-container']/div"));
        utils.waitUntillElementisInvisible(driver.findElement(By.cssSelector(".ng-animating")));
        
        // Get the confirmation text
        String conf = prodConf.getText();
        System.out.println(conf);

        return item;
    }
    
}
