package drivers;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;

public class EdgeDriverManager extends DriverManager {
    private EdgeDriverService edgeService;
    private DesiredCapabilities capabilities;

    public EdgeDriverManager() {
        this.capabilities = DesiredCapabilities.edge();
    }

    @Override
    public void startService(){
        if(null==edgeService){
            try{
                edgeService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File("MicrosoftWebDriver.exe"))
                        .usingAnyFreePort()
                        .build();
                edgeService.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if(null!=edgeService && edgeService.isRunning())
            edgeService.stop();
    }

    @Override
    public void createDriver() {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy("test-type");
        driver = new EdgeDriver(edgeService, capabilities);
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }
}
