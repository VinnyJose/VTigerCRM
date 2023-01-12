package pomImplementation;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import genericLibraries.ExcelFileUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomPages.ContactsPage;
import pomPages.CreateNewContactsPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewContactsInfoPage;

public class CreateContact
{
	
    
	public static void main(String[] args)
	{
		ExcelFileUtility excel=new ExcelFileUtility();
		JavaUtility java=new JavaUtility();
		PropertyFileUtility property=new PropertyFileUtility();
		WebDriverUtility web=new WebDriverUtility();
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
		String url=property.getDataFromProperties("url");
		String browser=property.getDataFromProperties("browser");
		long time=Long.parseLong(property.getDataFromProperties("timeouts"));
		WebDriver driver=web.openApplication(browser, url, time);
	    WebDriverManager.chromedriver().setup();
	
	   LoginPage login=new LoginPage(driver);
	   HomePage home=new HomePage(driver);
	   ContactsPage contactsPage=new ContactsPage(driver);
	   CreateNewContactsPage newContact=new CreateNewContactsPage(driver);
	   NewContactsInfoPage newContactInfo=new NewContactsInfoPage(driver);	
	   
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
	home.clickContactsTab();
	if(contactsPage.getPageHeader().equals("Contacts"))
	{
		System.out.println("Contact page is displayed");
	}
	contactsPage.clickPlusButton();
	
	if(newContact.getPageHeader().contains("Creating New Contact"))
	{
		System.out.println("Create new contact page is displayed");
	}
	
	Map<String,String> map= excel.getDataBasedOnKey("TestData", "Create Contact");
	
	
	newContact.selectSalutation(web, map.get("First Name Salutation"));
    String lastname=map.get("Last Name")+java.generateRandomNumber(100);
	newContact.setLastName(lastname);
	newContact.clickOrganizationPlusButton(web);
	newContact.uploadContactImage(map.get("Contact Image"),web);
	newContact.clickOnSaveButton();
	if(newContactInfo.getPageHeader().contains(lastname))
	{
		System.out.println("contact information page is displayed");
	}
	newContactInfo.clickContactsLink();
	if(contactsPage.getNewContact().contains(map.get("Last Name")))
	{
		System.out.println("new contact is added successfully");
		System.out.println("Test passed");
		excel.updateTestStatusInExcel("TestData", "Create Contact", "Pass", IConstantPath.EXCEL_FILE_PATH);
	}
	else
	{
		System.out.println("Test failed");
		excel.updateTestStatusInExcel("TestData", "Create Contact", "fail", IConstantPath.EXCEL_FILE_PATH);
	
	}
	home.signOut(web);
	web.closebrowser();
	excel.closeExcel();
	
	}
	
}
