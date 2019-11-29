package DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDriven {
WebDriver driver;
ExtentReports report;
ExtentTest test;
String inputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestInput\\RegisterData.xlsx";
String outputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestOutput\\register.xlsx";

@BeforeTest
public void setUp()
{
report=new ExtentReports("./Reports/Register.html");
System.setProperty("webdriver.chrome.driver", "D:\\4Oclock_Framework\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
}
@Test
public void verifyRegister()throws Throwable
{
//access excel methods
ExcelFileUtil xl=new ExcelFileUtil(inputpath);
//count no of rows
int rc=xl.rowCount("Register");
//count no of columns
int cc=xl.colCount("Register");
Reporter.log("row count"+rc+"   "+"column count"+cc,true);
for(int i=1;i<=rc;i++)
{
test=report.startTest("Register Test");
driver.get("http://newtours.demoaut.com/");
driver.manage().window().maximize();
String fname=xl.getCellData("Register", i, 0);
String lname=xl.getCellData("Register", i, 1);
String phone=xl.getCellData("Register", i, 2);
String mail=xl.getCellData("Register", i, 3);
String add1=xl.getCellData("Register", i, 4);
String add2=xl.getCellData("Register", i, 5);
String city=xl.getCellData("Register", i, 6);
String state=xl.getCellData("Register", i, 7);
String pcode=xl.getCellData("Register", i, 8);
String country=xl.getCellData("Register", i, 9);
String user=xl.getCellData("Register", i, 10);
String password=xl.getCellData("Register", i, 11);
String cpassword=xl.getCellData("Register", i, 12);
driver.findElement(By.partialLinkText("REG")).click();
Thread.sleep(3000);
driver.findElement(By.name("firstName")).sendKeys(fname);
driver.findElement(By.name("lastName")).sendKeys(lname);
driver.findElement(By.name("phone")).sendKeys(phone);
driver.findElement(By.name("userName")).sendKeys(mail);
driver.findElement(By.name("address1")).sendKeys(add1);
driver.findElement(By.name("address2")).sendKeys(add2);
driver.findElement(By.name("city")).sendKeys(city);
driver.findElement(By.name("state")).sendKeys(state);
driver.findElement(By.name("postalCode")).sendKeys(pcode);
new Select(driver.findElement(By.name("country"))).selectByVisibleText(country);
driver.findElement(By.name("email")).sendKeys(user);
driver.findElement(By.name("password")).sendKeys(password);
driver.findElement(By.name("confirmPassword")).sendKeys(cpassword);
driver.findElement(By.name("register")).click();
Thread.sleep(3000);
if(password.equals(cpassword))
{
String message=driver.findElement(By.xpath("//td//td[2]//table[1]//tbody[1]//tr[3]//td[1]")).getText();	
Reporter.log(message+"  "+"Register succes",true);
test.log(LogStatus.PASS,message );	
xl.setCellData("Register", i, 13, message, outputpath);
xl.setCellData("Register", i, 14, "Pass", outputpath);
}
else
{
test.log(LogStatus.FAIL, "Register Fail");	
Reporter.log("Register Fail",true);
xl.setCellData("Register", i, 13, "Register Fail", outputpath);
xl.setCellData("Register", i, 14, "Fail", outputpath);	
}
report.endTest(test);
report.flush();
}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}














