  
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OkTestCase1{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		
    driver = new FirefoxDriver();
    baseUrl = "http://login.castingnetworks.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testOkTestCase1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("guykapulnik");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("cGuy1234567");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.id("_ctl0_lnkExtrasRoles")).click();
    driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table[3]/tbody/tr[3]/td/a")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | ProjWin | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=ProjWin | ]]
    driver.findElement(By.linkText("submit")).click();
    driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
    driver.findElement(By.cssSelector("td.dotbottom > img")).click();
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
