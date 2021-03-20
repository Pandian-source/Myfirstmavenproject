package Firstpackage;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenerClass implements ITestListener {

	WebDriver driver=null;
	String filePath = "./Screenshots";
	
    public void onTestStart(ITestResult result) {

	}
	public void onTestSuccess(ITestResult result) 
	{
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		System.out.println(result.getName()+" Test has Failed");
		String methodName=result.getName().toString().trim();
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		takeScreenShot(methodName, driver);
	}
	public void takeScreenShot(String methodName, WebDriver driver) {
		File scrFile = ((TakesScreenshot)Firstclass.driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
			System.out.println("Successfully Taken the screenshot");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();

		}
	}
	

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context)
	{
		
	}}