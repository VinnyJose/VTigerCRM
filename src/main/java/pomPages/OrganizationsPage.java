package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	//Declaration
	 @FindBy(xpath="//a[@class='hdrLink']") private WebElement pageHeader;
     @FindBy(xpath="//img[contains(@title,'Create Organization...')]") private WebElement plusButton;
     @FindBy(xpath="//table[@class='lvt small']/descendant::tr[last()]/td[3]") private WebElement newOrganization;
 
 
 
  //Initialization
  public OrganizationsPage(WebDriver driver)
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
 	 * This method is used to click on add organization button
 	 */
 	public void clickPlusButton()
 	{
 		plusButton.click();
 	}
 	/*
 	 * This method is used to get text of newly added organization
 	 */
 	public String getNewOrganization()
 	{
 		return newOrganization.getText();
 	}
}
