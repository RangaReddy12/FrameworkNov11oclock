package Constants;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBConstant {
public static WebDriver driver;
public static Properties p;
public static FileInputStream fi;
@BeforeMethod
public void setUp()throws Throwable
{
p=new Properties();
fi=new FileInputStream("D:\\4Oclock_Framework\\Selenium_Frameworks\\PropertyFile\\OR.properties");
p.load(fi);
if(p.getProperty("browser").equalsIgnoreCase("chrome"))
{
System.setProperty("webdriver.chrome.driver", "D:\\4Oclock_Framework\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
	driver=new ChromeDriver();
driver.get(p.getProperty("objurl"));	
driver.manage().window().maximize();
}
else if(p.getProperty("browser").equalsIgnoreCase("firefox"))
{
System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
driver=new FirefoxDriver();
driver.get(p.getProperty("objurl"));		
}
else if(p.getProperty("browser").equalsIgnoreCase("ie"))
{
System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
driver=new InternetExplorerDriver();
driver.get(p.getProperty("objurl"));	
}
else
{
	System.out.println("Browser not matchiong");
}
}
@AfterMethod
public void tearDown()
{
driver.close();	
}
}















