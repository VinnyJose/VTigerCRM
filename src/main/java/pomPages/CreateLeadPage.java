package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateLeadPage 
{
 @FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement pageHeader;
 @FindBy(name="salutationtype") private WebElement salutationDropdrown;
 @FindBy(name="lastname") private WebElement lastNameTextField;
 @FindBy(name="company") private WebElement companyTextField;
 @FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]") WebElement saveButton;
 
 public CreateLeadPage(WebDriver driver)
 {
	 PageFactory.initElements(driver,this);
 }
 public String getPageHeader()
 {
	 return pageHeader.getText();
 }
 public void selectSalutation(WebDriverUtility web,String text)
 {
	 web.dropDown(text,salutationDropdrown);
 }
 public void setLastName(String lastName)
 {
	 lastNameTextField.sendKeys(lastName);
 }
 public void setCompany(String company)
 {
	 companyTextField.sendKeys(company);
 }
 public void clickSaveButton()
 {
	 saveButton.click();
 }
}
