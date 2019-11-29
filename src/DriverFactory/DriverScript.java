package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunlibaray.PBFunctions;
import Constants.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PBConstant {
String inputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestInput\\Controller.xlsx";
String outputpath="D:\\4Oclock_Framework\\Selenium_Frameworks\\TestOutput\\Keyword.xlsx";
ExtentReports report;
ExtentTest test;
String TCSheet="TestCases";
String TSSheet="TestSteps";
File screen;
@Test
public void pbKeyword()throws Throwable
{
	report=new ExtentReports("./Reports/Keyword.html");
boolean res=false;
String tcres="";
//access xl util methods
ExcelFileUtil xl=new ExcelFileUtil(inputpath);
//cont no of rows in TCSheet
int TCCount=xl.rowCount(TCSheet);
//count no of rows in TSSheet
int TSCount=xl.rowCount(TSSheet);
for(int i=1;i<=TCCount;i++)
{
	//read excute column
String Execute=xl.getCellData(TCSheet, i, 2);
if(Execute.equalsIgnoreCase("Y"))
{
//read tcid column from TCSHeet
String Tcid=xl.getCellData(TCSheet, i, 0);
for(int j=1;j<=TSCount;j++)
{
test=report.startTest("KeyWord Framework");
//read tsid column from TSSHeet
String TSid=xl.getCellData(TSSheet, j, 0);
String Description=xl.getCellData(TSSheet, j, 2);
if(Tcid.equalsIgnoreCase(TSid))
{
//read keyword column
String Keyword=xl.getCellData(TSSheet, j, 3);
if(Keyword.equalsIgnoreCase("AdminLogin"))
{
res=PBFunctions.verifyLogin("Admin", "Admin");
test.log(LogStatus.INFO, Description);
}
else if(Keyword.equalsIgnoreCase("NewBranchCreation"))
{
PBFunctions.navigateBranches();
res=PBFunctions.verifyBranchc("kadiri", "anantapur", "12345", 1, 2, 1);
test.log(LogStatus.INFO, Description);
}
else if(Keyword.equalsIgnoreCase("UpdateBranch"))
{
PBFunctions.navigateBranches();
res=PBFunctions.verifyBranchU("madanapalli", "chitttor");
test.log(LogStatus.INFO, Description);
}
else if(Keyword.equalsIgnoreCase("AdminLogout"))
{
res=PBFunctions.verifyLogout();
test.log(LogStatus.INFO, Description);
}
String tsres="";
if(res)
{
screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("D:\\4Oclock_Framework\\Selenium_Frameworks\\Screens\\"+PBFunctions.generateDate()+"Primus.png"));
	//write as pass in to results column in TSSheet
tsres="Pass";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);
test.log(LogStatus.PASS, Description);
}
else
{
screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen, new File("D:\\4Oclock_Framework\\Selenium_Frameworks\\Screens\\"+PBFunctions.generateDate()+"Primus.png"));	
//write as Fail in to results column in TSSheet	
tsres="Fail";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);
test.log(LogStatus.FAIL, Description);
}
//if not tcres equal to null then write as pass or fail into 
if(!tsres.equalsIgnoreCase("Fail"))
{
tcres=tsres;
}
report.endTest(test);
report.flush();
}

}
xl.setCellData(TCSheet, i, 3, tcres, outputpath);
}
else
{
//write as not executed in results column in TCSheet
xl.setCellData(TCSheet, i, 3, "Not Executed", outputpath);	
}
}
}
}
















