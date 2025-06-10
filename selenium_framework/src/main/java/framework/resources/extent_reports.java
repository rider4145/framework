package framework.resources;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extent_reports {
	
	public static ExtentReports reports;
	public static ExtentSparkReporter report;

		public static ExtentReports reports_config()					// by declaring as static, we can access this method without declaring the object
		{
			reports = new ExtentReports();
			String path = System.getProperty("user.dir")+"//reports//index.html";
			report = new ExtentSparkReporter(path);
			report.config().setReportName("Automation Reports - Web");
			report.config().setDocumentTitle("Reports");
			reports.attachReporter(report);
			reports.setSystemInfo("Tester", "Rahul N");
			return reports;
		}
}
