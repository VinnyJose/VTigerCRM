package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * This class contains element references and corresponding business libraries
 */
public class ContactsPage
{
 //declaration
	@FindBy(xpath="//a[@class='hdrLink']") private WebElement pageHeader;
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement plusButton;
	@FindBy(xpath="//table[@class='lvt small']/descendant::tr[last()]/td[4]") private WebElement newContact;
	 //Initialization
	  public ContactsPage(WebDriver driver)
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
		 * This method is used to click on plus button
		 */
		public void clickPlusButton()
		{
			plusButton.click();
		}
		/*
		 * This method is used to get new contact text
		 */
		public String getNewContact()
		{
			return newContact.getText();
		}
}
