package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

// IT WILL CREATE EXTENT REPORT ONCE EXCECUTION IS COMPLETED
public class ExtentReportManager implements ITestListener 
{
	public ExtentSparkReporter sparkReporter; // responsible for UI of your report(Look and feel of your report
	public ExtentReports extent; // common details we can post in report(user info,op system,project name)
	public ExtentTest test; // creating  the entries in the report
	
	String repName;
	
	public void onStart (ITestContext testContext) // it will execute before starting all tests
	{
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	repName="Test-Report-"+timeStamp+".html";
		
		
	sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName);// specify location of the report
	
	sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the report
	sparkReporter.config().setReportName("Pet Store Users ApI"); // Name of the report
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter); // main engine attaching with sparkReporter
	
	extent.setSystemInfo("Application", "Pet Store Users ApI"); // giving application name
	extent.setSystemInfo("OS", System.getProperty("os.name")); // operating system
	extent.setSystemInfo("User Name", System.getProperty("user.name"));// user name
	extent.setSystemInfo("Browser", "Chrome"); // browser name
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("user","feni");
	
	
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,"Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
	}
	
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,"Test Skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush(); 
	}

}
