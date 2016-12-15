 
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Rachel {
  private WebDriver driver1;
  private String aaBaseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String parentWindowHandler;

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		
    driver1 = new FirefoxDriver();
    aaBaseUrl = "http://www.actorsaccess.com";
    driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    parentWindowHandler = driver1.getWindowHandle();
  }

  @Test
  public void Rachel() throws Exception {
    driver1.get(aaBaseUrl + "/");
 
try{   
	//driver1.findElement(By.id("username")).clear();

driver1.findElement(By.xpath("//div[@id='mainContent']/table/tbody/tr/td/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/input")).clear();

}catch(Exception e){  System.out.println("BAD line ");}
driver1.findElement(By.xpath("//div[@id='mainContent']/table/tbody/tr/td/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/input")).sendKeys("guykapulnik");
    
    driver1.findElement(By.id("password")).clear();
    driver1.findElement(By.id("password")).sendKeys("aGuy1234567");
    driver1.findElement(By.id("login-btn_mobile")).click();
	driver1.findElement(By.xpath("//input[@id='submit']")).click();
    driver1.findElement(By.linkText("breakdowns »")).click();
    driver1.findElement(By.linkText("new york")).click();
    driver1.findElement(By.linkText("PRETTY")).click();
    driver1.findElement(By.linkText("HUEY")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | select_photo | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=main_window | ]]
    driver1.findElement(By.cssSelector("#photo_4799184 > table > tbody > tr > td.text-size-10 > a.btn-pill")).click();
    driver1.findElement(By.name("video_to_use")).click();
    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
    driver1.findElement(By.id("include_sc_checkbox_id")).click();
    driver1.findElement(By.name("notes")).clear();
    driver1.findElement(By.name("notes")).sendKeys("Thanks");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=buttons | ]]
    driver1.findElement(By.id("add_to_cart")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver1.findElement(By.cssSelector("strong")).click();
    driver1.findElement(By.id("cartsubmit")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver1.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver1.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver1.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver1.switchTo().alert();
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
