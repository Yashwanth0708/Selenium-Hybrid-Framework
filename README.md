**Selenium-Hybrid-Framework**

**Overview**

This repository contains the Hybrid java Automation Framework, built using Java, Selenium, TestNG, Cucumber, and ExtentReports. The framework is designed for end-to-end testing of the eKart application, integrating modern tools and following best practices like the Page Object Model (POM), data-driven testing, and Behavior Driven Development (BDD) with Cucumber.

**Features**
•	Browser automation using Selenium.
•	Behaviour-Driven Development (BDD) with Cucumber.
•	Parallel execution and test grouping using TestNG.
•	Automatic WebDriver management with WebDriverManager.
•	Detailed HTML reports using ExtentReports.
•	Jenkins integration for Continuous Integration/Continuous Delivery (CI/CD) pipelines.
•	Git for version control and collaboration.

**Technologies Used**

•	Java: Primary programming language.
•	Selenium: Browser automation.
•	TestNG: Test management and execution.
•	Cucumber: BDD testing with Gherkin syntax.
•	ExtentReports: HTML-based reports for test results.
•	WebDriverManager: Automatic browser driver management.
•	Maven: Dependency management and build automation.
•	Jenkins: CI/CD automation.
•	Git: Version control.

**Project Structure**
The project is organized into the following key directories and files:

HybridAutomationFramework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── yashwanthgunapatiqa/
│   │   │       ├── globalcomponents/
│   │   │       │   ├── BrowserComponents.java
│   │   │       │   ├── ExtentReportsUtils.java
│   │   │       │   ├── JsonUtils.java
│   │   │       │   ├── Utils.java
│   │   │       │   └── Wrapper.java
│   │   │       ├── pages/
│   │   │       │   ├── CartPage.java
│   │   │       │   ├── CheckOutPage.java
│   │   │       │   ├── ConfirmationPage.java
│   │   │       │   ├── LandingPage.java
│   │   │       │   ├── OrdersPage.java
│   │   │       │   └── ProductCatalogue.java
│   │   │       └── testdata/
│   │   │           ├── OrdersChecking.json
│   │   │           └── PurchaseProduct.json
│   │   └── resources/
│   │       └── BaseFile.properties
│   ├── test/
│   │   └── java/
│   │       └── yashwanthgunapatiqa/
│   │           ├── cucumber/
│   │           │   ├── TestRunner.java
│   │           │   ├── Errorvalidation.feature
│   │           │   └── PurchaseOrder.feature
│   │           ├── testcomponents/
│   │           │   ├── Listeners.java
│   │           │   ├── ReExecuteFailedcase.java
│   │           │   └── StepDefinitions.java
│   │           └── tests/
│   │               ├── Errorvalidation.java
│   │               └── SubmitOrder_E2E_TestCase.java
│
├── executeFile/
│   ├── errorValidation.xml
│   └── submitOrder.xml
│
├── drivers/
│   ├── geckodriver.exe
│   └── msedgedriver.exe
│
├── reports/
│   ├── checkOutProduct.png
│   ├── checkOutWithOutCountry.png
│   ├── index.html
│   └── loginErrorValidation.png
│
├── target/
│   ├── generated-sources/
│   ├── generated-test-sources/
│   ├── maven-archiver/
│   ├── maven-status/
│   ├── surefire-reports/
│   │   └── cucumber.html
│   └── FrameworkDesignImpl-0.0.1-SNAPSHOT.jar
│
├── test-output/
│
├── LICENSE
├── pom.xml
└── README.md

**Global Components**

•	BrowserComponents.java: Handles browser setup, initialization, and WebDriver configurations.
•	ExtentReportsUtils.java: Manages Extent Reports for logging detailed test execution reports.
•	JsonUtils.java: Utility for reading and parsing JSON data used in data-driven test cases.
•	Utils.java: General-purpose utility methods for interacting with web elements, waits, and alerts.
•	Wrapper.java: Encapsulates reusable methods like login, search, and order placement for easy reuse in tests.

**Pages**
Implements the Page Object Model (POM) to encapsulate page-specific logic:
•	LandingPage.java: Handles login functionality.
•	CartPage.java: Manages cart operations such as adding/removing products.
•	CheckOutPage.java: Manages actions related to the checkout process.
•	ConfirmationPage.java: Validates order confirmations.
•	OrdersPage.java: Manages order history and validation.
•	ProductCatalogue.java: Handles product selection and interactions.

**Test Data**
•	OrdersChecking.json: Contains data related to checking and verifying orders.
•	PurchaseProduct.json: Contains data related to purchasing products.

**Test Components**
•	Listeners.java: Implements TestNG listeners to log test results and capture screenshots on failure.
•	ReExecuteFailedcase.java: Manages re-execution of failed tests.
•	StepDefinitions.java: Defines the Cucumber step definitions.

**Cucumber Features**
•	Errorvalidation.feature: Defines BDD scenarios for validating error messages.
•	PurachaseOrder.feature: Defines BDD scenarios for product purchase tests.

**Test Classes**
•	Errorvalidation.java: Contains test methods for verifying error handling scenarios.
•	SubmitOrder_E2E_TestCase.java: An end-to-end test case for the complete product order process.

**Configuration Files**
•	BaseFile.properties: Contains environment configurations like browser settings, URLs, and file paths.
________________________________________

**Test Execution**
**Running Tests with TestNG**
You can run specific tests by executing the corresponding TestNG XML files located in the executeFile/ directory.
•	errorValidation.xml: Executes test cases related to error validation.
•	submitOrder.xml: Executes end-to-end order placement tests with parallel execution.

**To run the tests using TestNG:**

    •	mvn test -DsuiteXmlFile=executeFile/errorValidation.xml
    •	mvn test -DsuiteXmlFile=executeFile/submitOrder.xml

**Running Tests with Cucumber**

    •	mvn test -Dcucumber.options="--tags @YourTag"

**Reports**
After test execution, reports are generated in the reports/ folder:
    •	ExtentReports HTML: Detailed test results can be viewed in index.html, including screenshots of failures.
    •	Screenshots: Captured during test execution for failure analysis (e.g., checkOutProduct.png, loginErrorValidation.png).
    
**Pre-Requisites**
1.	Java 17 or higher.
2.	Maven for dependency management.
3.	WebDriver binaries (managed via WebDriverManager, so no manual setup required).
4.	Git for version control.
5.	Browsers: Ensure Chrome, Firefox, and Edge browsers are installed locally for cross-browser testing.

**Setting Up and Running the Framework**
**Clone the Repository:**

    •	git clone https://github.com/Yashwanth0708/Selenium-Hybrid-Framework.git
    •	cd eKartAutomationFramework    
**Install Dependencies:** 
Ensure Maven is installed and run the following command to install dependencies
    •	mvn clean install
    
**Configure Properties:**
Edit the BaseFile.properties file for browser type, URLs, and other configurations:
#Used in Browser Components class in global components package
    •	browser=chrome
    •	windowSize = maximize
    •	testURL = https://rahulshettyacademy.com/client
#Used in JsonUtils class in test data package
    •	PurchaseProductData = src/main/java/yashwanthgunapatiqa/testdata/PurchaseProduct.json
    •	OrderCheckData = src/main/java/yashwanthgunapatiqa/testdata/OrdersChecking.json

**Run the Tests**
Execute the TestNG suites or individual Cucumber feature files using Maven
      •	mvn test
    
**Jenkins Integration**
For CI/CD integration with Jenkins, configure the job to:
    •	Pull the code from the GitHub repository.
    •	Execute the Maven commands mentioned above for running the tests.
    •	Set up post-build actions to publish the ExtentReports generated in the reports/ folder.
    
**License**
This project is licensed under the Apache License – see the LICENSE file for details.

**Contributing**
Contributions, issues, and feature requests are welcome! Feel free to open a pull request or issue in the repository.


