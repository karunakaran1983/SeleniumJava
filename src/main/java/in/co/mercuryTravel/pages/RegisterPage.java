package in.co.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage{

	@FindBy(linkText = "Customer Login")
	private WebElement customerLogin;
	
	@FindBy(xpath =  "(//a[text()='Register'])[2]")
	private WebElement registerOptionElement;
	
	@FindBy(id = "acc_first_name")
	private WebElement firstNameElement;
	
	@FindBy(id = "acc_last_name")
	private WebElement lastNameElement;
	
	@FindBy(id = "acc_user_email")
	private WebElement emailAddressElement;
	
	@FindBy(id = "acc_user_password")
	private WebElement userPasswordElement;
	
	@FindBy(id = "acc_user_passconf")
	private WebElement confirmPasswordElement;
	
	@FindBy(id = "acc_mobile_no")
	private WebElement mobileNumberElement;
	
	@FindBy(xpath = "//button[@class='btn btn-lg btn-primary modal-btn ajax-submit'][text()='Register']")
	private WebElement registerButtonElement;
	
	public RegisterPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void registerUser(String firstName, String lastName, String emailAddress, String password, String mobileNumber) throws Exception{
		mouseControl.moveToElement(customerLogin);
		mouseControl.moveToElementAndClick(registerOptionElement);
		
		elementControl.setText(firstNameElement, firstName);
		elementControl.setText(lastNameElement, lastName);
		elementControl.setText(emailAddressElement, emailAddress);
		elementControl.setText(userPasswordElement, password);
		elementControl.setText(confirmPasswordElement, password);
		elementControl.setText(mobileNumberElement, mobileNumber);
		elementControl.clickElement(registerButtonElement);
	}
}
