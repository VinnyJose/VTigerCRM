package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class contains element references and corresponding business libraries
 */
public class NewOrganizationInfoPage 
{
	//declaration
  @FindBy(xpath="//span[@class='dvHeaderText']") private WebElement pageHeader;
  @FindBy(xpath="//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']") private WebElement orgLink;
  //Initialization
  public NewOrganizationInfoPage(WebDriver driver)
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
	 * This method is used to click on organizations link
	 */
	public void clickOrgLink()
	{
		orgLink.click();
	}
  
}
