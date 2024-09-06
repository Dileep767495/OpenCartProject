package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement EnterEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement EnterPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement clickLoginbtn;
	
	public void setEmail(String email) 
	{
		EnterEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd) 
	{
		EnterPassword.sendKeys(pwd);
	}
	
	public void clickLogin() 
	{
		clickLoginbtn.click();
	}
}	
	