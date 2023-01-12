package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;
/*
 * This class contains element references and corresponding business libraries of create new contact page
 */
public class CreateNewContactsPage 
{
	//declaration
@FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement pageHeader;
@FindBy(name="salutationtype") private WebElement salutationDropDown;
@FindBy(name="lastname") private WebElement lastNameTextField;
@FindBy(xpath="(//img[@src='themes/softed/images/select.gif'])[1]") private WebElement organizationPlusButton;
@FindBy(xpath="(//a[@href='javascript:window.close();'])[3]") private WebElement clickOrganization;
@FindBy(name="imagename") private WebElement contactImage;
@FindBy(xpath="(//input[@type='submit'])[2]") private WebElement saveButton;
//Initialization
public CreateNewContactsPage(WebDriver driver)
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
	 * This method is used to select salutation
	 */
	public void selectSalutation(WebDriverUtility web,String text)
	{
		web.dropDown(text,salutationDropDown);
	}
	/*
	 * This method is used to enter last name of contact
	 */
	public void setLastName(String lastName)
	{
		lastNameTextField.sendKeys(lastName);
	}
	/*
	 * This method is used to select existing organization
	 */
    
	public void clickOrganizationPlusButton(WebDriverUtility web)
	{
		
		String parentwindow=web.getParentWindow();
		organizationPlusButton.click();
		web.getChildWindow();
    	clickOrganization.click();
        web.switchToWindow(parentwindow);
        
	}
	
     /**
	 * This method is used to upload contact image
	 * @param imagePath
	 */
	public void uploadContactImage(String imagePath,WebDriverUtility web)
    {
		web.scrollAndClick(contactImage);
		contactImage.sendKeys(imagePath);
		
	}
	/*
	 * This method is used to click on save button
	 */
    public void clickOnSaveButton()
    {
    	saveButton.click();
    }
    		
}
