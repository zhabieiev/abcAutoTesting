package drivers;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {
        DriverManager driverManager;
        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new GeckoDriverManager();
                break;
            case IE:
                driverManager = new IEDriverManager();
                break;
            case EDGE:
                driverManager = new EdgeDriverManager();
                break;
            case OPERA:
                driverManager = new OperaDriverManager();
                break;
            case SAFARI:
                driverManager = new SafariDriverManager();
                break;
            default:
                driverManager = new GeckoDriverManager();
                break;
        }
        return driverManager;
    }
}
