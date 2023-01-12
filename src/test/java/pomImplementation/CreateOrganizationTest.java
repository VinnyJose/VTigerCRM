package pomImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrganizationTest extends BaseClass
{
	@Test
	public void creatOrganizationTest()
	{
		SoftAssert sassert=new SoftAssert();
		home.clickOrganizationsTab();
		sassert.assertTrue(organizations.getPageHeader().equals("Creating New Organization"));
		
		organizations.clickPlusButton();
		sassert.assertTrue(createOrganization.getPageHeader().contains("Creating New Organization"));
		
        Map<String,String> map = excel.getDataBasedOnKey("TestData", "Create Organization");
		String organizationName = map.get("Organization Name") + java.generateRandomNumber(100);
		createOrganization.setOrganizationName(organizationName);
		createOrganization.selectIndustry(web, map.get("Industry"));
		createOrganization.clickSaveButton();
		
		sassert.assertTrue(newOrganizationInfo.getPageHeader().contains(organizationName));
		
		newOrganizationInfo.clickOrgLink();
		sassert.assertTrue(organizations.getNewOrganization().equals(organizationName));
		
		if(organizations.getNewOrganization().equals(organizationName))
		{
			excel.updateTestStatusInExcel("TestData", "Create Organization", "Pass", IConstantPath.EXCEL_FILE_PATH);
		}else
		{
			excel.updateTestStatusInExcel("TestData", "Create Organization", "Fail", IConstantPath.EXCEL_FILE_PATH);
		}
		 sassert.assertAll();
	
	}
}
