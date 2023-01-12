package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pomPages.ContactsPage;
import pomPages.CreateLeadPage;
import pomPages.CreateNewContactsPage;
import pomPages.CreateNewOrganizationsPage;
import pomPages.DuplicateLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewContactsInfoPage;
import pomPages.NewLeadInfoPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;

public class BaseClass 
{
   protected WebDriver driver;
   protected ExcelFileUtility excel;
   //protected IConstantPath path;
   protected JavaUtility java;
   protected PropertyFileUtility property;
   protected WebDriverUtility web;
   protected ContactsPage contacts;
   protected CreateLeadPage createLead;
   protected CreateNewContactsPage createContact;
   protected CreateNewOrganizationsPage createOrganization;
   protected HomePage home;
   protected LeadsPage leads;
   protected LoginPage login;
   protected NewContactsInfoPage newContactInfo;
   protected NewOrganizationInfoPage newOrganizationInfo;
   protected OrganizationsPage organizations;
   protected NewLeadInfoPage newLeadInfo;
   protected DuplicateLeadPage duplicate;
   public static WebDriver sdriver;
   public static JavaUtility sjavaUtil;
   
   @BeforeClass
   public void classSetUp()
   {
	   web=new WebDriverUtility();
	   excel=new ExcelFileUtility();
	   java=new JavaUtility();
	   sjavaUtil=java;
	   property=new PropertyFileUtility();
	   
	   property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
	   excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
	   //WebDriverManager.chromedriver().setup();
	   String url=property.getDataFromProperties("url");
       String browser=property.getDataFromProperties("browser");
	   long time=Long.parseLong(property.getDataFromProperties("timeouts"));
	   //driver=web.openApplication(System.getProperty("browser"), url, time);
	   driver=web.openApplication(browser, url, time);
	   
	   sdriver=driver;
	   login=new LoginPage(driver);
	   Assert.assertTrue(login.getPageHeader().contains("vtiger"));
   }
   
   @BeforeMethod
   public void methodSetup()
   {
	  
	   home=new HomePage(driver);
	   contacts=new ContactsPage(driver);
	   leads=new LeadsPage(driver);
	   organizations=new OrganizationsPage(driver);
	   createContact=new CreateNewContactsPage(driver);
	   createLead=new CreateLeadPage(driver);
	   createOrganization=new CreateNewOrganizationsPage(driver);
	   newContactInfo=new NewContactsInfoPage(driver);
	   duplicate=new DuplicateLeadPage(driver);
	   newLeadInfo=new NewLeadInfoPage(driver);
	   newOrganizationInfo=new NewOrganizationInfoPage(driver);
	   
	   
	   String username=property.getDataFromProperties("Username");
	   String password=property.getDataFromProperties("Password");
	   login.loginToApplication(username, password);
	   Assert.assertTrue(home.getPageHeader().contains("Home"));
	   
   }
   @AfterMethod
   public void methodtearDown()
   {
	   home.signOut(web);;
   }
   
   @AfterClass
   public void classTearDown()
   {
	   web.closebrowser();
	   excel.closeExcel();
   }
}
