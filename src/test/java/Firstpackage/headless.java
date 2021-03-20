package Firstpackage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class headless {
	
	public ExtentReports extentReport;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest testCase;

	@Test
	public void Openbrowser() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
		extentReport = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
		extentReport.attachReporter(htmlReporter);
		driver.manage().window().maximize();
		testCase = extentReport.createTest("Get the title");
		testCase.log(Status.INFO,"Successfully Got the title");
		System.out.println("Window maximised");
		String baseUrl = "https://b2bui2.tltid.com/login";
		String expectedTitle = "Login";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		if (actualTitle.contentEquals(expectedTitle))
		{

			testCase.log(Status.PASS," Test Passed");
		} 
		else 
		{
			System.out.println("Test Failed");
			testCase.log(Status.FAIL," Test Failed");
		} 
		System.out.println("driver="+driver);
		testCase = extentReport.createTest("Loginbutton");
		WebDriverWait wait =new WebDriverWait(driver,100);
		Thread.sleep(10);
		WebElement Element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		String b = Element.getText();
		System.out.println(b);
		Element.click();
		if(b.contentEquals("Login"))
		{
			testCase.log(Status.PASS,"Text Equal");
		}
		else
		{
			testCase.log(Status.FAIL,"Text Not Equal");
		}
         driver.close();
	}


}
