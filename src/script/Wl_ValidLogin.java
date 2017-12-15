package script;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.WLLoginPage;
public class Wl_ValidLogin extends BaseTest {
	@Test(priority=1,groups="sanity")
	public void testValidLogin() {
		String emailId=Excel.getCellValue(XL_PATH, "WlValidLogin", 1, 0);
		String password=Excel.getCellValue(XL_PATH, "WlValidLogin", 1, 1);
	
	//enter email-id
	WLLoginPage l=new WLLoginPage(driver);
	l.enterEmail(emailId);	
	//enter password	
	l.enterPassword(password);	
	//click on login button 	
	l.clickLogin();

	}

}
