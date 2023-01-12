package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage
{
 @FindBy(xpath="//a[@class='hdrLink']") private WebElement pageHeader;
 @FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement plusbutton;
 @FindBy(xpath="//table[@class='lvt small']/descendant::tr[last()]/td[3]") private WebElement newLead;
 
public LeadsPage(WebDriver driver)
{
  PageFactory.initElements(driver,this);	
}
public String getPageHeader()
{
	return pageHeader.getText();
}
public void clickPlusButton()
{
	plusbutton.click();
}
public String getNewLead()
{
	return newLead.getText();
}
}