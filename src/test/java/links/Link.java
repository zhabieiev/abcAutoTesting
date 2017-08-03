package links;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Link {

    //    info about HTTP request to the url
    public void requestMessage(HttpURLConnection connection) throws IOException {
        // Output method of request
        System.out.println("Request method is: " + connection.getRequestMethod());
        // Output status of request
        System.out.println("Response: " + connection.getResponseMessage());
        // Get list of fields and keys from header
        Map<String, List<String>> myMap = connection.getHeaderFields();
        Set<String> myField = myMap.keySet();
        System.out.println("\nNext following header:");
        // Output all keys and values from header
        for (String k : myField) {
            System.out.println("Key: " + k + " Value: " + myMap.get(k));
        }
    }

    //    finding all links on page
    public List<WebElement> findAllLinks(RemoteWebDriver driver) {
        List<WebElement> elementList = new ArrayList();
        elementList.addAll(driver.findElements(By.tagName("a")));
//        elementList.addAll(driver.findElements(By.tagName("img")));
        List<WebElement> finalList = new ArrayList();
        for (WebElement element : elementList) {
            if (element.getAttribute("href") != null) {
                finalList.add(element);
            }
        }
        return elementList;
    }

    //    checking each link
    public int checkLink(RemoteWebDriver driver) throws IOException {
        System.out.println("Total links are " + findAllLinks(driver).size());
        HttpURLConnection httpURLConnect = null;
        try {
            for (int i = 0; i < findAllLinks(driver).size(); i++) {
                WebElement element = (WebElement) findAllLinks(driver).get(i);
                String url = element.getAttribute("href");
                System.out.println(url + " - OK");
                URL currentURL = new URL(url);
                httpURLConnect = (HttpURLConnection) currentURL.openConnection();
//                httpURLConnect.setConnectTimeout(3000);
                httpURLConnect.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpURLConnect.getResponseCode();
    }

//    public String[] extractLink(RemoteWebDriver driver){
//        String[] linkTexts = new String[findAllLinks(driver).size()];
//        int i = 0;
//        for (WebElement element : findAllLinks(driver)) {
//            linkTexts[i] = element.toString();
////            System.out.println(linkTexts[i]);
//            i++;
//        }
//        return linkTexts;
//    }

    public void clickLink(RemoteWebDriver driver){

        String[] linkTexts = new String[findAllLinks(driver).size()];
        int i = 0;
        for (WebElement element : findAllLinks(driver)) {
            linkTexts[i] = element.toString();
//            System.out.println(linkTexts[i]);
            i++;
        }

        //test each link
        for (String t : linkTexts) {
            try {
                driver.findElement(By.linkText(t)).click();
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                if (driver.getTitle().equals("")) {
                    System.out.println("\"" + t + "\"" + " is not working.");
                    driver.navigate().back();
                }
                else {
                    System.out.println("\"" + t + "\"" + " is working.");
                }
            } catch(NoSuchElementException exp){
            }
            driver.navigate().back();
        }
    }
}

