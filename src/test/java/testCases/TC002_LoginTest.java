package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccoutPage;

public class TC002_LoginTest extends BaseClass{	

	@Test(groups={"Sanity","Master"})
   public void verify_login()
   {
	   logger.info(" Starting TC002_LoginTest");
	   try 
	   {
		   HomePage hp = new HomePage(driver);
		   hp.clickMyAccount();
		   hp.clickLoginLink(); 
		   
		   LoginPage lp = new LoginPage(driver);
		   lp.setEmail(p.getProperty("email"));
		   //lp.setPassword(p.getProperty("password"));
		   lp.setPassword(p.getProperty("password"));
		   lp.clickLogin();
		   
		   MyAccoutPage macc = new MyAccoutPage(driver);
		   boolean targetPage = macc.inMyAccountPageExists();
		   Assert.assertTrue (targetPage);
		   
	   }
	   catch(Exception e)
	   {
		   Assert.fail();
	   }
	   
	   logger.info(" Finished TC002_LoginTest");
   }
}
