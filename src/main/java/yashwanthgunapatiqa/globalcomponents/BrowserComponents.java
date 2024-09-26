package yashwanthgunapatiqa.globalcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserComponents {

    public WebDriver driver;
    public Properties prop;

    // Method to read properties from the configuration file
    public void readPropFile() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\BaseFile.properties");
        prop.load(fis);
    }

    // Method to set up the browser based on the properties file
    public void setupBrowser() throws IOException {
        readPropFile();  // Read the properties file
       String browserName =  System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
       System.out.println("Setting up the browser: " + browserName);
        // Setup browser based on the "browser" property
        if (browserName.contains("chrome")) {
        	ChromeOptions options = new ChromeOptions();          
            if(browserName.contains("headless")) {
            options.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
            // Manage window size based on the properties file
            if (prop.getProperty("windowSize").equalsIgnoreCase("maximize")) {
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Browser window maximized.");
            } else if (prop.getProperty("windowSize").equalsIgnoreCase("minimize")) {
                driver.manage().window().minimize();
                System.out.println("Browser window minimized.");
            }

            System.out.println("Chrome browser initialized successfully.");

        } else if (browserName.equalsIgnoreCase("firefox")) {
            // Set the path of the GeckoDriver (skip this if GeckoDriver is in PATH)
        	WebDriverManager.firefoxdriver().setup();
            // Create a new instance of FirefoxDriver
            driver = new FirefoxDriver();
            System.out.println("FireFox browser initialized successfully.");
            if (prop.getProperty("windowSize").equalsIgnoreCase("maximize")) {
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Browser window maximized.");
            } else if (prop.getProperty("windowSize").equalsIgnoreCase("minimize")) {
                driver.manage().window().minimize();
                System.out.println("Browser window minimized.");
            }
        } else if (browserName.equalsIgnoreCase("edge")) {
            // Set the path of EdgeDriver (skip this if EdgeDriver is in PATH)
        	WebDriverManager.edgedriver().setup();
            // Create a new instance of EdgeDriver
            driver = new EdgeDriver();
        	System.out.println("Microsoft Edge browser initialized successfully.");
            if (prop.getProperty("windowSize").equalsIgnoreCase("maximize")) {
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Browser window maximized.");
            } else if (prop.getProperty("windowSize").equalsIgnoreCase("minimize")) {
                driver.manage().window().minimize();
                System.out.println("Browser window minimized.");
            }
        }

        // Check if driver was initialized correctly
        if (driver == null) {
            System.err.println("WebDriver initialization failed.");
        }
    }
    
    
    public void launchWebSite() throws IOException {
    	   System.out.println("Starting setupBrowser...");
    	    //setupBrowser();  // Setup the browser

    	    if (driver == null) {
    	        throw new IllegalStateException("WebDriver is not initialized. Cannot launch the website.");
    	    }
    	    
    	    String Url = System.getProperty("testURL") != null ? System.getProperty("testURL") : prop.getProperty("testURL");
    	    System.out.println("Navigating to URL: " + Url);
    	    driver.get(Url);  // Navigate to the URL
    }
    
    
    public void quitBrowser() {
    	System.out.println("closing setupBrowser...");
        if (driver != null) {
            driver.quit();  // Quit the WebDriver session
        }
    }
}
