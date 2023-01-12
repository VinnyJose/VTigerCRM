package genericLibImplementation;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import genericLibraries.ExcelFileUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;

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
	/*WebDriverManager.chromedriver().setup();
	ChromeOptions options=new ChromeOptions();
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
	driver.findElement(By.xpath("//a[.='Contacts']")).click();
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	String page_title=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	if(page_title.contains("Creating New Contact"))
	{
		System.out.println("Create new contact page is displayed");
	}
	Map<String,String> map= excel.getDataBasedOnKey("TestData", "Create Contact");
	String parent=web.getParentWindow();
	//String parent=driver.getWindowHandle();
	WebElement salutions=driver.findElement(By.name("salutationtype"));
	web.dropDown(map.get("First Name Salutation"), salutions);
	/*Select s=new Select(salutions);
	s.selectByVisibleText("Mrs.");*/
	String lastname=map.get("Last Name")+java.generateRandomNumber(100);
	driver.findElement(By.name("lastname")).sendKeys(lastname);
	driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
	web.getChildWindow();
	String organization=map.get("Organization Name");
	WebElement org = driver.findElement(By.xpath("(//a[@href='javascript:window.close();'])[3]"));
		String name = org.getText();
		if(name.contains(organization)) 
		{
			org.click();
			
		}
	
	/*Set<String> child=driver.getWindowHandles();
	for(String ch:child)
	{
		driver.switchTo().window(ch);
	}
	driver.findElement(By.xpath("//a[.='Airbnb']")).click();
	driver.switchTo().window(parent);*/
	web.switchToWindow(parent);
	WebElement img=driver.findElement(By.name("imagename"));
	web.scrollAndClick(img);
	/*Point p=img.getLocation();
	int x=p.getX();
	int y=p.getY();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy("+x+","+y+")");*/
	//img.click();
	img.sendKeys("C:\\Users\\jose_\\OneDrive\\Documents\\Qspider\\adv.selenium\\image\\20191026_154908.jpg");
	driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
	String page_text=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(page_text.contains(map.get("Last Name")))
	{
		System.out.println("contact information page is displayed");
	}
	driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")).click();
	String contact=driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[4]")).getText();
	if(contact.contains(map.get("Last Name")))
	{
		System.out.println("new contact is added successfully");
		System.out.println("Test passed");
	}
	else
	{
		System.out.println("Test failed");
	
	}
	WebElement administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	web.mouseOveroElement(administrator);
	 /*  Actions a=new Actions(driver);
	   a.moveToElement(administrator).perform();*/
	   driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	   driver.close();
	
	}
	
}
