package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ApplicationLayer.AdminPage;
import ApplicationLayer.EmpPage;
import ApplicationLayer.LogoutPage;
import Utilities.ExcelFileUtil;

public class PomDataDriven {
WebDriver driver;
ExtentReports report;
ExtentTest test;
String inputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestInput\\EmpCreation.xlsx";
String outputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestOutput\\empresults.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	report=new ExtentReports("./Reports/Pomdatadriven.html");
	System.setProperty("webdriver.chrome.driver", "D:\\4Oclock_Framework\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
AdminPage login=PageFactory.initElements(driver, AdminPage.class);
login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void Verifyempcreation()throws Throwable
{
	EmpPage emp=PageFactory.initElements(driver, EmpPage.class);
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("Emp");
	Reporter.log("no of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
	test=report.startTest("Emp Creation");
	String fname=xl.getCellData("Emp", i, 0);
	String lname=xl.getCellData("Emp", i, 1);
	String eid=xl.getCellData("Emp", i, 2);
	emp.verifyAddEmp(fname, lname, eid);
	if(driver.getCurrentUrl().contains("empNumber"))
	{
xl.setCellData("Emp", i, 3, "Pass", outputpath);		
		Reporter.log("Emp Created Success",true);
		test.log(LogStatus.PASS, "Emp Created Success");
	}
	else
	{
		xl.setCellData("Emp", i, 3, "Fail", outputpath);
		Reporter.log("Emp Created Fail",true);
		test.log(LogStatus.FAIL, "Emp Created Fail");	
	}	
	report.endTest(test);
	report.flush();
	}
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
















