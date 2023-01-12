package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadInfoPage 
{
   @FindBy(xpath="//span[@class='dvHeaderText']") private WebElement pageHeader;
   @FindBy(xpath="(//input[@title='Duplicate [Alt+U]'])[1]") private WebElement duplicateButton;
   @FindBy(xpath="//a[@href='index.php?action=ListView&module=Leads&parenttab=Marketing']") private WebElement leadsLink;
   
    public NewLeadInfoPage(WebDriver driver)
    {
    	PageFactory.initElements(driver,this);
    }
    public String getPageHeader()
    {
    	return pageHeader.getText();
    }
    public void clickDuplicateButton()
    {
    	duplicateButton.click();
    	
    }
    public void clickOnLeadsLink()
    {
    	leadsLink.click();
    }
}
