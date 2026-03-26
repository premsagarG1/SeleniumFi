package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	//locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement btn_my_acc;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement btn_my_reg;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement  btn_login;
	
	//methods for locators
	public void myAccBtn() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(btn_my_acc)).click();
		//btn_my_acc.click();
	}
	
	public void clickRegistration() {
		btn_my_reg.click();
	}
	
	public void clickLogin() {
		btn_login.click();
		}

}
