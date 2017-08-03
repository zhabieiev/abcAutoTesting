package drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

public class GeckoDriverManager extends DriverManager {
    private GeckoDriverService ffService;
    private DesiredCapabilities capabilities;

    public GeckoDriverManager() {
        this.capabilities = DesiredCapabilities.firefox();
    }

    @Override
    public void startService(){
        if(null==ffService){
            try{
                ffService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("geckodriver.exe"))
                        .usingAnyFreePort()
                        .build();
                ffService.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if(null!=ffService && ffService.isRunning())
            ffService.stop();
    }

    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        driver = new FirefoxDriver(options);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }
}
