package learning.framework.testcomponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.resources.extent_reports;

public class listeners extends Base implements ITestListener{
		
		ExtentTest test;
		ExtentReports reports = extent_reports.reports_config();			// calling the method using class name followed by method name
		
		@Override
	    public void onTestStart(ITestResult result)					
		{
			// result will be having the complete data of the method which is going to execute
			//Before starting any test, this method will be executed
			test = reports.createTest(result.getMethod().getMethodName());		//using result, we are fetching the method name		
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) 
	    {
	    	//if any test is passed this method will be executed
	    	test.log(Status.PASS, "Test passed");
	        
	    }

	    @Override
	    public void onTestFailure(ITestResult result) 
	    {	
	    	//if any test is failed this method will be executed, we will use this method to take the screenshot
	    	test.log(Status.FAIL, "Test passed");
	    	
	    	
	    	try 
	    	{
				driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			}
	    	catch (Exception e) 
	    	{	
				e.printStackTrace();
			} 	    	
	    	
	    	test.fail(result.getThrowable());					// this will get the fail error msg
	    	
	    	String path = null;
			try 
			{
				path = screenshot(result.getMethod().getMethodName(),driver);		// calling the screenshot method and storing the path of image
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	test.addScreenCaptureFromPath(path,result.getMethod().getMethodName());		// attaching the image path and along with method name
	    }

	    @Override 
	    public void onTestSkipped(ITestResult result) {
	        
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Usually not used
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        
			reports.flush();								// after finshing all the tak, this will generate the report
	    }
	
}
