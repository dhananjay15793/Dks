package generic;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//Automation Utility Library
public class AUL {
	public static String getProperty(String path,String key) {
		String v="";
		try {
			Properties p=new Properties();
			p.load( new FileInputStream(path));
			v=p.getProperty(key);
		}
		catch(Exception e){		
		     }
		return v;
		
	}
	
	
	public boolean FileUploadUsingRobot(String m_strText) throws Exception {
		boolean bStatus = false;
		try {
			StringSelection stringSelection = new StringSelection(m_strText);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			Robot robot = new Robot();
			robot.keyPress(17);
			robot.keyPress(86);
			robot.keyRelease(86);
			Thread.sleep(1000L);
			robot.keyRelease(17);
			Thread.sleep(1000L);
			robot.keyPress(9);
			robot.keyRelease(9);
			Thread.sleep(1000L);
			robot.keyPress(9);
			robot.keyRelease(9);
			Thread.sleep(1000L);
			robot.keyPress(10);
			robot.keyRelease(10);
			Thread.sleep(2000L);
			bStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bStatus;
	}
	
//Method to take screen shot of App
	public static void takePhoto(String folder,String TestName,WebDriver driver){
		String dateTime = new Date().toString().replaceAll(":","_");
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		String dstPath=folder+TestName+dateTime+".png";
		try {
			FileUtils.copyFile(srcFile,new File(dstPath));
		} catch (Exception e) {
	}
		
		
	}
}

