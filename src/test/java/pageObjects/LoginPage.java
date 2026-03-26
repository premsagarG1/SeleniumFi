package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement btn_login;
	
	//methods
	public void email(String email) {
		txt_email.sendKeys(email);
	}
	
	public void password(String pw) {
		txt_password.sendKeys(pw);
	}
	
	public void loginClick() {
		btn_login.click();;
	}
	
	
	
	
}
