package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/*
 * This class contains methods to perform actions on properties file
 */

public class PropertyFileUtility 
{
   Properties p;
   FileInputStream fis;
   /*
    * This method is used to initialize properties file
    * @param filepath 
    */
   public void propertyFileInitialization(String filepath)
   {
	   try {
		fis=new FileInputStream(filepath);
	       } 
	   catch (FileNotFoundException e) 
	   {
		e.printStackTrace();
	   }
	   p=new Properties();
	   try {
		p.load(fis);
	       } 
	   catch (IOException e) 
	   {
		e.printStackTrace();
	   }
   }   
	   /*
	    * This method is used to fetch data from properties file
	    * @param key
	    * @return
	    */
	   public String getDataFromProperties(String key)
	   {
		   return p.getProperty(key);
	   }
   
}
