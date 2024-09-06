package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import testCases.BaseClass;



public class ExtentReportManager implements ITestListener 
{ 
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentRepo;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		
		  /*SimpleDateFormat df = new SimpleDateFormat("yyy.MM.dd HH.mm.ss"); 
		  Date dt = new Date(); 
		  String currentdatetimestamp = df.format(dt);*/
		 
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time Stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\ "+ repName);  //specify the location of the report
		
		sparkReporter.config().setDocumentTitle("openCart automation Report"); // Title of the Report
		sparkReporter.config().setReportName("openCart functional Testing"); // name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentRepo = new ExtentReports();
		extentRepo.attachReporter(sparkReporter);
		extentRepo.setSystemInfo("Application", "openCart");
		extentRepo.setSystemInfo("Module", "Admin");
		extentRepo.setSystemInfo("Sub Module", "Customers");
		extentRepo.setSystemInfo("User Name", System.getProperty("user.name"));
		extentRepo.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extentRepo.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extentRepo.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		
		if(!includedGroups.isEmpty()) 
		{
			extentRepo.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extentRepo.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  // to display groups in Report
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extentRepo.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"git failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try 
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extentRepo.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extentRepo.flush();	
	//if we want to open report file automatically have to write code 
	    
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\ "+repName;
		
		File extentReport = new File(pathofExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
