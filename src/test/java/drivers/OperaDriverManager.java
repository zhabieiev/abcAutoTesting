package drivers;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

public class OperaDriverManager extends DriverManager {
    private OperaDriverService oService;
    private DesiredCapabilities capabilities;

    public OperaDriverManager() {
        this.capabilities = DesiredCapabilities.opera();
    }

    @Override
    public void startService(){
        if(null==oService){
            try{
                oService = new OperaDriverService.Builder()
                        .usingDriverExecutable(new File("operadriver.exe"))
                        .usingAnyFreePort()
                        .build();
                oService.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if(null!=oService && oService.isRunning())
            oService.stop();
    }

    @Override
    public void createDriver() {
        OperaOptions options = new OperaOptions();
        options.addArguments("test-type");
        capabilities.setCapability(OperaOptions.CAPABILITY, options);
        driver = new OperaDriver(oService, capabilities);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }
}
