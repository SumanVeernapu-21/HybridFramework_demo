package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Regression", "Master"})
	public void verify_login() {
		
		logger.info("******TC002_LoginTest execution Started*******");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);		
		lp.SetEmailadd(p.getProperty("Email"));
		lp.SetPwd(p.getProperty("Password"));
		lp.clkLogin();
				
		//MyAccount
		MyAccountPage map = new MyAccountPage(driver);
		boolean val= map.chk_MyAccountHng();		
		Assert.assertEquals(val, true); //or Assert.assertTrue(val);
		
		
	}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("******TC002_LoginTest execution Completed*******");
	}
	
}
