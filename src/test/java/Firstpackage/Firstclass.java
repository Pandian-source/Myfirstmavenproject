package Firstpackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
		  String expectedTitle = "ClaritySSO - Login";
	      String actualTitle = "";
	      driver.get(baseUrl);
          actualTitle = driver.getTitle();
	      if (actualTitle.contentEquals(expectedTitle))
	      {
	           //System.out.println("Test Passed!");
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
      WebDriverWait wait =new WebDriverWait(driver,10);
      WebElement Element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[1][@class=\"hero-login-link btn btn-primary\"]")));
	  String b = Element.getText();
	  System.out.println(b);
      Element.click();
      if(b.contentEquals("LOGIN"))
      {
    	  testCase.log(Status.PASS,"Text Equal");
      }
      else
      {
    	  testCase.log(Status.FAIL,"Text Not Equal");
      }
      JavascriptExecutor js1 = (JavascriptExecutor) driver;		
      js1.executeScript("alert('Welcome to B2B PANDIAN');");
      String text = driver.switchTo().alert().getText();			
      System.out.println(text);
      Alert alert = driver.switchTo().alert();
      alert.accept();
	}
@Test
public void username() throws IOException
  	{
	  System.out.println("driver="+driver);
 	  testCase = extentReport.createTest("Username Test");
 	  JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,350)", "");
 	  this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  WebElement username = this.driver.findElement(By.xpath(".//input[@placeholder=\"Your Email *\"]"));
      username.click();
      System.out.println("username field clicked");
      username.sendKeys("a.pandian@wintlt.com");
      System.out.println("Username field entered");
  	}
@Test
public void password() throws IOException
      {
	  System.out.println("driver="+driver);
 	  testCase = extentReport.createTest("password and Login success");
      this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      WebElement password = this.driver.findElement(By.xpath(".//input[@placeholder=\"Password *\"]"));
      password.click();
      System.out.println("password field clicked");
      password.sendKeys("12345S#");
      System.out.println("password field entered");
      WebElement button = driver.findElement(By.xpath(".//button[text()=\"Login\"]"));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].click();", button);
    }
@Test
public void password1() throws IOException
{
      System.out.println("driver="+driver);
 	  testCase = extentReport.createTest("searchform");
      this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      WebElement password1 = this.driver.findElement(By.xpath(".//span[text()=\"ClaritySSO\"]/following::input[@placeholder=\"From?\"]"));
      password1.click();
      JavascriptExecutor js2 = (JavascriptExecutor) driver;		
      js2.executeScript("alert('All actions performed PANDIAN');");
      String action = this.driver.switchTo().alert().getText();			
      System.out.println(action);
      Alert alert1 = this.driver.switchTo().alert();
      alert1.accept();
      }
@AfterSuite
public void close()
	      {   
	    	 driver.close();
	    	 System.out.println("browser closed");
	    	 extentReport.flush();
	      }
}
