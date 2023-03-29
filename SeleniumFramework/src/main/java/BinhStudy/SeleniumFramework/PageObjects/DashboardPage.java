package BinhStudy.SeleniumFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import BinhStudy.SeleniumFramework.AbstractComponents.AbstractComponents;

public class DashboardPage extends AbstractComponents{
	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
