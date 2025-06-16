package RSAcademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {        //as we keep this method as static, we can access it without creating object of that class
//		ExtentSparkReporter expects the path where reporter should be created
		String path = 	System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
//		ExtentReports responsible to driver all your reporting execution
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);          //helps to attach all the reports to main class ExtentReports
		extent.setSystemInfo("Tester", "Kalyani Sajanpawar");
		return extent;	
	}
}
