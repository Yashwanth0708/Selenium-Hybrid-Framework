package yashwanthgunapatiqa.globalcomponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils{

    public WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize page elements
    }
    
    @FindBy (css="[routerlink*='/dashboard/cart']")
    WebElement clickOnCart;
    
    @FindBy (xpath="//button[@routerlink='/dashboard/']")
    WebElement clickOnHome;
    
    @FindBy (css="[routerlink*='/dashboard/myorders']")
    WebElement clickOnOrders;
    
    @FindBy (xpath="//button[.//i[contains(@class,'fa-sign-out')]]")
    WebElement clickOnSignOut;

    public void waitUntillElementisVisible(By findByElement) {
        System.out.println("Waiting for element to be visible: " + findByElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findByElement));
    }
    public void waitUntillElementisVisible(WebElement findByElement) {
        System.out.println("Waiting for element to be visible: " + findByElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findByElement));
    }
    public void waitUntillElementisInvisible(WebElement findByElement) {
        System.out.println("Waiting for element to be Invisible: " + findByElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(findByElement));
    }
    
    public void waitUntillElementisClickable(WebElement findByElement) {
        System.out.println("Waiting for element to be Invisible: " + findByElement);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findByElement));
    }
    
    public void navigateToCart() {
    	waitUntillElementisVisible(clickOnCart);
    	clickOnCart.click();
    }
    
    public void navigateToHome() {
    	waitUntillElementisVisible(clickOnHome);
    	clickOnHome.click();
    }
    public void navigateToOrders() {
    	waitUntillElementisVisible(clickOnOrders);
    	clickOnOrders.click();
    }
    public void signOutfromApp() {
    	clickOnSignOut.click();
    }
    
}
