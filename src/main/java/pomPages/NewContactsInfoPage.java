package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class contains element references and corresponding business libraries of Contacts information page
 */
public class NewContactsInfoPage 
{
   //Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']") private WebElement pageHeader;
	@FindBy(xpath="//a[@class='hdrLink']") private WebElement contactsLink;
	
	//Initialization
	public NewContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	//Utilization
	/*
	 * This method is used to get the page header
	 */
	public String getPageHeader()
	{
		return pageHeader.getText();
	}
	/*
	 * This method is used to click on contacts link
	 */
	public void clickContactsLink()
    {
		contactsLink.click();
    }
}
