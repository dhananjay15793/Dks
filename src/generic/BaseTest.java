package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest implements IAutoConstant {
	public WebDriver driver;
	static {
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	@Parameters({"browser"} )
	@BeforeMethod(alwaysRun=true)
	public void openApplication(@Optional("chrome")String browser ) {
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
//
//		//driver.manage().window().maximize();
//				ChromeOptions co = new ChromeOptions();
//				//here "--start-maximized" argument is responsible to maximize chrome browser
//				co.addArguments("--start-maximized");
//				
//				//Chromedriver.exe is place in the same directory
//				//System.setProperty(CHROME_KEY,CHROME_VALUE);
//		WebDriver driver = new ChromeDriver(co);
		String AUT=AUL.getProperty(CONFIG_PATH, "AUT");
		driver.get(AUT);
		
		
		String strITO=AUL.getProperty(CONFIG_PATH, "ITO");
		Long ITO=Long.parseLong(strITO);
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
	}
	@AfterMethod(alwaysRun=true)
	public void closeApplication(ITestResult res){
		String testName=res.getName();
		if(res.getStatus()==2){
			AUL.takePhoto(PHOTO_PATH,testName, driver);
		}
		driver.quit();
}
}
