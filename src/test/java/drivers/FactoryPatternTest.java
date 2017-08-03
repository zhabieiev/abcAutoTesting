package drivers;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FactoryPatternTest {

    DriverManager driverManager;
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.IE);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = driverManager.getDriver();
    }

    @AfterTest
    public void afterMethod(){
        driverManager.quitDriver();
    }

    @Test
    public void launchStackoverflowTest() {
        driver.get("http://stackoverflow.com/");
        Assert.assertEquals("Stack Overflow - Where Developers Learn, Share, & Build Careers", driver.getTitle());
    }

    @Test
    public void launchGoogleTest() {
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test
    public void launchYahooTest() {
        driver.get("https://www.yahoo.com/");
        Assert.assertEquals("Yahoo", driver.getTitle());
    }
}