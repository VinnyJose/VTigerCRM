package genericLibImplementation;

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
		
		WebDriverManager.chromedriver().setup();
		/*ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));*/
		String image=driver.findElement(By.xpath("//a[@href='http://www.vtiger.com']")).getText();
		if(image.contains("vtiger"))
		{
			System.out.println("Login page is displayed");
		}
		driver.findElement(By.name("user_name")).sendKeys(property.getDataFromProperties("Username"));
		driver.findElement(By.name("user_password")).sendKeys(property.getDataFromProperties("Password"));
		driver.findElement(By.id("submitButton")).click();
		String homepagetext=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(homepagetext.contains("Home"))
		{
			System.out.println("Logined successfully");
		}
		driver.findElement(By.xpath("//a[@href='index.php?module=Leads&action=index']")).click();
		String leadpagetext=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(leadpagetext.contains("Leads"))
		{
			System.out.println("Leads page is displayed");
			
		}
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		String createleadpage=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(createleadpage.contains("Creating New Lead"))
		{
			System.out.println("create new lead page is displayed");
		}
		Map<String,String> map=excel.getDataBasedOnKey("TestData","Create Lead");
		String salutation=map.get("First Name Salutation");
	    WebElement salutationDropdown=driver.findElement(By.name("salutationtype"));
	    web.dropDown(salutationDropdown, salutation);
		/*&Select s1=new Select(salutationDropdown);
		s1.selectByVisibleText("Ms.");*/
		driver.findElement(By.name("lastname")).sendKeys((map.get("Last Name"))+java.generateRandomNumber(100));
		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
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
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
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
