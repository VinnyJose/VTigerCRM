package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * This class contains methods to perform actions on an excel file
 */
public class ExcelFileUtility 
{
  FileInputStream fis;
  FileOutputStream fos;
  Workbook workbook;
  DataFormatter df;
  /*
   * This method is used to initialize excel file
   * @param excelpath
   */
  public void excelInitialization(String excelpath)
  {
	  try {
		fis=new FileInputStream(excelpath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	 
		try {
			workbook=WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	
	 
  }
  /*
   * This method is used to fetch single data from excel
   * @param sheetname
   * @param ownum
   * @param cellnum
   */
  public String getSingleDataFromExcel(String sheetname, int rownum, int cellnum)
  {
	  df=new DataFormatter();
	  Sheet sheet=workbook.getSheet(sheetname);
	  return df.formatCellValue(sheet.getRow(rownum).getCell(cellnum));
  }
  /*
   * This method is used to fetch multiple data from excel
   * @param sheetname
   * @param expectedTestCase
   */
  public Map<String,String> getDataBasedOnKey(String sheetname, String expectedTestCase)
  {
	  df=new DataFormatter();
	  Map<String,String> map=new HashMap<>();
	  Sheet sheet=workbook.getSheet(sheetname);
	  
	  for(int i=0; i<= sheet.getLastRowNum(); i++) 
	  {
			if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestCase))
			{
				for(int j=i; j<= sheet.getLastRowNum(); j++) 
				{
					map.put(df.formatCellValue(sheet.getRow(j).getCell(2)), df.formatCellValue(sheet.getRow(j).getCell(3)));
					if(df.formatCellValue(sheet.getRow(j).getCell(2)).equals("####"))
					break;

				}
				break;			    
			}
		}
		return map;
  }
  /*
   * This method is used to update test script status to excel
   * @param sheetname
   * @param status
   * @param expectedTestCase
   * @param excelpath
   */
  public void updateTestStatusInExcel(String sheetname,String expectedTestCase,String status,String excelpath)
  {
	  df=new DataFormatter();
	  Sheet sheet=workbook.getSheet(sheetname);
	  
	  for(int i=0;i<=sheet.getLastRowNum();i++)
	  {
		  if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestCase))
		  {
			  Cell cell=sheet.getRow(i).createCell(4);
			  cell.setCellValue(status);
			  break;
		  }
	  }
	  try {
		fos=new FileOutputStream(excelpath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  try {
		workbook.write(fos);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  try {
		fos.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
  /*
   * This method is used to close the excel workbook
   */
  public void closeExcel()
  {
	  try {
		workbook.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
}
