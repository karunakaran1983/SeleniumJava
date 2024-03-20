package in.co.mercuryTravel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class MercuryTravelTestCases extends BaseTest {
	
//	@Test
//	public void verifyUserRegistration() throws Exception {
//		
//		String firstName = "Ramya";
//		String lastName = "Karun";
//		String emailAddress = "karunaiphone201803@gmail.com";
//		String password = "K@run@007";
//		String mobileNumber = "9884095487";		
//				
//		registerPage.registerUser(firstName, lastName, emailAddress, password, mobileNumber);
//	}
	
	@Test
	public void loginUser() throws Exception {
		
		extentTest = extent.createTest("TC-001 - Verify user login with correct credentials");
		
		String emailAddress = configProperties.getProperty("userEmailId");
		extentTest.log(Status.INFO, "User Email Id is "+ emailAddress);
		String password = configProperties.getProperty("userPassword");
		extentTest.log(Status.INFO, "User Password is "+ password);
		
		homePage.userLogin(emailAddress, password);
		
		String expectedWelcomeText = configProperties.getProperty("expectedWelcomeText");
		String actualWelcomeText = homePage.getWelcomeText();
		
		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
		extentTest.log(Status.INFO, "User login successful");
	}
}
