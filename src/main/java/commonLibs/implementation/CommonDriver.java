package commonLibs.implementation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import commonLibs.contracts.IDriver;

public class CommonDriver implements IDriver {

	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectionTimeout;

	private String currentWorkingDriectory;

	public CommonDriver(String browserType) throws Exception {
		pageloadTimeout = 60;
		elementDetectionTimeout = 10;

		currentWorkingDriectory = System.getProperty("user.dir");

		if (browserType.equalsIgnoreCase("chrome")) {
			 System.setProperty("webdriver.chrome.driver",
			 currentWorkingDriectory + "/drivers/chromedriver.exe");

			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			 System.setProperty("webdriver.edge.driver",
			 currentWorkingDriectory + "/drivers/msedgedriver.exe");

			driver = new EdgeDriver();
		} else {
			throw new Exception("Invalid Browser Type");
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	@Override
	public void navigateToFirstUrl(String url) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageloadTimeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(elementDetectionTimeout));
		url = url.trim();
		driver.get(url);
	}

	@Override
	public String getTitle() throws Exception {
		String title = driver.getTitle();
		return title;
	}

	@Override
	public String getCurrentUrl() throws Exception {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	@Override
	public String getPageSource() throws Exception {
		return driver.getPageSource();
	}

	@Override
	public void navigateToUrl(String url) throws Exception {
		url = url.trim();
		driver.navigate().to(url);

	}

	@Override
	public void navigateForward() throws Exception {
		driver.navigate().forward();

	}

	@Override
	public void navigateBackward() throws Exception {
		driver.navigate().back();

	}

	@Override
	public void refresh() throws Exception {
		driver.navigate().refresh();

	}

	@Override
	public void closeBrowser() throws Exception {
		if (driver != null) {
			driver.close();
		}
	}

	@Override
	public void closeAllBrowsers() throws Exception {
		if(driver != null ) {
			driver.quit();
		}
	}
}
