package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

	//constructor
	public RegistrationPage(WebDriver driver){
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_psw;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_cpsw;
	
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btn_agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confimMsg;
	
	//methods
	public void fistName(String fname) {
		txt_firstname.sendKeys(fname);
	}
	
	public void lastName(String lname) {
		txt_lastname.sendKeys(lname);
	}
	
	public void email(String email) {
		txt_email.sendKeys(email);
	}
	
	public void telephone(String telephone) {
		txt_telephone.sendKeys(telephone);
	}
	
	public void passowd(String pw) {
		txt_psw.sendKeys(pw);
	}
	
	public void cpassowd(String cpw) {
		txt_cpsw.sendKeys(cpw);
	}
	
	public void btnAgree() {
		btn_agree.click();
	}
	
	public void btnContinue() {
		btn_continue.click();
		
		//sol1
		//btn_continue.submit();
		
		//sol2
		//Actions act=new Actions(driver);
		//act.moveToElement(btn_continue).click().perform();
		
		//sol3
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("Argumets[0].click()", btn_continue);
		
		//sol4
		//btn_continue.sendKeys(Keys.RETURN);
		
		//sol5
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable(btn_continue)).click();
		
	}
	
	public String getMsg() {
		try {
			return confimMsg.getText();
		}catch(Exception e) {
			return (e.getMessage());
		}
		
	}
	
}
