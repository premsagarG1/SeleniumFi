package testCases;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import testBase.BaseClass;


public class SearchProductTest extends BaseClass{

	@Test(groups= {"Sanity","Master"})
	public void searchProduct() throws InterruptedException {
		try {
			FileReader read=new FileReader(".//src//test//resources//config.properties");
			prop=new Properties();
			prop.load(read);
			
			MyAccountPage myAcc=new MyAccountPage(driver);
			myAcc.searchProd(prop.getProperty("searchProd"));
			
			myAcc.searchBtn();
			Thread.sleep(2000);
			
			boolean avlOrNo=myAcc.availableProd();//true -->prod(0)//false(1)
						
			if(avlOrNo==true) {
				System.out.println("no products available");
				Assert.assertEquals(avlOrNo, false);	
			}else {
				System.out.println("products available.....but");
				//System.out.println(myAcc.verifyProdName());
				//List<String> ver=myAcc.verifyProdName();
				String ver=myAcc.verifyProdName1();
				System.out.println(ver);
				
				if("iPhone".equals(ver)) {
					System.out.println("products available....verify");
					myAcc.addCart();
					System.out.println("added to cart");
					Thread.sleep(3000);
				}
				else {
					System.out.println("searched product wrong shown");
					Assert.assertTrue(false);
				}
			}
	
		}
	
		catch (Exception e) {
			Assert.fail();
		}
	}
	
	
}
