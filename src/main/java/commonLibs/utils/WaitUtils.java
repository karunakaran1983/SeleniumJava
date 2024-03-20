package commonLibs.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	public static void WaitForSeconds(int timeOutInSeconds) throws Exception {
		Thread.sleep(timeOutInSeconds * 1000);
	}

	public static void WaitTillAlertIsPresent(WebDriver driver, int timeOutInSeconds) throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public static void WaitTillElementVisible(WebDriver driver, WebElement element, int timeOutInSeconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void WaitTillElementClickable(WebDriver driver, WebElement element, int timeOutInSeconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void WaitTillElementSelectable(WebDriver driver, WebElement element, int timeOutInSeconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(timeOutInSeconds));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}
}
