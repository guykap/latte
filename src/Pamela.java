
//import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class Pamela {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;
	int trielNumB = -1;
	int trielNum = -1;
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testPamela() throws Exception {
		while (true) {
			System.out.println("Start Login");
			driver.get(baseUrl + "/");
			TimeUnit.SECONDS.sleep(4);
			driver.findElement(By.id("login")).click();
			driver.findElement(By.id("login")).clear();
			driver.findElement(By.id("login")).sendKeys("guykapulnik");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("cGuy1234567");
			driver.findElement(By.xpath("//input[@id='submit']")).click();
			TimeUnit.SECONDS.sleep(4);
			driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
			// check for welcome window:
			String locationTest1 = new String(driver.findElement(By.xpath("//div[@id='maininfo']/h2")).getText());
			if (!(locationTest1.contains("Welcome"))) {
				// go back to login page
				System.out.println("Error logging in.");
				continue;
			}

			System.out.println("Succ logging in");
			TimeUnit.SECONDS.sleep(4);

			// WORK ONLY ON BACKGROUND WORK $$$ NOW

			try {
				
				switch (++trielNumB) {
				case 0:
					driver.findElement(By.xpath("//a[contains(text(),'new Extras roles')]")).click();
					break;
				case 1:
					driver.findElement(By.xpath("//a[@id='_ctl0_cphBody_lnkExtrasRoles']")).click();
					break;
				case 2:
					driver.findElement(By.xpath("//li[@id='_ctl0_cphBody_liDirectCastExtras']/a")).click();
					break;
				case 3:
					driver.findElement(By.xpath("//a[contains(@href, '../DirectCast/Roles.aspx?rt=xc1')]")).click();
					break;
				case 4:
					driver.findElement(By.xpath("//div[2]/div/div/div/ul/li[3]/a")).click();
					break;

				}
				System.out.println("B worked on " +trielNumB); 
				
				/*
				driver.findElement(By.xpath("//a[contains(text(),'Casting Billboard')]")).click();
				String locationTest2 = new String(
						driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2")).getText());
				if (!(locationTest2.contains("Casting Billboard"))) {
					// go back to login page
					System.out.println("Error pressing Casting Billboard.");
					continue;
				}
				*/
				/*
				// choose BACKGROUND filter
				switch (++trielNum) {
				case 0:
					driver.findElement(By.xpath("//a[contains(text(),'Extras Roles')]")).click();
					break;
				case 1:
					driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a")).click();
					break;
				case 2:
					driver.findElement(By.xpath("//td/table/tbody/tr/td/a")).click();
					break;
				case 3:
					driver.findElement(By.xpath("css=td > table > tbody > tr > td > a")).click();
					break;
				case 4:
					driver.findElement(By.xpath("//a[contains(text(),'Click here to view  Extras Roles')]")).click();
					break;

				}
				System.out.println("worked on " +trielNum); 
*/
				 
				// TimeUnit.SECONDS.sleep(3);
				String locationTest3 = new String(
						driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3")).getText());
				if (!(locationTest3.contains("Extras"))) {
					// go back to login page
					System.out.println("Error pressing Extras link.");
					continue;
				}

			} catch (Exception e) {
				System.out.println("Didn't work");
				// go back to login page
				continue;
			}

			System.out.println("Succ opening Casing Billboards and Extras link");

			// Choose from drop down list 'all roles':
			try {
				offer = new Job();
				offer.setIsBackgroundWork(true);
				// driver.findElement(By.xpath("//td/table/tbody/tr/td/a")).click();
			} catch (Exception e) {
				System.out.println("Didn't work");
				// go back to login page
				continue;
			}

			new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
			// driver.findElement(By.id("_ctl0_lnkExtrasRoles")).click();

			handleBackgroundWorkOffer(true);
			//offer.readNotice();
			offer.makeDecision();
			
			if ((offer.getDecisionSubmit()) && (!offer.getHasBeenSubmitted())) {
				System.out.println("Begin submittion for this offer");
				// submitting to this offer
				TimeUnit.SECONDS.sleep(2);
				try {
						//submitting to top offer
					driver.findElement(By.xpath("//tr[3]/td/a")).click();
					 //checking that we are in submittion Page 1 - ADD THIS IS IMPORTANT
					
					
					/*String locationTest2 = new String(driver.findElement(By.xpath("//td[3]")).getText());
					if (!(locationTest2.contains("Role"))) {
						// go back to login page
						System.out.println("Error pressing Casting Billboard -EXTRAS.");
						continue;
					}
					*/
				} catch (Exception e) {
					System.out.println("Didn't work");
					// go back to login page
					continue;
				}

				driver.findElement(By.linkText("submit")).click();
				
				// driver.findElement(By.xpath("//a[contains(text(),'submit')]")).click();

				// make sure the windows opened:
				String a = new String(driver.findElement(By.xpath("//table/tbody/tr/td/span")).getText());
				String b = new String(driver.findElement(By.xpath("//table/tbody/tr/td/a")).getText());
				String navigatorTest = new String(driver.findElement(By.xpath("//tr[3]/td/a")).getText());
				if (!navigatorTest.contains("Customize your submission")) {
					// error oppenning the window
					System.out.println("Error openning the window");
					return;
				}
				// add the choose the photo

				System.out.println("Openned window to choose photo and fill talent notes.");
				driver.findElement(By.id("TALENTNOTE")).clear();
				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				driver.findElement(By.cssSelector("td.dotbottom > img")).click();
				System.out.println("Succ submitting");
				offer.setHasBeenSubmitted(true);
			} else {
				// do not submit
			}
			System.out.println("Submitted: " + offer.getHasBeenSubmitted() + " SAG:" + offer.getIsSag() + " Male:"
					+ offer.getIsMale() + " Eth:" + offer.getIsEthnicity() + "Car: " + offer.isCar + " __ "
					+ offer.getNotice());

		}
	}

	private void handleBackgroundWorkOffer(boolean isBackgroundWork) {

		offer.setIsBackgroundWork(isBackgroundWork);
		// the EXTRA table has the shooting date .
		// the PRINCIPLE table does not

		String currentOffer;
		String currentOfferRole;
		String currentOfferProjectName;
		String currentOfferShootDate;
		String currentOfferTypeProject;
		String currentOffertRate;
		String currentOfferPaying;
		String currentOfferUnionStatus;
		String currentOfferPostedDate;
		String currentOfferListing;

		try {
			
			try{currentOffer = new String(driver.findElement(By.xpath("//tr[3]/td/a")).getText());}catch(Exception e){currentOffer = new String("");};
			currentOfferRole = new String(currentOffer);
			
			if (isBackgroundWork) {
				// BACKGROUND WORK
	try{currentOfferProjectName = new String(driver.findElement(By.xpath("//tr[3]/td[2]/a")).getText());}catch(Exception e){currentOfferProjectName = new String("");};
				try{currentOfferShootDate = new String(driver.findElement(By.xpath("//tr[3]/td[3]/a")).getText());}catch(Exception e){currentOfferShootDate = new String("");};
				try{currentOfferTypeProject = new String(driver.findElement(By.xpath("//tr[3]/td[4]/a")).getText());}catch(Exception e){currentOfferTypeProject = new String("");};
				try{currentOffertRate = new String(driver.findElement(By.xpath("//tr[3]/td[5]/a")).getText());}catch(Exception e){currentOffertRate = new String("");};
				try{currentOfferPaying = new String(driver.findElement(By.xpath("//tr[3]/td[6]/a")).getText());}catch(Exception e){currentOfferPaying = new String("");};
				try{currentOfferUnionStatus = new String(driver.findElement(By.xpath("//tr[3]/td[7]/a")).getText());}catch(Exception e){currentOfferUnionStatus = new String("");};
				try{currentOfferPostedDate = new String(driver.findElement(By.xpath("//tr[3]/td[8]/a")).getText());}catch(Exception e){currentOfferPostedDate = new String("");};
				try{currentOfferListing = new String(driver.findElement(By.xpath("//tr[4]/td")).getText());}catch(Exception e){currentOfferListing = new String("");};
			 	} else {
				// PRINCIPLE WORK
			 		try{currentOfferProjectName = new String(driver.findElement(By.xpath("//tr[3]/td[2]/a")).getText());}catch(Exception e){currentOfferProjectName = new String("");};
					try{currentOfferShootDate = new String(driver.findElement(By.xpath("//tr[3]/td[3]/a")).getText());}catch(Exception e){currentOfferShootDate = new String("");};
					try{currentOfferTypeProject = new String("");}catch(Exception e){currentOfferTypeProject = new String("");};
					try{currentOffertRate = new String(driver.findElement(By.xpath("//tr[3]/td[4]/a")).getText());}catch(Exception e){currentOffertRate = new String("");};
					try{currentOfferPaying = new String(driver.findElement(By.xpath("//tr[3]/td[5]/a")).getText());}catch(Exception e){currentOfferPaying = new String("");};
					try{currentOfferUnionStatus = new String(driver.findElement(By.xpath("//tr[3]/td[6]/a")).getText());}catch(Exception e){currentOfferUnionStatus = new String("");};
					try{currentOfferPostedDate = new String(driver.findElement(By.xpath("//tr[3]/td[7]/a")).getText());}catch(Exception e){currentOfferPostedDate = new String("");};
					try{currentOfferListing = new String(driver.findElement(By.xpath("//tr[4]/td")).getText());}catch(Exception e){currentOfferListing = new String("");};	
		 
			}
			// enter into JOB class

			offer.setOfferRole(currentOffer);
			offer.setOfferRole(currentOfferRole);
			offer.setOfferProjectName(currentOfferProjectName);
			offer.setOfferShootDate(currentOfferShootDate);		
			offer.setOfferTypeProject(currentOfferTypeProject);
			offer.setOffertRate(currentOffertRate);
			offer.setOfferPaying(currentOfferPaying);
			offer.setOfferUnionStatus(currentOfferUnionStatus);
			offer.setOfferPostedDate(currentOfferPostedDate);
			offer.setOfferListing(currentOfferListing);
			Jobs.add(offer);
			System.out.println("Succ adding offer to Jobs list");

			return;

		} catch (Exception e) {
			System.out.println("Error grabbing the current offer data into the Strings");
			// go back to login page

		}

		// enter into MySQL

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
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
