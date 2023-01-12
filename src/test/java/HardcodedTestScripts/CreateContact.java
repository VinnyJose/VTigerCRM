package HardcodedTestScripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact
{
	
    
	public static void main(String[] args)
	{
	WebDriverManager.chromedriver().setup();
	ChromeOptions options=new ChromeOptions();
	options.addArguments("--disable-notifications");
	WebDriver driver=new ChromeDriver(options);
	driver.manage().window().maximize();
	driver.get("http://localhost:8888/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String image=driver.findElement(By.xpath("//a[@href='http://www.vtiger.com']")).getText();
	if(image.contains("vtiger"))
	{
		System.out.println("Login page is displayed");
	}
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
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
	String parent=driver.getWindowHandle();
	WebElement salutions=driver.findElement(By.name("salutationtype"));
	Select s=new Select(salutions);
	s.selectByVisibleText("Mrs.");
	driver.findElement(By.name("lastname")).sendKeys("John");
	driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
	Set<String> child=driver.getWindowHandles();
	for(String ch:child)
	{
		driver.switchTo().window(ch);
	}
	driver.findElement(By.xpath("//a[.='Airbnb']")).click();
	driver.switchTo().window(parent);
	WebElement img=driver.findElement(By.name("imagename"));
	Point p=img.getLocation();
	int x=p.getX();
	int y=p.getY();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy("+x+","+y+")");
	//img.click();
	img.sendKeys("C:\\Users\\jose_\\OneDrive\\Documents\\Qspider\\adv.selenium\\image\\20191026_154908.jpg");
	driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
	String page_text=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(page_text.contains("John  -  Contact Information"))
	{
		System.out.println("contact information page is displayed");
	}
	driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")).click();
	String contact=driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[4]")).getText();
	if(contact.contains("John"))
	{
		System.out.println("new contact is added successfully");
		System.out.println("Test passed");
	}
	else
	{
		System.out.println("Test failed");
	
	}
	WebElement administrator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	   Actions a=new Actions(driver);
	   a.moveToElement(administrator).perform();
	   driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	   driver.close();
	
	}
	
}
