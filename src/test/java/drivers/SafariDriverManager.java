package drivers;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;
import java.io.File;

public class SafariDriverManager extends DriverManager {
    private SafariDriverService sfService;
    private DesiredCapabilities capabilities;

    public SafariDriverManager() {
        this.capabilities = DesiredCapabilities.safari();
    }

    @Override
    public void startService(){
        if(null==sfService){
            try{
                sfService = new SafariDriverService.Builder()
                        .usingDriverExecutable(new File("SafariDriver.safariextz"))
                        .usingAnyFreePort()
                        .build();
                sfService.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if(null!=sfService && sfService.isRunning())
            sfService.stop();
    }

    @Override
    public void createDriver() {
        SafariOptions options = new SafariOptions();
        capabilities.setCapability(SafariOptions.CAPABILITY, options);
        driver = new SafariDriver(options);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }
}
