package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.AUL;
import generic.IAutoConstant;

public class WLLoginPage implements IAutoConstant {
	@FindBy(id="j_email")
	private WebElement emailTxtBx;
	
	@FindBy(id="j_password")
	private WebElement passwordTxtBx;
	
	@FindBy(id="proceed")
	private WebElement loginBtn;
	
	@FindBy(xpath="//p[@class='errorTextpopup' and contains(.,'username/password does not match ')]")
	private WebElement errorMsg;
	
	public WLLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void enterEmail(String email) {
		emailTxtBx.sendKeys(email);
	}
	public void enterPassword(String pwd) {
		passwordTxtBx.sendKeys(pwd);
	}
	public void clickLogin() {
		loginBtn.click();
	}
	public void verifyErrMsgIsDisplayed(WebDriver driver) {
		String strETO=AUL.getProperty(CONFIG_PATH, "ETO");
		Long ETO=Long.parseLong(strETO);
		WebDriverWait wait=new WebDriverWait(driver, ETO);
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMsg));
			Reporter.log("error message displayed ",true);
		}catch(Exception e) {
			Reporter.log("error message not displayed ",true);
			Assert.fail();
			
		}
		
	}

}
