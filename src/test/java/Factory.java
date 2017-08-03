
import drivers.*;
import links.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Factory {
    DriverManager driverManager;
    WebDriver driver;
    String hub = "http://35.187.182.202:4444/wd/hub"; //GoogleCloud
//    String hub = "http://172.17.10.87:4444/wd/hub"; //VirtualBox
    String url = "http://wdss.com.ua/";


    @BeforeTest
    public void beforeTest() throws IOException {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {
        driver = new RemoteWebDriver(new URL(hub), driverManager.getCapabilities());
        driver.get(url);
    }


    @Test
    public void checkWebSite() throws IOException {
        Link link = new Link();
        URL currentURL = new URL(url);
        HttpURLConnection httpURLConnect = (HttpURLConnection) currentURL.openConnection();
        link.requestMessage(httpURLConnect);
    }

    @Test
    public void checkLinks() throws IOException {
        Link link = new Link();
        link.checkLink((RemoteWebDriver) driver);
    }

    @Test
    public void clickWebSiteLinks(){
        Link link = new Link();
        link.clickLink((RemoteWebDriver) driver);
    }


    @AfterMethod
    public void catchExceptions(ITestResult result) throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + methodName + "-"
                        + formater.format(calendar.getTime()) +  ".jpg"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @AfterTest
    public void afterMethod() {
        driver.quit();
    }
}