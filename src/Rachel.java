 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Rachel {
  private WebDriver driver;
  private String aaBaseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String parentWindowHandler;

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		
    driver = new FirefoxDriver();
    aaBaseUrl = "http://www.actorsaccess.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    parentWindowHandler = driver.getWindowHandle();
  }

  @Test
  public void Rachel() throws Exception {
    driver.get(aaBaseUrl + "/");
 
try{    driver.findElement(By.id("username")).clear();
}catch(Exception e){  System.out.println("BAD line ");}
    driver.findElement(By.id("username")).sendKeys("guykapulnik");
    
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("aGuy1234567");
    driver.findElement(By.id("login-btn_mobile")).click();
    driver.findElement(By.linkText("breakdowns »")).click();
    driver.findElement(By.linkText("new york")).click();
    driver.findElement(By.linkText("PRETTY")).click();
    driver.findElement(By.linkText("HUEY")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | select_photo | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=main_window | ]]
    driver.findElement(By.cssSelector("#photo_4799184 > table > tbody > tr > td.text-size-10 > a.btn-pill")).click();
    driver.findElement(By.name("video_to_use")).click();
    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
    driver.findElement(By.id("include_sc_checkbox_id")).click();
    driver.findElement(By.name("notes")).clear();
    driver.findElement(By.name("notes")).sendKeys("Thanks");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=buttons | ]]
    driver.findElement(By.id("add_to_cart")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.cssSelector("strong")).click();
    driver.findElement(By.id("cartsubmit")).click();
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
