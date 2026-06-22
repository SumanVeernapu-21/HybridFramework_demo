package testCases;

import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_withDataDriven extends BaseClass{

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "Datadriven")
	public void login_datadriven(String email, String pwd, String res) {
		
		logger.info("*****Started execution TC003_Login_withDataDriven*******");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);		
		lp.SetEmailadd(email);
		lp.SetPwd(pwd);
		//Thread.sleep(3000);
		lp.clkLogin();
				
		//MyAccount
		MyAccountPage map = new MyAccountPage(driver);
		boolean val= map.chk_MyAccountHng();		
		
		if(res.equalsIgnoreCase("Valid")) {
			if(val==true) {
				map.clk_logout();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}
		
		
		if(res.equalsIgnoreCase("Invalid")) {
			if(val==true) {
				map.clk_logout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		}
		catch (Exception e){
			Assert.fail();
		}
		logger.info("*****Completed execution TC003_Login_withDataDriven*******");	
	}
}
