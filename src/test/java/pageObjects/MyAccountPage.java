package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement verify_user;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btn_logout;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement search_Box;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement search_btn;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement no_productucts_available;
	
	@FindBy(xpath="//div[@class='caption']//a")
	List<WebElement> vaerify_product;
	
	
	@FindBy(xpath="//a[normalize-space()='iPhone']")
	WebElement vaerify_product1;
	
	
	
	@FindBy(xpath="//span[normalize-space()='Add to Cart']")
	WebElement add_prod_to_cart;
	
	public boolean verifyAccount() {
		try {
		return verify_user.isDisplayed();
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		btn_logout.click();
	}
	
	public void searchProd(String name) {
		search_Box.sendKeys(name);
	}
	
	public void searchBtn() {
		search_btn.click();
	}
	
	public boolean availableProd() {
		try {
			boolean bool=no_productucts_available.isDisplayed();
			return bool;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<String> verifyProdName() {
		List<String> ed=new ArrayList<>();
		 for (WebElement webElement : vaerify_product) {
			String as=webElement.getText();
			ed.add(as);
		}
		 return ed;
	}
	public String verifyProdName1() {
		return vaerify_product1.getText();
	}
	
	public void addCart() {
		add_prod_to_cart.click();
	}

}
