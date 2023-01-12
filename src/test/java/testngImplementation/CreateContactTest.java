package testngImplementation;

import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateContactTest extends BaseClass
{
	
    @Test
	public void creatContact()
	{
	home.clickContactsTab();
	SoftAssert sassert=new SoftAssert();
	sassert.assertTrue(contacts.getPageHeader().equals("Contacts"));
	
	contacts.clickPlusButton();
	
	sassert.assertTrue(createContact.getPageHeader().contains("Creating New Contact"));
	
	Map<String,String> map= excel.getDataBasedOnKey("TestData", "Create Contact");
	createContact.selectSalutation(web, map.get("First Name Salutation"));
    String lastname=map.get("Last Name")+java.generateRandomNumber(100);
    createContact.setLastName(lastname);
    createContact.clickOrganizationPlusButton(web);
    createContact.uploadContactImage(map.get("Contact Image"),web);
    createContact.clickOnSaveButton();
    sassert.assertTrue(newContactInfo.getPageHeader().contains(lastname));
	
	newContactInfo.clickContactsLink();
	sassert.assertTrue(contacts.getNewContact().contains(lastname));
	if(contacts.getNewContact().equals(lastname))
	{
		
		excel.updateTestStatusInExcel("TestData", "Create Contact", "Pass", IConstantPath.EXCEL_FILE_PATH);
	}
	else
	{
		
		excel.updateTestStatusInExcel("TestData", "Create Contact", "fail", IConstantPath.EXCEL_FILE_PATH);
	
	}
	sassert.assertAll();
	
	
	}
	
}
