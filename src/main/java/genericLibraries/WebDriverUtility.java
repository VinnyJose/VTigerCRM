package genericLibraries;
/*
 * This class contains all reusable methods to perform WebDriver actions
 * 
 */

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility 
{
	WebDriver driver;
	/*
	 * This method is used to open the application
	 * @param browser
     * @param url
     * @param time
	 */
	
	public WebDriver openApplication(String browser, String url, long time)
	{
		switch(browser)
		{
		case "chrome" : WebDriverManager.chromedriver().setup();
		                driver=new ChromeDriver();
		                break;
		case "firefox":WebDriverManager.firefoxdriver().setup();
		               driver=new FirefoxDriver();
		               break;
		case "edge"   :WebDriverManager.edgedriver().setup();
		               driver=new EdgeDriver();
		               break;
		               
		default       :System.out.println("Invalid browser name");
		               break;
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		return driver;
	}
	/*
	 * This method is used to perform mouse overing actions
	 * @element
	 */
	public void mouseOveroElement(WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();			
	}
	public void dropDown(WebElement element, int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);	
	}
	public void dropDown(WebElement element, String value)
	{
		Select s=new Select(element);
		s.selectByValue(value);	
	}
	public void dropDown(String text,WebElement element)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);	
	}
	public void switchToFrame(String index)
	{
		driver.switchTo().frame(index);
	}
	public void switchBackFrame()
	{
		driver.switchTo().defaultContent();
	}
	public String getParentWindow()
	{
		return driver.getWindowHandle();
	}
	public void getChildWindow()
	{
		Set<String> child=driver.getWindowHandles();
		for(String s:child)
		{
			driver.switchTo().window(s);
		}
	}
	public void switchToWindow(String windowid)
	{
		driver.switchTo().window(windowid);
	}
	public void scrollAndClick(WebElement img)
	{
		Point p=img.getLocation();
		int x=p.getX();
		int y=p.getY();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy("+x+","+y+")");
	}
	public String getScreenShot(JavaUtility javaUtility,WebDriver driver, String result)
	{
		String currenttime=javaUtility.currentTime();
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File des=new File("./screenshot/"+result+"_"+currenttime+".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return des.getAbsolutePath();
	}
	public String getScreenShot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		String src=ts.getScreenshotAs(OutputType.BASE64);
		return src;
	}
	public void closebrowser() {
		driver.quit();
	}
}
