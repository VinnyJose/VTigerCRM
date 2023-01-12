package pomImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import genericLibraries.ExcelFileUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomPages.CreateLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;

public class CreateDuplicateLeadTest {

	public static void main(String[] args) 
	{
		PropertyFileUtility property=new PropertyFileUtility();
		JavaUtility java=new JavaUtility();
		WebDriverUtility web=new WebDriverUtility();
		ExcelFileUtility excel=new ExcelFileUtility();
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
		
		String url=property.getDataFromProperties("url");
		String browser=property.getDataFromProperties("browser");
		long time=Long.parseLong(property.getDataFromProperties("timeouts"));
		WebDriver driver=web.openApplication(browser, url, time);
		
		LoginPage login=new LoginPage(driver);
		HomePage home=new HomePage(driver);
		LeadsPage leads=new LeadsPage(driver);
		CreateLeadPage newLead=new CreateLeadPage(driver);
		
		WebDriverManager.chromedriver().setup();
		
		if(login.getPageHeader().contains("vtiger"))
		{
			System.out.println("Login page is displayed");
		}

		driver.findElement(By.name("user_name")).sendKeys(property.getDataFromProperties("Username"));
		driver.findElement(By.name("user_password")).sendKeys(property.getDataFromProperties("Password"));
		driver.findElement(By.id("submitButton")).click();
		
		if(home.getPageHeader().contains("Home"))
		{
			System.out.println("Logined successfully");
		}
		home.clickLeadsTab();
		
		if(leads.getPageHeader().contains("Leads"))
		{
			System.out.println("Leads page is displayed");
			
		}
	    leads.clickPlusButton();
		
		if(newLead.getPageHeader().contains("Creating New Lead"))
		{
			System.out.println("create new lead page is displayed");
		}
		Map<String,String> map=excel.getDataBasedOnKey("TestData","Create Lead");
	    newLead.selectSalutation(web, map.get("First Name Salutation"));
	    String lastName=map.get("Last Name")+java.generateRandomNumber(100);
	    newLead.setLastName(lastName);
		newLead.setCompany(map.get("Company"));
		
		/*WebElement leadsourse=driver.findElement(By.name("leadsource"));
		Select s2=new Select(leadsourse);
		s2.selectByVisibleText("Web Site");
		WebElement industry=driver.findElement(By.name("industry"));
		Select s3=new Select(industry);
		s3.selectByVisibleText("Banking");
		driver.findElement(By.id("mobile")).sendKeys("8860457281");
		driver.findElement(By.id("email")).sendKeys("paul123@gmail.com");
		WebElement leadstatus=driver.findElement(By.name("leadstatus"));
		Select s4=new Select(leadstatus);
		s4.selectByVisibleText("Contacted");
		WebElement rating=driver.findElement(By.name("rating"));
		Select s5=new Select(rating);
		s5.selectByVisibleText("Active");*/
		newLead.clickSaveButton();
		
		String leadinformation=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(leadinformation.contains(map.get("Last Name")+"  -  Lead Information"))
		{
			System.out.println("Lead information page is displayed");
		}
		driver.findElement(By.xpath("(//input[@title='Duplicate [Alt+U]'])[1]")).click();
		String duplicatepage=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(duplicatepage.contains("Duplicating"))
		{
			System.out.println("Lead details displayed in edittable mode");
		}
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys((map.get("New Last Name"))+java.generateRandomNumber(100));
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Leads&parenttab=Marketing']")).click();
		String leadpagetext1=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(leadpagetext1.contains("Leads"))
		{
			System.out.println("Leads page is displayed");
		}
		
		String modifiedlead=driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]")).getText();
		if(modifiedlead.contains(map.get("New Last Name")))
		{
			System.out.println("Modified lead name is added to the leads page");
		}
		   WebElement administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		   Actions a=new Actions(driver);
		   a.moveToElement(administrator).perform();
		   driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		   driver.close();
	}

}
