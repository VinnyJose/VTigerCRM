package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains element references and respective business libraries for login page
 * 
 *
 */
public class LoginPage 
{
   //declaration
	@FindBy(xpath="//a[@href='http://www.vtiger.com']") private WebElement pageHeader;
	@FindBy(name="user_name") private WebElement usernameTextField;
	@FindBy(name="user_password") private WebElement passwordTextField;
	@FindBy(id="submitButton") private WebElement submitButton;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//Utilization
	/*
	 * This method returns page header text
	 */
	public String getPageHeader()
	{
		return pageHeader.getText();
		
	}
	/*
	 * This method is used to login the application
	 */
	 public void loginToApplication(String username,String password)
	 {
		 usernameTextField.sendKeys(username);
		 passwordTextField.sendKeys(password);
		 submitButton.click();
	 }
}
