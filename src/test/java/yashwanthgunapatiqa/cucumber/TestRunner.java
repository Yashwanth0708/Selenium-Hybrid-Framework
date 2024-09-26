package yashwanthgunapatiqa.cucumber;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import yashwanthgunapatiqa.globalcomponents.BrowserComponents;
import yashwanthgunapatiqa.globalcomponents.Wrapper;

@CucumberOptions(features="src/test/java/yashwanthgunapatiqa/cucumber"
				, glue="yashwanthgunapatiqa/testcomponents"
				,monochrome=true
				,tags="@InvalidLoginCredentials"
				,plugin= {"html:target/cucumber.html"} )


public class TestRunner extends AbstractTestNGCucumberTests {

	
}




