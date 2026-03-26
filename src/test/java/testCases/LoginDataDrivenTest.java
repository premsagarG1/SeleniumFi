package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass{

	/*\
	 * data is valid--login success--test pass ----logout
	 *                 login fail  ---test fail
	 *  data is invalid---login success--test fail--logout
	 *                    login fail---test pass                
	 */
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void verifyUser(String email,String password,String exp) {
		
		try {
		logger.info("***login data driver test started****");
		HomePage ph=new HomePage(driver);
		ph.myAccBtn();
		ph.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.email(email);
		lp.password(password);
		lp.loginClick(); 
		
		MyAccountPage myAcc=new MyAccountPage(driver);
		boolean target_page=myAcc.verifyAccount();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(target_page==true) {
				myAcc.clickLogout();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid")) {
			if(target_page==true) {
				myAcc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***login data driver test completed****");
}
}