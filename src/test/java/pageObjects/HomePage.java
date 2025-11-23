package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']") private WebElement myAccountDrp;
	@FindBy(xpath = "//a[normalize-space()='Register']") private WebElement registerButtn;
	@FindBy(xpath = "//a[normalize-space()='Login']") private WebElement loginButton;

	public void clickMyAccountDrpMenu()
	{
		myAccountDrp.click();
	}

	public void clickRegisterButton()
	{
		registerButtn.click();
	}

	public void clickLoginButton()
	{
		loginButton.click();
	}


}
