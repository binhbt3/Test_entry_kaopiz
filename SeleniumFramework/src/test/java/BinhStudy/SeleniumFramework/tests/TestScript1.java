package BinhStudy.SeleniumFramework.tests;

import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import BinhStudy.SeleniumFramework.TestComponents.BaseTest;

public class TestScript1  extends BaseTest{
	@Test(dataProvider="getData")
	public void loginValidation(HashMap<String, String> input) {
		boolean loginResut = landingPage.loginApplication(input.get("email"), input.get("password"));
		Assert.assertTrue(loginResut);
		
	}
	
	@DataProvider
	public Object[][] getData(){
		HashMap<String, String> account = new HashMap<String, String>();
		account.put("email", "admin@phptravels.com");
		account.put("password", "demoadmin");
		return new Object[][] {{account}};
	}
 }
