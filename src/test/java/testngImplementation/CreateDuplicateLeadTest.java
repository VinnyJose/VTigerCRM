package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateDuplicateLeadTest extends BaseClass{

	@Test
	public void duplicateLead()
	{
		
		home.clickLeadsTab();
		SoftAssert sassert=new SoftAssert();
		sassert.assertTrue(leads.getPageHeader().contains("Leads"));
		
		leads.clickPlusButton();
		sassert.assertTrue(createLead.getPageHeader().contains("Creating New Lead"));
		
		Map<String,String> map=excel.getDataBasedOnKey("TestData","Create Lead");
	    createLead.selectSalutation(web, map.get("First Name Salutation"));
	    String lastName=map.get("Last Name")+java.generateRandomNumber(100);
	    createLead.setLastName(lastName);
		createLead.setCompany(map.get("Company"));
		
		
		createLead.clickSaveButton();
		sassert.assertTrue(newLeadInfo.getPageHeader().contains(lastName));
		newLeadInfo.clickDuplicateButton();
		sassert.assertTrue(duplicate.getPageHeader().contains("Duplicating"));
		String newLastName=map.get("New Last Name")+java.generateRandomNumber(100);
		duplicate.setLastName(newLastName);
		duplicate.clickonSaveButton();
		sassert.assertTrue(newLeadInfo.getPageHeader().contains(newLastName));
		newLeadInfo.clickOnLeadsLink();
		sassert.assertTrue(leads.getPageHeader().contains("Leads"));
		//sassert.assertTrue(leads.getNewLead().contains(newLastName));
		if(leads.getNewLead().equals(newLastName))
		
			excel.updateTestStatusInExcel("TestData", "Create Lead", "Pass", IConstantPath.EXCEL_FILE_PATH);
		
			else 
			
				excel.updateTestStatusInExcel("TestData", "Create Lead", "Fail", IConstantPath.EXCEL_FILE_PATH);
			
		sassert.assertAll();
	}

}
