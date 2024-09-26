package yashwanthgunapatiqa.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import yashwanthgunapatiqa.globalcomponents.BrowserComponents;
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
        extentTest.get().fail(result.getThrowable());  // Log the failure in the report

        // Get WebDriver instance from the test class
        Object testClass = result.getInstance();
        WebDriver driver = ((BrowserComponents) testClass).driver;  // Safely retrieve WebDriver

        // Check if WebDriver is null
        if (driver == null) {
            System.err.println("WebDriver is null in onTestFailure. Skipping screenshot.");
            return;
        }

        // Capture screenshot
        ExtentReportsUtils extentReportsUtils = new ExtentReportsUtils(driver);
        try {
            String filePath = extentReportsUtils.getScreenShot(result.getMethod().getMethodName());
            if (filePath != null) {
                extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            }
        } catch (IOException e) {
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
