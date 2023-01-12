package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicateLeadPage 
{
 @FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement pageHeader;
 @FindBy(name="lastname") private WebElement lastNameTextField;
 @FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]") private WebElement saveButton;
 
 public DuplicateLeadPage(WebDriver driver)
 {
	 PageFactory.initElements(driver,this);
 }
 public String getPageHeader()
 {
	 return pageHeader.getText();
 }
 public void setLastName(String lastName)
 {
	 lastNameTextField.clear();
	 lastNameTextField.sendKeys(lastName);
 }
 public void clickonSaveButton()
 {
	 saveButton.click();
 }
}
