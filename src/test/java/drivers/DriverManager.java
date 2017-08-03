package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class DriverManager {
    public WebDriver driver;
    public abstract void startService();
    public abstract void stopService();
    public abstract void createDriver();
    public abstract DesiredCapabilities getCapabilities();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}