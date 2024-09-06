package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() {
		logger.info("Startig TC001_AccountRegistrationTest....");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link....");
		
		hp.clickRegister();
		logger.info("Clicked on Registration Link....");
		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer details....");
		arp.setFirstName(randomeString().toUpperCase());
		arp.setLastName(randomeString().toUpperCase());
		arp.setEmail(randomeString()+"@gmail.com");
		arp.setTelephone(randomeNumber());
		
		
		String password = randomeAlphaNumeric();
		arp.setPassword(password);
		arp.setconfirmPassword(password);
		arp.setPrivacyPolicy();
		arp.clickContinue();
		
		
		logger.info("Validating Expected msg....");
		String confmsg = arp.getConfirmationMsg();
		if(confmsg.equals("Congratulations! Your new account has been successfully created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Congratulations! Your new account has been successfully created!");
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Fineshed TC001_AccountRegistrationTest...");
		

  }
}
