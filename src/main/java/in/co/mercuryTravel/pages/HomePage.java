package in.co.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.utils.WaitUtils;

public class HomePage extends BasePage{
	
	@FindBy(linkText = "International Holidays")
	private WebElement internationalHolidaysLink;
	
	@FindBy(linkText = "Indian Holidays")
	private WebElement indianHolidays;
	
	@FindBy(linkText = "Luxury Rails")
	private WebElement luxuryRails;
	
	@FindBy(linkText = "Customer Login")
	private WebElement customerLogin;
	
	@FindBy(linkText = "User Login")
	private WebElement userLogin;
	
	@FindBy(id = "sign_user_email")
	private WebElement signUserEmailId;
	
	@FindBy(id = "sign_user_password")
	private WebElement signUserPassword;
	
	@FindBy(xpath = "//button[text()='Log in'][@class='btn btn-lg btn-primary modal-btn ajax-submit']")
	private WebElement loginButton;
	
	@FindBy(partialLinkText = "Welcome,")
	private WebElement welcomeText;
	
	public HomePage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void userLogin(String username, String password) throws Exception{
		mouseControl.moveToElement(customerLogin);
		mouseControl.moveToElementAndClick(userLogin);
		
		WaitUtils.WaitForSeconds(2);
		elementControl.setText(signUserEmailId, username);
		WaitUtils.WaitForSeconds(2);
		elementControl.setText(signUserPassword, password);
		elementControl.clickElement(loginButton);
	}
	
	public String getWelcomeText() throws Exception {
		WaitUtils.WaitForSeconds(2);
		return elementControl.getText(welcomeText);
	}
	
}
