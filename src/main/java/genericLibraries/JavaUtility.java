package genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*
 * This class contains all the reusable methods
 */
public class JavaUtility 
{
	/*
	 * this method is used to generate random number
	 * @param limit
	 * @return
	 */
public int generateRandomNumber(int limit)
{
	Random random=new Random();
	return random.nextInt(limit);
}
/*
 * This method is used to get current time
 * @return
 */
public String currentTime()
{
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("dd_mm_yyyy_hh_mm_sss");
	String actualdate=sdf.format(date);
	return actualdate;
	
}
}
