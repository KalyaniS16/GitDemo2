package RSAcademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RSAcademy.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	WebDriver driver;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();   //ThreadSafe
	
	
	@Override
//	Invoked each time before a test will be invoked.
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());     //when ErrorValidationClass method (LoginErrorValidation) is under execution, it will get only methodname and set the entry in extent report.
		extentTest.set(test);   //this will set the test object into ThreadLocal
	}

//	On test pass
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {		
		extentTest.get().fail(result.getThrowable());
		test.log(Status.FAIL, "Test is fail");   //it is a log
		test.fail(result.getThrowable());    //it will print the error message in the report
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		
		String filePath = null;
		//Screenshot	
//		iF SS doent exists, then it will print in the output saying that SS doesn't exists.
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);   //in parameters of getScreenShot method, we have send the testCase name from above
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {
	
	}
	
}
