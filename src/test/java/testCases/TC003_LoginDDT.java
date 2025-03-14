package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccoutPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass= DataProviders.class, groups = {"dataDriven", "Master"})// getting data provider from diffrent class
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("Starting TC003_LoginDDT");
		
		try
		{
			   HomePage hp = new HomePage(driver);
			   hp.clickMyAccount();
			   hp.clickLoginLink(); 
			   
			   LoginPage lp = new LoginPage(driver);
			   lp.setEmail(email);
			   lp.setPassword(pwd);
			   lp.clickLogin();
			   
			   MyAccoutPage macc = new MyAccoutPage(driver);
			   boolean targetPage = macc.inMyAccountPageExists();
			   
			   if(exp.equalsIgnoreCase("Valid"))
			   { 
				   if(targetPage==true)
				   {
					   macc.clickLogout();
					   Assert.assertTrue(true);
				   }
				   else
				   {
					   Assert.assertTrue(false);
				   }
			    }
			   if(exp.equalsIgnoreCase("Invalid"))
			   {
				   if(targetPage==true)
				   {
					   macc.clickLogout();
					   Assert.assertTrue(false);
				   }
				   else
				   {
					   Assert.assertTrue(true);
				   }
			   }
			
	      }catch(Exception e)
	           {
	        	   Assert.fail();
	           }
		logger.info("Finished TC003_LoginDDT");
			   
  }
}
	
	

