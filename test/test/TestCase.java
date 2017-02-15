package test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase {
	/**
	 * @uml.property  name="driver"
	 * @uml.associationEnd  
	 */
	private WebDriver driver;
	/**
	 * @uml.property  name="baseUrl"
	 */
	private String baseUrl;
	/**
	 * @uml.property  name="acceptNextAlert"
	 */
	private boolean acceptNextAlert = true;
	/**
	 * @uml.property  name="verificationErrors"
	 */
	private StringBuffer verificationErrors = new StringBuffer();

	/**
	 * @uml.property  name="risultato"
	 */
	public String risultato = "Results: 0";
	/**
	 * @uml.property  name="error"
	 */
	public String error = "Oh snap! Change a few things up and try submitting again.";

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "/Users/Barbara/Desktop/LAB/Selenium/geckodriver");
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	 @Test
	 public void test1() throws Exception {
	 driver.get(baseUrl + "TwitterFollowingFinder/");
	 new
	 Select(driver.findElement(By.id("age"))).selectByVisibleText("13-17");
	 new
	 Select(driver.findElement(By.id("gender"))).selectByVisibleText("Male");
	 driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	 Thread.sleep(1000);
	 assertFalse(driver.getPageSource().contains(risultato));
	 assertFalse(driver.getPageSource().contains(error));
	 }

	@Test
	public void test2() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("New York");
		driver.findElement(By.id("interests")).clear();
		driver.findElement(By.id("interests")).sendKeys("music love");
		driver.findElement(By.id("radioInt1")).click();
		driver.findElement(By.id("hashtags")).clear();
		driver.findElement(By.id("hashtags")).sendKeys("happy love");
		driver.findElement(By.id("radioHash1")).click();
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertFalse(driver.getPageSource().contains(risultato));
		assertFalse(driver.getPageSource().contains(error));
	}

	@Test
	public void test6() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("san diego");
		driver.findElement(By.id("interests")).clear();
		driver.findElement(By.id("interests")).sendKeys("love music");
		driver.findElement(By.id("radioInt1")).click();
		new Select(driver.findElement(By.id("age"))).selectByVisibleText("65-100");
		new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Male");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertFalse(driver.getPageSource().contains(risultato));
		assertFalse(driver.getPageSource().contains(error));
	}
	
	@Test
	public void test8() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("New York");
		driver.findElement(By.id("hashtags")).clear();
		driver.findElement(By.id("hashtags")).sendKeys("happy fitness");
		driver.findElement(By.id("radioHash2")).click();
		new Select(driver.findElement(By.id("age"))).selectByVisibleText("18-25");
		new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Female");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertFalse(driver.getPageSource().contains(risultato));
		assertFalse(driver.getPageSource().contains(error));
	}
	
	@Test
	public void test13() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Female");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertFalse(driver.getPageSource().contains(risultato));
		assertFalse(driver.getPageSource().contains(error));
	}
	
	@Test
	public void test21() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		driver.findElement(By.id("interests")).clear();
		driver.findElement(By.id("interests")).sendKeys("happy oscar");
		driver.findElement(By.id("radioInt2")).click();
		driver.findElement(By.id("hashtags")).clear();
		driver.findElement(By.id("hashtags")).sendKeys("love film");
		driver.findElement(By.id("radioHash2")).click();
		new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Female");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertFalse(driver.getPageSource().contains(risultato));
		assertFalse(driver.getPageSource().contains(error));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
