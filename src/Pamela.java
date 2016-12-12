
//import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
	private String pamelaLog;
	int[] passedOnOptionArray = new int[] { -1, -1 };
	int[] currentOnOptionArray = new int[] { 0, 0 };
	boolean useSleep;
	int leftNumOfLoginWhileLoopsChances = 0;
	int leftNumOfSubmittionWhileLoopsChances = 0;
	String parentWindowHandler;
	String newWindowHandler;
	Iterator<String> windowHandlesIterator;
	Set<String> handles;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		pamelaLog = new String("");
		useSleep = true;

	}

	@Test
	public void testPamela() throws Exception {
		log("A: Window handle Parent " + parentWindowHandler);
		while ((leftNumOfLoginWhileLoopsChances++) < 10) {
			log("B: Start Login num " + leftNumOfLoginWhileLoopsChances);
			driver.get(baseUrl + "/");
			breath();
			driver.findElement(By.id("login")).click();
			driver.findElement(By.id("login")).clear();
			driver.findElement(By.id("login")).sendKeys("guykapulnik");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("cGuy1234567");
			driver.findElement(By.xpath("//input[@id='submit']")).click();
			breath();
			driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
			// check for welcome window:
			if (!verifyLocation("//div[@id='maininfo']/h2", "Welcome")) {
				continue;
			}

			log("C: Location->Home Page");
			breath();

			// WORK ONLY ON BACKGROUND WORK $$$ NOW

			try {
				if (!assertiveClicking(0, new String[] { "//a[@id='_ctl0_cphBody_lnkExtrasRoles']",
						"//a[contains(text(),'new Extras roles')]", "//li[@id='_ctl0_cphBody_liDirectCastExtras']/a",
						"//a[contains(@href, '../DirectCast/Roles.aspx?rt=xc1')]",
						"//div[2]/div/div/div/ul/li[3]/a" })) {
					break;
				}

				log("D: First triel worked on " + passedOnOptionArray[0]);

				if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2", "Casting Billboard")) {
					log("E: Location->Casting Billboard");
				}

				if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
					continue;
				}

			} catch (Exception e) {
				log("Didn't work");
				// go back to login page
				continue;
			}

			log("F: Succ opening Casing Billboards and Extras link");
			// end login while loop
			break;
		}
		while (leftNumOfSubmittionWhileLoopsChances++ < 3) {
			log("G: Start submittion while loop num " + leftNumOfSubmittionWhileLoopsChances);
			// Choose from drop down list 'all roles':
			try {
				offer = new Job();
				offer.setIsBackgroundWork(true);
				new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
				handleBackgroundWorkOffer(true);
				// offer.readNotice();
				offer.makeDecision();

				if ((!offer.getDecisionSubmit()) || (offer.getHasBeenSubmitted())) {
					// DO NOT SUBMIT THIS OFFER
					continue;
				}
				log("I: Begin submittion for top offer");
				breath();
				driver.findElement(By.xpath("//tr[3]/td/a")).click();
				breath();
				windowStatus();
				driver.switchTo().window(getSonWindowHandler());
				windowStatus();
				/*
				 * if (!moveToOtherWindow()) { // restart continue; }
				 */

			} catch (Exception e) {
				log("Didn't work");
				
				// move back to parent and go back to G point
				killSubWindowAndMoveToParentWindow();				
				continue;
			}
			try {
				log("J: Trying an option for the submit link");

				if (!assertiveClicking(1,
						new String[] { "//a[contains(text(),'submit')]", "//table[6]/tbody/tr/td/a" })) {
					break;
				}
				/*
				 * case 1: driver.findElement(By.linkText("submit")).click();
				 * case 3:driver.findElement(By.cssSelector("css=a")).click();
				 */
				log("K: Second triel worked on " + passedOnOptionArray[1]);
				windowStatus();
				// succece opening to photos page

				if (!verifyLocation("//span", "Customize your submission")) {
					log("Error: You are on wrong window");
					windowStatus();
					continue;
				}

				log("L: Succ on openning window to choose photo and fill talent notes.");
				driver.findElement(By.id("TALENTNOTE")).clear();
				// driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				breath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				breath();
				// verify that the confirmation window opened
				windowStatus();
				windowStatus2();
				if (!verifyLocation("//span", "Submission Successful")) {
					log("Did NOT recieve final submittion successful");
					windowStatus();
					continue;
				}
				if (!killSubWindowAndMoveToParentWindow()) {
					log("Memory leak error: failed killing child window");
					break;
				}
				offer.setHasBeenSubmitted(true);
				offer.setLog(pamelaLog);
				log("M: Succ Submitted: " + offer.getHasBeenSubmitted() + " SAG:" + offer.getIsSag() + " Male:"
						+ offer.getIsMale() + " Eth:" + offer.getIsEthnicity() + "Car: " + offer.isCar + " __ "
						+ offer.getNotice() + "LOG: " + offer.getLog());

				return;
			} catch (Exception e) {
				log("Clicking submit failed on triel");
			}
		}
		log("Z: Stopping");
		return;
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

			try {
				currentOffer = new String(driver.findElement(By.xpath("//tr[3]/td/a")).getText());
			} catch (Exception e) {
				currentOffer = new String("");
			}
			;
			currentOfferRole = new String(currentOffer);

			if (isBackgroundWork) {
				// BACKGROUND WORK
				try {
					currentOfferProjectName = new String(driver.findElement(By.xpath("//tr[3]/td[2]/a")).getText());
				} catch (Exception e) {
					currentOfferProjectName = new String("");
				}
				;
				try {
					currentOfferShootDate = new String(driver.findElement(By.xpath("//tr[3]/td[3]/a")).getText());
				} catch (Exception e) {
					currentOfferShootDate = new String("");
				}
				;
				try {
					currentOfferTypeProject = new String(driver.findElement(By.xpath("//tr[3]/td[4]/a")).getText());
				} catch (Exception e) {
					currentOfferTypeProject = new String("");
				}
				;
				try {
					currentOffertRate = new String(driver.findElement(By.xpath("//tr[3]/td[5]/a")).getText());
				} catch (Exception e) {
					currentOffertRate = new String("");
				}
				;
				try {
					currentOfferPaying = new String(driver.findElement(By.xpath("//tr[3]/td[6]/a")).getText());
				} catch (Exception e) {
					currentOfferPaying = new String("");
				}
				;
				try {
					currentOfferUnionStatus = new String(driver.findElement(By.xpath("//tr[3]/td[7]/a")).getText());
				} catch (Exception e) {
					currentOfferUnionStatus = new String("");
				}
				;
				try {
					currentOfferPostedDate = new String(driver.findElement(By.xpath("//tr[3]/td[8]/a")).getText());
				} catch (Exception e) {
					currentOfferPostedDate = new String("");
				}
				;
				try {
					currentOfferListing = new String(driver.findElement(By.xpath("//tr[4]/td")).getText());
				} catch (Exception e) {
					currentOfferListing = new String("");
				}
				;
			} else {
				// PRINCIPLE WORK
				try {
					currentOfferProjectName = new String(driver.findElement(By.xpath("//tr[3]/td[2]/a")).getText());
				} catch (Exception e) {
					currentOfferProjectName = new String("");
				}
				;
				try {
					currentOfferShootDate = new String(driver.findElement(By.xpath("//tr[3]/td[3]/a")).getText());
				} catch (Exception e) {
					currentOfferShootDate = new String("");
				}
				;
				try {
					currentOfferTypeProject = new String("");
				} catch (Exception e) {
					currentOfferTypeProject = new String("");
				}
				;
				try {
					currentOffertRate = new String(driver.findElement(By.xpath("//tr[3]/td[4]/a")).getText());
				} catch (Exception e) {
					currentOffertRate = new String("");
				}
				;
				try {
					currentOfferPaying = new String(driver.findElement(By.xpath("//tr[3]/td[5]/a")).getText());
				} catch (Exception e) {
					currentOfferPaying = new String("");
				}
				;
				try {
					currentOfferUnionStatus = new String(driver.findElement(By.xpath("//tr[3]/td[6]/a")).getText());
				} catch (Exception e) {
					currentOfferUnionStatus = new String("");
				}
				;
				try {
					currentOfferPostedDate = new String(driver.findElement(By.xpath("//tr[3]/td[7]/a")).getText());
				} catch (Exception e) {
					currentOfferPostedDate = new String("");
				}
				;
				try {
					currentOfferListing = new String(driver.findElement(By.xpath("//tr[4]/td")).getText());
				} catch (Exception e) {
					currentOfferListing = new String("");
				}
				;

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
			log("H: Succ adding offer to Jobs list");

			return;

		} catch (Exception e) {
			log("Error grabbing the current offer data into the Strings");
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

	private void log(String newLog) {
		if (newLog.length() < 1) {
			return;
		}
		pamelaLog += (new String(newLog)).concat("\n");
		System.out.println(newLog);
	}

	private boolean moveToOtherWindow() {
		windowStatus();
		String currentWindowHandler = driver.getWindowHandle();
		handles = driver.getWindowHandles(); // get all window handles
		if (handles.size() < 2) {
			log("Error: there is only one window : " + currentWindowHandler);
			return false;
		}
		windowHandlesIterator = handles.iterator();
		if (windowHandlesIterator.hasNext()) {
			newWindowHandler = windowHandlesIterator.next();
			if (!newWindowHandler.equals(currentWindowHandler)) {
				driver.switchTo().window(newWindowHandler); // switch to popup
															// window
				windowStatus();
				return true;
			} else {
				// fell on the same window - so move again
				if (windowHandlesIterator.hasNext()) {
					newWindowHandler = windowHandlesIterator.next();
					if (!newWindowHandler.equals(currentWindowHandler)) {
						driver.switchTo().window(newWindowHandler); // switching
																	// to
																	// popup
																	// window
						windowStatus();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean killSubWindowAndMoveToParentWindow() {
		// returns true onlyon a succesfull kill the sub window and return back
		// to parent window.
		windowStatus();
		driver.close();
		driver.switchTo().window(parentWindowHandler);
		String newWindowHandler = driver.getWindowHandle();
		log("killed window and returned to  " + newWindowHandler);
		windowStatus();
		return true;
	}

	private void windowStatus() {
		String currentWindowHandler = driver.getWindowHandle();
		String sonWindow = getSonWindowHandler();
		String pointing;
		if (getParentWindowHandler().equals(currentWindowHandler)) {
			pointing = new String("PARENT");
		} else {
			pointing = new String("SON");
		}
		driver.getWindowHandle();
		log("Parent: " + getParentWindowHandler() + " Son: " + sonWindow + " Current: " + pointing);
		return;
	}

	private void windowStatus2() {
		handles = driver.getWindowHandles(); // get all window handles
		// String allHandles = new String(Arrays.toString(handles));

		StringBuilder builder = new StringBuilder();
		for (String s : handles) {
			builder.append(s + ",");
		}
		String allHandles = new String("[");
		allHandles += new String(builder.toString());
		allHandles += new String("] ");
		String currentWindowHandler = driver.getWindowHandle();
		log(allHandles + " on: " + currentWindowHandler);
	}

	private String getParentWindowHandler() {
		if (parentWindowHandler.length() > 1) {
			return parentWindowHandler;
		}
		log("Error finding Parent holder");
		return ("");

	}

	private String getSonWindowHandler() {
		String currentWindowHandler = driver.getWindowHandle();
		handles = driver.getWindowHandles(); // get all window handles
		windowHandlesIterator = handles.iterator();

		switch (handles.size()) {
		case 1:
			windowStatus2();
			return ("");
		case 2: {
			if (!currentWindowHandler.equals(getParentWindowHandler())) {
				return currentWindowHandler;
			} else {
				// finding out what the other window handler is
				if (windowHandlesIterator.hasNext()) {
					newWindowHandler = windowHandlesIterator.next();
					if (!newWindowHandler.equals(getParentWindowHandler())) {
						return newWindowHandler;
					} else {
						//
						if (windowHandlesIterator.hasNext()) {
							newWindowHandler = windowHandlesIterator.next();
							if (!newWindowHandler.equals(currentWindowHandler)) {
								return newWindowHandler;
							}
						}
					}
				}
			}
		}
		case 3:
			log("Error there are 3 windows!");
			windowStatus2();
			return ("");
		}

		log("Error finding SON");
		return ("");
	}

	private boolean verifyLocation(String xpathTab, String verifyText) {
		String locationTest1 = new String(driver.findElement(By.xpath(xpathTab)).getText());
		if ((locationTest1.contains(verifyText))) {
			return true;
		}
		log("Error: verify text " + verifyText + " failed.");
		return false;
	}

	private boolean assertiveClicking(int numOfTriel, String[] optionStrings) {
		{// works only with xPath links - sorry!
			if (optionStrings.length < 1) {
				log("No option strings");
				return false;
			}

			if (currentOnOptionArray[numOfTriel] > optionStrings.length) {
				log("Fatal Error: Tried all the options! We might be on the wrong page");
				windowStatus();
				windowStatus2();
				return false;
			}

			try {
				if (passedOnOptionArray[numOfTriel] == (-1)) {
					// trying an option

					driver.findElement(By.xpath(optionStrings[currentOnOptionArray[numOfTriel]])).click();
					passedOnOptionArray[numOfTriel] = currentOnOptionArray[numOfTriel];
				} else {
					// used the option that passed
					driver.findElement(By.xpath(optionStrings[passedOnOptionArray[numOfTriel]])).click();
				}
				return true;
			} catch (Exception e) {
				log("Option " + optionStrings[numOfTriel] + " didn't work.");
				currentOnOptionArray[numOfTriel] = currentOnOptionArray[numOfTriel] + 1;
				log("Lets try option num " + currentOnOptionArray[numOfTriel]);
				return false;
			}
		}

	}

	public void breath() throws InterruptedException {
		// sleeps for the configured time + impro
		int sleepTime;
		sleepTime = randInt(2, 5);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
		} else {
			// no sleep!
		}

	}
}
