package Firstpackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Firstclass {
WebDriver driver= null;
ExtentReports extentReport;
ExtentHtmlReporter htmlReporter;
ExtentTest testCase;

 @BeforeSuite
 public void Openbrowser() throws IOException
 {
          System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\Automation\\chromedriver.exe");
		  extentReport = new ExtentReports();
		  htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
		  extentReport.attachReporter(htmlReporter);
 }
@Test
public void gettitle() throws IOException
{
          this.driver = new ChromeDriver();
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
	      } }
@Test
public void loginbutton() throws InterruptedException
{	 
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
     
	}

@Test
public void password() throws IOException
      {
	  System.out.println("driver="+driver);
 	  testCase = extentReport.createTest("password and Login success");
      this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      WebElement username = this.driver.findElement(By.xpath("//input[@type='text']"));
      username.click();
      System.out.println("username field clicked");
      username.sendKeys("a.pandian@wintlt.com");
      System.out.println("Username field entered");
      WebElement password = this.driver.findElement(By.xpath("//input[@type='password']"));
      password.click();
      System.out.println("password field clicked");
      password.sendKeys("12345S#");
      System.out.println("password field entered");
      WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", button);
    }
@Test
public void password1() throws IOException, InterruptedException
{
      System.out.println("driver="+driver);
 	  testCase = extentReport.createTest("searchform");
      this.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
      WebDriverWait wait2 = new WebDriverWait(driver, 100);
      Thread.sleep(30000);
      wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='text'])[2]"))).click();
      }

@Test
public void tofield() throws IOException
{
	  System.out.println("driver="+driver);
	  testCase = extentReport.createTest("searchform");
	  this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      WebDriverWait wait2 = new WebDriverWait(driver, 10);
      wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='text'])[3]"))).click();
}
@AfterSuite
public void close()
	      {   
	    	 driver.close();
	    	 System.out.println("browser closed");
	    	 extentReport.flush();
	      }
}
