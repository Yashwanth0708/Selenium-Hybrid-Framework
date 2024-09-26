package yashwanthgunapatiqa.globalcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ExtentReportsUtils  {
	
	
	private WebDriver driver;
	private BrowserComponents browser;
    // Constructor to initialize the WebDriver
	
    public ExtentReportsUtils(WebDriver driver) throws IOException {
        this.driver = driver;  // Use the WebDriver passed from test classes
        this.browser = new BrowserComponents();
    }
    
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Yash");
		
		return extent;
		
	}
	
	public String getScreenShot(String testcaseName) throws IOException {
	        
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, destFile);
		return (System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		
		
	}

	

}
