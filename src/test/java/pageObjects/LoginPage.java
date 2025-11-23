package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']") private WebElement emailField;
	@FindBy(xpath = "//input[@id='input-password']") private WebElement passwordField;
	@FindBy(xpath = "//input[@value='Login']") private WebElement loginBtn;

	public void enterEmail(String email)
	{
		emailField.sendKeys(email);
	}

	public void enterPassword(String pwd)
	{
		passwordField.sendKeys(pwd);
	}

	public void clickLogin()
	{
		loginBtn.click();
	}
}
