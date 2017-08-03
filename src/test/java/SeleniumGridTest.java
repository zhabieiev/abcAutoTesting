import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.net.URL;


public class SeleniumGridTest {
    WebDriver driver;
//    String url = "http://35.187.182.202:4444/wd/hub"; // GoogleCloud
    String url = "http://172.17.10.83:4444/wd/hub"; //VirtualBox Mac OS X

    @BeforeMethod
    public void beforeMethod() throws IOException {
        DesiredCapabilities capability = DesiredCapabilities.safari(); //using Chrome instead FireFox, cause special mode in FF
        driver = new RemoteWebDriver(new URL(url), capability);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void launchStackoverflowTest() {
        driver.get("http://stackoverflow.com/");
        Assert.assertEquals(driver.getTitle(), "Stack Overflow - Where Developers Learn, Share, & Build Careers");
    }

    @Test
    public void launchGoogleTest() {
        driver.get("http://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @Test
    public void launchYahooTest() {
        driver.get("http://www.yahoo.com");
        Assert.assertEquals(driver.getTitle(), "Yahoo");
    }

    @Test
    public void openPageTest() throws IOException, InterruptedException {
        driver.get("http://lms.mainacad.com/course/view.php?id=33");
        driver.findElement(By.id("username")).sendKeys("heorhyi_zhabeev");
        driver.findElement(By.id("password")).sendKeys("Heorhyi_Zhabeev05");
        driver.findElement(By.id("loginbtn")).click();
        Assert.assertEquals(driver.getTitle(),"Курс: Automated testing");
    }
}