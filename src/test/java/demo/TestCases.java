package demo;

import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers=new Wrappers();

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }

    @Test
    public void testCase01() throws InterruptedException  {
        wrappers.navigateToFlipkart(driver);
        wrappers.searchItem("Washing Machine", driver);
        Thread.sleep(2000);
        wrappers.sortByPopularity(driver);
        Thread.sleep(3000);
        System.out.println(wrappers.getCountRatings(driver));
    }

    @Test
    public void testCase02() throws InterruptedException  {
        wrappers.navigateToFlipkart(driver);
        Thread.sleep(5000);
        wrappers.closePopup(driver);
        wrappers.searchItem("iPhone", driver);
        wrappers.getIphoneTitles(driver);
    }

    @Test
    public void testCase03() throws InterruptedException  {
        wrappers.navigateToFlipkart(driver);
        wrappers.searchItem("Coffee Mug", driver);
        wrappers.getHighestRatings(driver);

    }
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}