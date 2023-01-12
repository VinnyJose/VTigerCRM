package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class contains element references and respective business libraries for create Organizations page
 * 
 *
 */
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateNewOrganizationsPage 
{
	
	   //declaration
		@FindBy(xpath="//span[text()='Creating New Organization']") private WebElement pageHeader;
		@FindBy(name="accountname") private WebElement organization;
		@FindBy(name="industry") private WebElement industryDropDown;
		@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") private WebElement saveButton;
		
		//initialization
		public CreateNewOrganizationsPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}
		//utilization
		/*
		 * This method is used to get page header
		 */
		public String getPageHeader()
		{
			return pageHeader.getText();
		}
		/*
		 * This method is used to set organization name
		 */
		public void setOrganizationName(String orgName)
		{
			organization.sendKeys(orgName);
		}
		/*
		 * This method is used to select industry from drop down
		 */
		public void selectIndustry(WebDriverUtility web,String industryName)
		{
			web.dropDown(industryName,industryDropDown);
		}
		/*
		 * This method is used to click on save button
		 */
		public void clickSaveButton()
		{
			saveButton.click();
		}
}
