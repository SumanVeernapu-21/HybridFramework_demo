package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass{

		
	@Test(groups= {"Regression", "Sanity"})
	public void verifyAccountRegistration() throws InterruptedException {
		logger.info("*****Started TC001_AccountRegistrationTest execution*******");
		try {
		HomePage hp= new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickRegister();
		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		arp.setFirstname(RandomString().toUpperCase());
		arp.setLastname(RandomString().toUpperCase());
		arp.setEmail(RandomString()+"@gmail.com");
		Thread.sleep(2000);
		arp.setTelephone(Randomnumber());
		String pwd= RandomAplhaNum();
		arp.setPassword(pwd);
		arp.setConfirmpassword(pwd);
		Thread.sleep(2000);
		arp.setPrivacypolicy();
		arp.clkContinue();
		String msg= arp.getConfirmationMsg();
		if(msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			
		}
		else {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertFalse(false);
		}
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("******Execution completed********");		
	}
	
	
	
}
