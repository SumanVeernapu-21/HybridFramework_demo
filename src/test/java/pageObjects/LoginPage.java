package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
	PageFactory.initElements(driver, this);		
	}
	
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_Emailadd;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_pwd;	
	
	@FindBy(xpath="//input[@value='Login']") WebElement btn_Login;
	
	public void SetEmailadd(String email) {
		txt_Emailadd.sendKeys(email);
	}
			
	public void SetPwd(String password) {
		txt_pwd.sendKeys(password);
	}
	
	public void clkLogin() {
		btn_Login.click();
	}


	}

	
	
