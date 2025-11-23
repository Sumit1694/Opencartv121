package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_VerifyLoginTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verifyLoginFunctionality()
	{
		logger.info(" ******Starting TC_002 Login Test ******");
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on my account drop menu");
		hp.clickMyAccountDrpMenu();
		logger.info("Clicked on login button");
		hp.clickLoginButton();

		LoginPage lp = new LoginPage(driver);
		logger.info("entering email");
		lp.enterEmail(prop.getProperty("appEmail"));
		logger.info("entering password");
		lp.enterPassword(prop.getProperty("appPassword"));
		logger.info("clicking on login button");
		lp.clickLogin();

		MyAccountPage map = new MyAccountPage(driver);
		logger.info("verifying user is landed on my account page!");
		Assert.assertTrue(map.isMyAccountPageDisplayed());
		logger.info(" ******Finished TC_002 Login Test ******");
	}
}
