package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

/*
 * his class contains elements and corresponding business libraries for home page
 */
public class HomePage
{
	//declaration
  @FindBy(xpath="//a[@href='index.php?action=index&module=Home']") private WebElement pageHeader;
  @FindBy(xpath="//a[@href='index.php?module=Accounts&action=index']") private WebElement organizationTab;
  @FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") private WebElement administratorIcon;
  @FindBy(xpath="//a[.='Sign Out']") private WebElement signOutButton;
  @FindBy(xpath="//a[.='Contacts']") private WebElement contactsTab;
  @FindBy(xpath="//a[@href='index.php?module=Leads&action=index']") private WebElement leadsTab;
  
  //Initialization
  public HomePage(WebDriver driver)
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
	 * This method is used to click on contacts tab
	 */
	public void clickContactsTab()
	{
		contactsTab.click();
	}
	/*
	 * This method is used to click on Organization Tab
	 */
	public void clickOrganizationsTab()
	{
		organizationTab.click();
	}
	/*
	 * This method is used to click on Leads tab
	 */
	public void clickLeadsTab()
	{
		leadsTab.click();
	}
	/*
	 * This method is used to signout of the application
	 * 
	 */
	public void signOut(WebDriverUtility web)
	{
		web.mouseOveroElement(administratorIcon);
		signOutButton.click();
	}
}
