package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccount;
import testBase.BaseClass;

public class TC001_VerifyAccountRegistrationTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verifyAccountRegistration()
	{
		logger.info("******* Starting 001 Test ********");

         HomePage hp = new HomePage(driver);
         logger.info("clicking on my account drop menu!");
         hp.clickMyAccountDrpMenu();
         logger.info("clicking on register button drop menu!");
         hp.clickRegisterButton();

         RegisterAccount ra = new RegisterAccount(driver);
         logger.info("entering first name!");
         ra.enterFirstName(getRandomString());
         logger.info("entering last name!");
         ra.enterLastName(getRandomString());
         logger.info("entering email name!");
         ra.enterEmailField(getRandomString()+"@gmail.com");
         logger.info("entering telephone number!");
         ra.enterTelephoneField(getRandomNumber());
         logger.info("entering password!");
         String password = getRandomString() + getRandomNumber();
         ra.enterPasswordField(password);
         logger.info("entering confirm password!");
         ra.enterConfirmPasswordField(password);
         logger.info("selecting checkBox!");
         ra.selectCheckBox();
         logger.info("Clicking on continue button!");
         ra.clickContinueBtn();
         logger.info("Verifying success message!");
         Assert.assertTrue(ra.verifySuccessMessage());

         logger.info("******* Finished 001 Test ********");
	}
}
