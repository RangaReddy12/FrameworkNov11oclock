package CommonFunlibaray;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constants.PBConstant;

public class PBFunctions extends PBConstant{
/*ProjectName:Primus Bank
 * Module Name:Admin login
 * TesterName:Ranga
 * Creation date:28/11/19
 */
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
	driver.findElement(By.xpath(p.getProperty("objusername"))).sendKeys(username);
	driver.findElement(By.xpath(p.getProperty("objpassword"))).sendKeys(password);
	driver.findElement(By.xpath(p.getProperty("objloginbtn"))).click();
	Thread.sleep(4000);
	String Expval="adminflow";
	String Actval=driver.getCurrentUrl();
	if(Actval.toLowerCase().contains(Expval.toLowerCase()))
	{
	Reporter.log("Login Success",true);
	return true;
	}
	else
	{
	Reporter.log("Login Fail",true);
	return false;
	}
	}
/*ProjectName:Primus Bank
	 * Module Name:Navigate branches
	 * TesterName:Ranga
	 * Creation date:28/11/19
	 */
	public static void navigateBranches()throws Throwable
	{
driver.findElement(By.xpath(p.getProperty("objBranches"))).click();
Thread.sleep(4000);
	}
	/*ProjectName:Primus Bank
	 * Module Name:Admin logout
	 * TesterName:Ranga
	 * Creation date:28/11/19
	 */
public static boolean verifyLogout()throws Throwable
{
	driver.findElement(By.xpath(p.getProperty("objlogout"))).click();
	Thread.sleep(3000);
	if(driver.findElement(By.xpath(p.getProperty("objloginbtn"))).isDisplayed())
	{
	Reporter.log("Logout Success",true);
	return true;
	}
	else
	{
	Reporter.log("Logout Fail",true);
	return false;
	}
}
/*ProjectName:Primus Bank
 * Module Name:Branch Creation
 * TesterName:Ranga
 * Creation date:28/11/19
 */
public static boolean verifyBranchc(String bname,String address1,String zcode,
int country,int state,int city)throws Throwable
{
driver.findElement(By.xpath(p.getProperty("objnewbranch"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("objbname"))).sendKeys(bname);
driver.findElement(By.xpath(p.getProperty("objadd1"))).sendKeys(address1);
driver.findElement(By.xpath(p.getProperty("objzcode"))).sendKeys(zcode);
new Select(driver.findElement(By.xpath(p.getProperty("objcountry")))).selectByIndex(country);
Thread.sleep(4000);
new Select(driver.findElement(By.xpath(p.getProperty("objstate")))).selectByIndex(state);
Thread.sleep(4000);
new Select(driver.findElement(By.xpath(p.getProperty("objcity")))).selectByIndex(city);
Thread.sleep(4000);
driver.findElement(By.xpath(p.getProperty("objsubmit"))).click();
//get alert message
String alertmessage=driver.switchTo().alert().getText();
Reporter.log(alertmessage,true);
Thread.sleep(4000);
driver.switchTo().alert().accept();
Thread.sleep(4000);
String Expval="new branch";
if(alertmessage.toLowerCase().contains(Expval.toLowerCase()))
{
Reporter.log("Branch Created Success",true);
return true;
}
else
{
Reporter.log("Branch Created Fail",true);
return false;
}
}
/*ProjectName:Primus Bank
 * Module Name:Branch Updation
 * TesterName:Ranga
 * Creation date:28/11/19
 */
public static boolean verifyBranchU(String bname,String address1)throws Throwable
{
driver.findElement(By.xpath(p.getProperty("Obj_Click_Edit"))).click();
Thread.sleep(3000);
WebElement branch=driver.findElement(By.xpath(p.getProperty("Obj_Update_Bname")));
branch.clear();
Thread.sleep(3000);
branch.sendKeys(bname);
WebElement add=driver.findElement(By.xpath(p.getProperty("Obj_Update_Add1")));
add.clear();
Thread.sleep(3000);
add.sendKeys(address1);
Thread.sleep(3000);
driver.findElement(By.xpath(p.getProperty("Obj_Click_Update"))).click();
//get alert message
String alertmessage1=driver.switchTo().alert().getText();
Reporter.log(alertmessage1,true);
Thread.sleep(4000);
driver.switchTo().alert().accept();
Thread.sleep(4000);
String Expval="branch update";
if(alertmessage1.toLowerCase().contains(Expval.toLowerCase()))
{
Reporter.log("Branch updated Success",true);
return true;
}
else
{
Reporter.log("Branch Updated Fail",true);
return false;
}
}
public static String generateDate()
{
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_ss");
return sdf.format(date);
}
}



























