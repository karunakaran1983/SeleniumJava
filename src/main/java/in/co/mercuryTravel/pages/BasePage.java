package in.co.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.AlertControl;
import commonLibs.implementation.CommonElement;
import commonLibs.implementation.MouseControl;

public class BasePage{
	
	protected AlertControl alertControl;
	protected CommonElement elementControl;
	protected MouseControl mouseControl;
	
	public BasePage(WebDriver driver) throws Exception{
		alertControl = new AlertControl(driver);
		elementControl = new CommonElement();
		mouseControl = new MouseControl(driver);
	}

}
