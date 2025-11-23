package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccount extends BasePage {

	public RegisterAccount(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']") private WebElement firstName;
	@FindBy(xpath = "//input[@id='input-lastname']") private WebElement lastName;
	@FindBy(xpath = "//input[@id='input-email']") private WebElement emailField;
	@FindBy(xpath = "//input[@id='input-telephone']") private WebElement telephoneField;
	@FindBy(xpath = "//input[@id='input-password']") private WebElement password;
	@FindBy(xpath = "//input[@id='input-confirm']") private WebElement confPassword;
	@FindBy(xpath = "//input[@name='agree']") private WebElement chekBox;
	@FindBy(xpath = "//input[@value='Continue']") private WebElement continueBtn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") private WebElement successMsg;

	public void enterFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}

	public void enterLastName(String lname)
	{
		lastName.sendKeys(lname);
	}

	public void enterEmailField(String email)
	{
		emailField.sendKeys(email);
	}

	public void enterTelephoneField(String tel)
	{
		telephoneField.sendKeys(tel);
	}

	public void enterPasswordField(String pwd)
	{
		password.sendKeys(pwd);
	}

	public void enterConfirmPasswordField(String pwd)
	{
		confPassword.sendKeys(pwd);
	}

	public void selectCheckBox()
	{
		chekBox.click();
	}

	public void clickContinueBtn()
	{
		continueBtn.click();
	}

	public boolean verifySuccessMessage()
	{
		return successMsg.isDisplayed();
	}
}
