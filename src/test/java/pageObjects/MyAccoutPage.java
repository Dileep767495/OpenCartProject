package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccoutPage extends BasePage{

	public MyAccoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement MyAccountmsgHeadding;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;

  public boolean inMyAccountPageExists() 
  {
	  try 
	  {
		  return (MyAccountmsgHeadding.isDisplayed());
	  }
	  catch(Exception e)
	  {
		  return false;
	  }
	
  }
  
  public void clickLogout()
  {
	  lnkLogout.click();
  }
}