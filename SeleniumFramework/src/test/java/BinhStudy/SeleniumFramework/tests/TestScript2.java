package BinhStudy.SeleniumFramework.tests;

import org.testng.annotations.Test;

import BinhStudy.SeleniumFramework.PageObjects.LandingPage;
import BinhStudy.SeleniumFramework.TestComponents.BaseTest;

public class TestScript2 extends BaseTest{
	@Test
	public void loginPlaceholderValidation() {
//		2.1 Verify placeholder
		landingPage.loginVerifyPlaceHolder();
//		2.2 click and check button was selected after click()
		landingPage.clickRememberMe();
	}
}
