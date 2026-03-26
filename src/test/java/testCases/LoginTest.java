package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass{

	
	@Test(groups= {"Sanity","Master"})
	public void login() {
		try {
			
		logger.info("***login page execution started******");
		HomePage hp=new HomePage(driver);
		hp.myAccBtn();
		hp.clickLogin();
		
		LoginPage login=new LoginPage(driver);
		login.email(prop.getProperty("email"));
		login.password(prop.getProperty("password"));
		login.loginClick();
		
		MyAccountPage verify=new MyAccountPage(driver);
		
		boolean bool=verify.verifyAccount();
			Assert.assertEquals(bool, true);
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("*** login page execution completed******");
	}
}
