
//import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Pamela {

	private WebDriver driver;
	private String cnBaseUrl;
	private String aaBaseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;
	private String pamelaLog;
	int[] passedOnOptionArray = new int[] { -1, -1 };
	int[] currentOnOptionArray = new int[] { 0, 0 };
	static boolean useSleep;
	int leftNumOfLoginWhileLoopsChances = 0;
	int leftNumOfSubmittionWhileLoopsChances = 0;
	String parentWindowHandler;
	String newWindowHandler;
	Iterator<String> windowHandlesIterator;
	Set<String> handles;
	private static final String OUTPUT_FILE = "C:\\Users\\me\\work\\bork\\outputFolder\\logHandler_";
	static private boolean logStateFull;
	public static Appender fh = null;
	public static Logger logger = Logger.getLogger("MyLog");
	static public boolean seekBackgroundWork;

	public static void main(String[] args) {

		try {
			String appendixFileName = new String((new Long(System.currentTimeMillis())).toString());
			useSleep = true;
			logStateFull = true;
			fh = new FileAppender(new SimpleLayout(),
					(new String(OUTPUT_FILE).concat(appendixFileName).concat(".txt")));
			seekBackgroundWork = false;
			while (networkWorking()) {
				JUnitCore jCore = new JUnitCore();
				jCore.run(Pamela.class);
				TimeUnit.SECONDS.sleep(300);
				// seek Principle / BG work next round
				full_log("ALTERNATE PRINCIPLE <-> BACKGROUND");
				// seekBackgroundWork ^= true;

			}
		} catch (Exception e) {
		}
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\me\\work\\fifth\\selenium\\libs\\geckodriver.exe");
		driver = new FirefoxDriver();
		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		pamelaLog = new String("New Pamela");

	}

	@Test
	public void aaRachel() throws Exception {
		try {
			log('a');
			driver.get("http://www.actorsaccess.com/");
			driver.findElement(By.id("username_mobile")).clear();
			driver.findElement(By.id("username_mobile")).sendKeys("guykapulnik");
			driver.findElement(By.id("password_mobile")).clear();
			driver.findElement(By.id("password_mobile")).sendKeys("aGuy1234567");
			driver.findElement(By.id("login-btn_mobile")).click();
			deepBreath();
			driver.get(" http://www.actorsaccess.com/projects/?view=breakdowns&region=2");
		} catch (Exception e) {
		}
	

	// read top offer:
		 offer = new Job();
		 handleAAWorkOffer(); 	
		 lookForSubmissionCheckOnLeft();
		 aaDecideToSubmit();
	/*
	 * driver.findElement(By.linkText("breakdowns")).click();
	 * driver.findElement(By.linkText("new york")).click();
	 */
	// driver.findElement(By.linkText("PRETTY")).click();

	
	
	
	}
	
	
	 public void aaDecideToSubmit(){
		
	 }
	
	 
	
	@Test
	public void testPamela() throws Exception {

		// REAL PAMELA

		log('a');
		while ((leftNumOfLoginWhileLoopsChances++) < 3) {
			log('b');
			driver.get(cnBaseUrl + "/");
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

			log('c');
			breath();

			try {
				if (seekBackgroundWork) {

					if (!assertiveClicking(0,
							new String[] { "//a[@id='_ctl0_cphBody_lnkExtrasRoles']",
									"//a[contains(text(),'new Extras roles')]",
									"//li[@id='_ctl0_cphBody_liDirectCastExtras']/a",
									"//a[contains(@href, '../DirectCast/Roles.aspx?rt=xc1')]",
									"//div[2]/div/div/div/ul/li[3]/a" })) {
						break;
					}

					log('d');

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2", "Casting Billboard")) {
						log('e');
					}

					if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
						continue;
					}
				} else {// PRINCIPLE ROLES
					if (!assertiveClicking(0,
							new String[] { "//a[@id='_ctl0_cphBody_lnkPrincipalsRoles']",
									"//a[contains(text(),'new Principals roles')]",
									"//li[@id='_ctl0_cphBody_liDirectCastPrincipals']/a",
									"//div[2]/div/div/div/ul/li[2]/a" })) {
						break;
					}

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2", "Casting Billboard")) {
						log('e');
					}

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
						continue;
					}

				}
			} catch (Exception e) {
				log("Didn't work");
				// go back to login page
				continue;
			}

			log('f');
			// end login while loop
			break;
		}
		while (leftNumOfSubmittionWhileLoopsChances++ < 10) {
			log('g');
			// Choose from drop down list 'all roles':
			try {
				offer = new Job();
				// offer.setIsBackgroundWork(seekBackgroundWork);
				new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
				deepBreath();
				handleBackgroundWorkOffer(seekBackgroundWork);
				offer.readNotice();
				offer.makeDecision();
				if ((!offer.getDecisionSubmit()) || (offer.getHasBeenSubmitted())) {
					// DO NOT SUBMIT THIS OFFER
					continue;
				}
				log('i');
				deepBreath();
				driver.findElement(By.xpath("//tr[3]/td/a")).click();
				deepBreath();
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
				log('j');

				deepBreath();
				try {
					if (seekBackgroundWork) {
						assertTrue(isElementPresent(By.xpath("//table[6]/tbody/tr/td/img")));
					} else {
						// seeking Principle
						assertTrue(isElementPresent(By.xpath("//table[5]/tbody/tr/td/img")));
					}
					// the green button is there SO :
					log("This project saved as  " + offer.getOfferId() + " has been submitted before.");
					offer = null;
					if (!killSubWindowAndMoveToParentWindow()) {
						log("Memory leak error: failed killing child window");
						break;
					}
					// refresh page to allow new offers to be displayed
					nap();
					log("REFRESH PAGE");
					driver.navigate().refresh();

					continue;
				} catch (Error e) {
					verificationErrors.append(e.toString());
					// no green button
				}

				deepBreath();
				if (!assertiveClicking(1,
						new String[] { "//a[contains(text(),'submit')]", "//table[6]/tbody/tr/td/a" })) {
					break;
				}

				log('k');
				windowStatus();
				// succece opening to photos page
				deepBreath();
				if (!verifyLocation("//span", "Customize your submission")) {
					log("Error: You are on wrong window");
					windowStatus();
					continue;
				}

				log('l');
				deepBreath();
				driver.findElement(By.id("TALENTNOTE")).clear();
				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				log("filled talent notes with : " + offer.getMessage());
				deepBreath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				deepBreath();
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
				log('m');

			} catch (Exception e) {
				log("Clicking submit failed on triel");
			}
		}
		log('z');
		return;
	}

	private void handleAAWorkOffer(){

			 String currentOffer;
				
			
				String currentOfferPostedTime;
				String currentOfferPostedHour;
				String currentOfferProjectName;
				String currentOfferTypeProject;
				String currentOfferCastingDirector;
				String currentOfferStartDate;
				String currentOfferUnionStatus;
				String currentOfferListing;
				/******/
				String currentOfferRole;
				String currentOfferShootDate;
				String currentOffertRate;
				String currentOfferPaying;
				
	try{offer.setOfferPostedTime(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[2]")).getText()));} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));}
	
	try{offer.setOfferProjectName(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[3]/a")).getText()));} catch (Exception e) {
		offer.setOfferProjectName(new String(""));}
	
	try{offer.setOfferTypeProject(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[4]")).getText()));} catch (Exception e) {
		offer.setOfferTypeProject(new String(""));}
	
	try{offer.setOfferCastingDirector(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[5]")).getText()));} catch (Exception e) {
		offer.setOfferCastingDirector(new String(""));}
	try{offer.setOfferShootDate(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[6]")).getText()));} catch (Exception e) {		
		offer.setOfferShootDate(new String(""));}
	try{offer.setOfferUnionStatus(new String(driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[7]")).getText()));} catch (Exception e) {
		offer.setOfferUnionStatus(new String(""));}
	 
				
		 
		
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
					currentOfferShootDate = "";
				} catch (Exception e) {
					currentOfferShootDate = new String("");
				}
				;
				try {
					currentOfferTypeProject = new String(driver.findElement(By.xpath("//tr[3]/td[3]/a")).getText());
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

			offer.setOfferRole(currentOffer.toLowerCase());

			offer.setOfferProjectName(currentOfferProjectName.toLowerCase());
			offer.setOfferShootDate(currentOfferShootDate.toLowerCase());
			offer.setOfferTypeProject(currentOfferTypeProject.toLowerCase());
			offer.setOffertRate(currentOffertRate.toLowerCase());
			offer.setOfferPaying(currentOfferPaying.toLowerCase());
			offer.setOfferUnionStatus(currentOfferUnionStatus.toLowerCase());
			offer.setOfferPostedDate(currentOfferPostedDate.toLowerCase());
			offer.setOfferListing(currentOfferListing.toLowerCase());
			Jobs.add(offer);
			log('h');

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

	static private void full_log(String newLog) {
		if (newLog.length() < 1) {
			return;
		}
		System.out.println(newLog);
		try {
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
			logger.info(newLog);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	private void log(String newLog) {
		full_log(newLog);

	}

	private void log(char stage) {
		// each char is assigned a stage in the process - so the log will write
		// the whole string out
		// state - Full log - outputs the whole string
		// state - min log - outputs only the letter representing the stage
		if (logStateFull) {
			switch (stage) {
			case 'a':
				Pamela.full_log("A: Window handle Parent " + parentWindowHandler);
				break;
			case 'b':
				Pamela.full_log("B: Start Login num " + leftNumOfLoginWhileLoopsChances);
				break;
			case 'c':
				Pamela.full_log("C: Location->Home Page");
				break;
			case 'd':
				if (seekBackgroundWork) {
					Pamela.full_log("D: First BG triel worked on " + passedOnOptionArray[0]);
				} else {
					Pamela.full_log("D: First PRINCIPLE triel worked on " + passedOnOptionArray[0]);
				}
				break;
			case 'e':
				Pamela.full_log("E: Location->Casting Billboard");
				break;
			case 'f':
				Pamela.full_log("F: Succ opening Casing Billboards and Extras link");
				break;
			case 'g':
				if (seekBackgroundWork) {
					Pamela.full_log("G: BACKGROUND work Start submittion while loop num "
							+ leftNumOfSubmittionWhileLoopsChances);
				} else {
					Pamela.full_log("G: PRINCIPLE work Start submittion while loop num "
							+ leftNumOfSubmittionWhileLoopsChances);
				}

				break;
			case 'h':
				Pamela.full_log("H: Succ adding offer to Jobs list");
				break;
			case 'i':
				Pamela.full_log(
						"I: Begin submittion for top offer id " + offer.getOfferId() + " : " + offer.getOfferRole());
				break;
			case 'j':
				Pamela.full_log("J: Making sure there is no GREEN STAR");
				break;
			case 'k':
				Pamela.full_log("K: Second triel worked on " + passedOnOptionArray[1]);
				break;
			case 'l':
				Pamela.full_log("L: Succ on openning window to choose photo and fill talent notes.");
				break;
			case 'm':
				Pamela.full_log("M: Succ Submitted: " + offer.getHasBeenSubmitted() + " SAG:" + offer.getIsSag()
						+ " Male:" + offer.getIsMale() + " Eth:" + offer.getIsEthnicity() + "Car: " + offer.isCar
						+ " __ " + offer.getNotice() + "Talent Notes :" + offer.getMessage());
				break;

			case 'y':
				Pamela.full_log("Parent: " + getParentWindowHandler() + " Son: " + getSonWindowHandler());
				break;
			case 'z':
				Pamela.full_log("Z: Stopping");
				break;

			}
		} else {
			Pamela.full_log(Character.toString(stage));
		}
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
			log('y');
			log("Now on PARENT");
		} else {
			log('y');
			log("Now on SON");
		}
		driver.getWindowHandle();
		log('y');
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
		// returns true only if the location of the xpath contains the
		// verifyText
		try {
			String locationTest1 = new String(driver.findElement(By.xpath(xpathTab)).getText());

			if ((locationTest1.contains(verifyText))) {
				return true;
			}
		} catch (Exception e) {
			log("Verify text " + verifyText + " Does NOT appear");
			return false;
		}

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

					// must chekc there is no green star
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
		sleepTime = randInt(4, 5);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
			if (logStateFull) {
				 log(".");

			}
		}
	}

	public void deepBreath() throws InterruptedException {
		// the first breaths are small ones. After the second while loop - we
		// take deep breaths.
		if (leftNumOfSubmittionWhileLoopsChances < 2) {
			breath();
		}
		for (int i = 0; i < 3; i++) {
			breath();
		}
	}

	public void nap() throws InterruptedException {
		log("Zzz " + leftNumOfSubmittionWhileLoopsChances);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(60);
		}
	}

	static boolean networkWorking() {
		// returns true if there is a network connection

		return true;
	}
}
