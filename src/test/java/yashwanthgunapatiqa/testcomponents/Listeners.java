package yashwanthgunapatiqa.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import yashwanthgunapatiqa.globalcomponents.*;
import yashwanthgunapatiqa.globalcomponents.ExtentReportsUtils;

public class Listeners implements ITestListener {

    // Use ThreadLocal for thread safety when running parallel tests
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    ExtentReports extent = ExtentReportsUtils.getReportObject();  // Initialize ExtentReports properly
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());  // Create new test
        extentTest.set(test);  // Set the test in ThreadLocal for thread safety
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test case passed");  // Log pass
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        extentTest.get().fail(result.getThrowable());  // Log the failure in the report

        // Get WebDriver instance from the test class
        Object testClass = result.getInstance();
        WebDriver driver = null;

        // Safely retrieve WebDriver, ensuring that testClass is of type Wrapper
        if (testClass instanceof Wrapper) {
            System.out.println("Test class is an instance of Wrapper, retrieving WebDriver.");
            driver = ((Wrapper) testClass).driver;  // Retrieve WebDriver if testClass is a Wrapper
        } else {
            System.err.println("Test class is NOT an instance of Wrapper. Cannot retrieve WebDriver.");
            return;  // Exit if WebDriver cannot be retrieved
        }

        if (driver == null) {
            System.err.println("WebDriver is null, cannot take a screenshot.");
            return;
        }

        // Capture screenshot
        try {
            ExtentReportsUtils extentReportsUtils = new ExtentReportsUtils(driver);
            String filePath = extentReportsUtils.getScreenShot(result.getMethod().getMethodName());
            if (filePath != null) {
                extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
                System.out.println("Screenshot captured for failed test: " + result.getMethod().getMethodName());
            }
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test case skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Flush the report after all tests are done
    }
}
