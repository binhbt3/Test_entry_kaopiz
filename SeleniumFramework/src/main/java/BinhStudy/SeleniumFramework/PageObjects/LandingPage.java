package BinhStudy.SeleniumFramework.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import java.util.List;

import BinhStudy.SeleniumFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='text']")
	private WebElement userEmail;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement userPassword;

	@FindBy(css = "button[type='submit']")
	private WebElement loginButton;

	@FindBy(css = ".display-4")
	private WebElement dashBoardVerify;

	@FindBy(xpath = "//div[@class='mb-2'][1]")
	private WebElement emailHolder;

	@FindBy(xpath = "//div[@class='mb-2'][2]")
	private WebElement passwordHolder;

	@FindBy(css = ".iCheck-helper")
	private WebElement rememberMeButton;

	@FindBy(css = ".checkbox div")
	private WebElement verifySelectRememberMe;

	public boolean loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();

		if (dashBoardVerify.getText().equalsIgnoreCase("Dashboard"))
			return true;
		else {
			return false;
		}
	}

	public void loginVerifyPlaceHolder() {
		List <WebElement> elements = driver.findElements(By.xpath("//div[@class='mb-2']"));
		for (WebElement ele: elements) {
			if (ele.findElement(By.xpath("label/input")).getAttribute("name").equals("email")) {
				String emailPlaceholder = ele.getText();
				System.out.println("emailPlaceholder: " + emailPlaceholder);
				Assert.assertTrue(emailPlaceholder.equals("Email"));
			} else {
				String passwordPlaceholder = ele.getText();
				System.out.println("passwordPlaceholder: " + passwordPlaceholder);
				Assert.assertTrue(passwordPlaceholder.equals("Password"));
			}
		}
	}

	public void clickRememberMe() {
		rememberMeButton.click();
		Assert.assertTrue(verifySelectRememberMe.getAttribute("class").contains("checked"));
	}

	public void goTo() {
		driver.get("https://phptravels.net/api/admin");
	}

}
