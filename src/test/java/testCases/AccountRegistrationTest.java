package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {

	
	
	
	@Test(groups= {"Regression","Master"})
	public void Registration() {
		
		try {
		logger.info("*** home page is started****");
		HomePage hp=new HomePage(driver);
		hp.myAccBtn();
		logger.info("My Account btn clicked");
		hp.clickRegistration();
		logger.info("registration started");
		
		RegistrationPage rp=new RegistrationPage(driver);
		
		logger.info("provide user details");
		rp.fistName(randomString());
		rp.lastName(randomString());
		rp.email(randomString()+"@gmail.com");
		rp.telephone(randomNumber());
		String pd=randompw();
		rp.passowd(pd);
		rp.cpassowd(pd);
		rp.btnAgree();
		rp.btnContinue();
		logger.info("verifing user details");
		String m=rp.getMsg();
		if(m.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			
			logger.error("test failed");
			logger.debug("debugg level");
			assertTrue(false);
		}
		
		//Assert.assertEquals(m, "Your Account Has Been Created!");
		
		
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("***registration page Execution completed****");
			
	}
	
}
