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

public class CreateOrganizationTest 
{
	
	public static void main(String[] args)
	{
		WebDriverUtility web=new WebDriverUtility();
		PropertyFileUtility property=new PropertyFileUtility();
		ExcelFileUtility excel=new ExcelFileUtility();
		JavaUtility java=new JavaUtility();
		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
		
		String url=property.getDataFromProperties("url");
		String browser=property.getDataFromProperties("browser");
		long time=Long.parseLong(property.getDataFromProperties("timeouts"));
		WebDriver driver=web.openApplication(browser, url, time);
		
		WebDriverManager.chromedriver().setup();
		/*ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  */
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
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[contains(@title,'Create Organization...')]")).click();
	    WebElement pageheader=driver.findElement(By.xpath("//span[text()='Creating New Organization']"));
		String title=pageheader.getText();
		if(title.equals("Creating New Organization"))
		{
			System.out.println("Creating new Organization page is displayed");
		}
        Map<String,String> map = excel.getDataBasedOnKey("TestData", "Create Organization");
		String organizationName = map.get("Organization Name") + java.generateRandomNumber(100);
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		WebElement industry=driver.findElement(By.name("industry"));
		web.dropDown(map.get("Industry"), industry);
		/*Select s=new Select(industry);
		s.selectByVisibleText("Recreation");*/
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String org_details=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(org_details.contains("organizationName"))
		{
		System.out.println("organization details page is displayed");
		}
		driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']")).click();
		WebElement org1=driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]"));
		String org1_name=org1.getText();
		
		if(org1_name.contains("organizationName"))
		{
			System.out.println("New organization is created");
		}else
		{
			System.out.println("organization is not created");
		}
	   WebElement administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	   Actions a=new Actions(driver);
	   a.moveToElement(administrator).perform();
	   driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	   driver.close();
	
	}
}
