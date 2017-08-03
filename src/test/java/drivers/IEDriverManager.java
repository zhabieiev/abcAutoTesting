package drivers;

import java.io.File;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEDriverManager extends DriverManager {
    private InternetExplorerDriverService ieService;
    private DesiredCapabilities capabilities;

    public IEDriverManager() {
        this.capabilities =  DesiredCapabilities.internetExplorer();
    }

    @Override
    public void startService(){
        if(null==ieService){
            try{
                ieService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(new File("IEDriverServer.exe"))
                        .usingAnyFreePort()
                        .build();
                ieService.start();
                }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if(null!=ieService && ieService.isRunning())
            ieService.stop();
    }

    @Override
    public void createDriver() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.addCommandSwitches("test-type");
        driver = new InternetExplorerDriver(ieService, capabilities);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }
}