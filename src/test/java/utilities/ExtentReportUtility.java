package utilities;



import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener{
	public ExtentSparkReporter sparkReport;
	public ExtentReports extent;
	public ExtentTest test;
	public String repName;
	
	public void onStart(ITestContext context) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//date format to show page
		repName="test-report-"+timeStamp+".html";
		sparkReport=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of file

		sparkReport.config().setDocumentTitle("opencart automation report");
		sparkReport.config().setReportName("opencart functional testing");
		sparkReport.config().setTheme(Theme.STANDARD);
		
		// dummy data
		extent=new ExtentReports();
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("application", "opencart");
		extent.setSystemInfo("computor name", "localhost");
		extent.setSystemInfo("environment", "QA");
		extent.setSystemInfo("tester name", "ps");
		extent.setSystemInfo("username", System.getProperty("user.name"));
		
	
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("browser name", os);	
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("groups", includedGroups.toString());
		}
	  }
	
	public void onTestSuccess(ITestResult result) {
	   test=extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.PASS, "Test case passed is::"+result.getName());
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		   test.assignCategory(result.getMethod().getGroups());
		   
		  test.log(Status.FAIL, " got failed "+result.getName());
		  test.log(Status.INFO, "Test case failed case is::"+result.getThrowable().getMessage());
		
		  try {
			  String imgPath=new BaseClass().captureScreen(result.getName());
			  test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		   test.assignCategory(result.getMethod().getGroups());
		   
		   test.log(Status.SKIP, "Test case skipped is::"+result.getName());
		   test.log(Status.INFO, result.getThrowable().getMessage());
			
	  }
	
	public void onFinish(ITestContext context) {
	   extent.flush();
	   
	   String pathOfExtendedReport=System.getProperty("user.dir")+"\\reports\\"+repName;
	   File extendRepo=new File(pathOfExtendedReport);
	   try {
		Desktop.getDesktop().browse(extendRepo.toURI());
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	  }


}
