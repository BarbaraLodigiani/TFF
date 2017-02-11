package test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SingoliTest {
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

	// //Interests AND
	// @Test
	// public void testSingTEST1() throws Exception {
	// driver.get(baseUrl + "TwitterFollowingFinder/");
	// driver.findElement(By.id("interests")).clear();
	// driver.findElement(By.id("interests")).sendKeys("love");
	// driver.findElement(By.id("radioInt1")).click();
	// driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	// Thread.sleep(1000);
	// assertFalse(driver.getPageSource().contains(risultato));
	// }
	//
	// //Hashtags OR
	// @Test
	// public void testSingTEST4() throws Exception {
	// driver.get(baseUrl + "TwitterFollowingFinder/");
	// driver.findElement(By.id("hashtags")).clear();
	// driver.findElement(By.id("hashtags")).sendKeys("love");
	// driver.findElement(By.id("radioHash2")).click();
	// driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	// Thread.sleep(1000);
	// assertFalse(driver.getPageSource().contains(risultato));
	// }

	// Location non presente nell'indice
	@Test
	public void testSingTEST5() throws Exception {
		driver.get(baseUrl + "TwitterFollowingFinder/");
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("Parigi");
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
		Thread.sleep(1000);
		assertTrue(driver.getPageSource().contains(error));
	}

	// Interests + ricerca in OR
	@Test
	  public void testSingTEST7() throws Exception {
	    driver.get(baseUrl + "TwitterFollowingFinder/");
	    driver.findElement(By.id("interests")).clear();
	    driver.findElement(By.id("interests")).sendKeys("sanremo pippo");
	    driver.findElement(By.id("radioInt2")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	    Thread.sleep(1000);
	    assertTrue(driver.getPageSource().contains(error));
	  }
	
	//Hahstags + ricerca in AND
	@Test
	  public void testSingTest8() throws Exception {
	    driver.get(baseUrl + "TwitterFollowingFinder/");
	    driver.findElement(By.id("hashtags")).clear();
	    driver.findElement(By.id("hashtags")).sendKeys("sanremo love");
	    driver.findElement(By.id("radioHash1")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	    Thread.sleep(1000);
	    assertTrue(driver.getPageSource().contains(error));
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
