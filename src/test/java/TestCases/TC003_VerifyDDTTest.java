package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_VerifyDDTTest extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DDT")
	public void verifyLoginFunctionality(String email, String pwd, String exp)
	{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccountDrpMenu();
		hp.clickLoginButton();

		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickLogin();

		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageDisplayed();

		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}

		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
	}
}

}
