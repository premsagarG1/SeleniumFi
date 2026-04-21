package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass{

	
	public Logger logger; 
	
	public static WebDriver driver;
	public static final String username = "premborkut_pCOSdi";
	public static final String accessKey = "UscsVSsipkBucaLpwzts";
	public static final String url = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
    
	
	public Properties prop;
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})//@Parameters({"browser"})
	public void setUp(String os,String browser) throws IOException  {
		
		FileReader read=new FileReader(".//src//test//resources//config.properties");
		prop=new Properties();
		prop.load(read);
		
		//logger msg
		logger=LogManager.getLogger(this.getClass());
		
		System.out.println(prop.getProperty("execution_env").equalsIgnoreCase("remote"));

		//os and browser setup
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilites=new DesiredCapabilities();
			/*
			if(os.equalsIgnoreCase("window")) {
				capabilites.setPlatform(Platform.WIN10);
			}
			else if(browser.equalsIgnoreCase("mac")) {
				capabilites.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("not matching os");
				return;
			}
			
		
		
		//browser 
		switch(browser.toLowerCase()) 
		{
		case "chrome":capabilites.setBrowserName("chrome");break;
		case "edge":capabilites.setBrowserName("MicrosoftEdge");break;
		default :System.out.println("invalid browser");
		return;
		}*/
		capabilites.setCapability("os", "Windows");
		capabilites.setCapability("osVersion", "10");
		capabilites.setCapability("browserName", "Edge");
		driver=new RemoteWebDriver(new URL(url),capabilites);
	   //driver=new RemoteWebDriver(new URL("http://192.168.1.6:4444/wd/hub"), capabilites);

	}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase()) 
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver(); break;
			default :System.out.println("invalid browser");
			return;
		}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("addUrl"));
		
	
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
		
	}
	public String randomString() {
		String genString=RandomStringUtils.randomAlphanumeric(5);
		return genString;
	}
	
	public String randomNumber() {
		String genNum=RandomStringUtils.randomNumeric(10);
		return genNum;
	}
	
	public String randompw() {
		return randomString()+"@"+randomNumber();
	}
	
	public String captureScreen(String tname) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot shot=(TakesScreenshot)driver;
		File sourceFile=shot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp;
		File tragetFile=new File(targetFilePath);
		
		sourceFile.renameTo(tragetFile);
		
		return targetFilePath;
		
		
		
		
	}
}
