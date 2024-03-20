package in.co.mercuryTravel.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.ConfigFileUtils;
import commonLibs.utils.DateUtils;
import in.co.mercuryTravel.pages.HomePage;
import in.co.mercuryTravel.pages.RegisterPage;

public class BaseTest {

	CommonDriver commonDriver;

	HomePage homePage;
	RegisterPage registerPage;

	private WebDriver driver;

	String browserType;
	int pageloadTimeout;
	int elementDetectionTimeout;
	String baseUrl;

	static String configFileName;
	static Properties configProperties;
	static String currentWorkingDirectory;
	static String executionStartDate;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;

	String reportFilename;

	String screenshotFilename;
	ScreenshotControl screenshotControl;

	static {
		try {
			currentWorkingDirectory = System.getProperty("user.dir");
			executionStartDate = DateUtils.getCurrentDateAndTime();

			configFileName = currentWorkingDirectory + "/config/config.properties";

			configProperties = ConfigFileUtils.readProperties(configFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void preSetup() {
		reportFilename = String.format("%s/reports/MercuryTravelTestReport-%s.html", currentWorkingDirectory,
				executionStartDate);

		htmlReporter = new ExtentHtmlReporter(reportFilename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass
	public void setUp() throws Exception {
		extentTest = extent.createTest("Setup - Set up the pre-requisite to run automated test cases");

		browserType = configProperties.getProperty("browserType");
		extentTest.log(Status.INFO, "Browser invoked is " + browserType);
		commonDriver = new CommonDriver(browserType);

		pageloadTimeout = Integer.parseInt(configProperties.getProperty("pageLoadTimeout"));
		commonDriver.setPageloadTimeout(pageloadTimeout);
		extentTest.log(Status.INFO, "Page Load Timeout is " + pageloadTimeout);

		elementDetectionTimeout = Integer.parseInt(configProperties.getProperty("elementDetectionTimeout"));
		commonDriver.setElementDetectionTimeout(elementDetectionTimeout);
		extentTest.log(Status.INFO, "Element Detection Timeout is " + elementDetectionTimeout);

		baseUrl = configProperties.getProperty("baseUrl");
		commonDriver.navigateToUrl(baseUrl);
		extentTest.log(Status.INFO, "Base URL where the browser navigates to - " + baseUrl);

		driver = commonDriver.getDriver();

		extentTest.log(Status.INFO, "Initializing all pages");
		homePage = new HomePage(driver);
		registerPage = new RegisterPage(driver);

		screenshotControl = new ScreenshotControl(driver);
	}

	@AfterClass
	public void cleanUp() throws Exception {
		commonDriver.closeBrowser();

		extentTest = extent.createTest("Clean Up - Clean process");
		extentTest.log(Status.INFO, "Closing all browser instances ");
	}

	@AfterSuite
	public void postCleanUp() {
		extent.flush();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		String testCaseName = result.getName();

		screenshotFilename = String.format("%s/screenshot/%s-%s.jpeg", currentWorkingDirectory, testCaseName, executionStartDate);
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test case Pass " + testCaseName);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test case Failed " + testCaseName);
			screenshotControl.captureAndSaveScreenshot(screenshotFilename);
			extentTest.addScreenCaptureFromPath(screenshotFilename);
		} else {
			extentTest.log(Status.SKIP, "Test case skipped " + testCaseName);
		}
	}
}
