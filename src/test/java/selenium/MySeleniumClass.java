package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class MySeleniumClass {
    public enum DriverTypes {
        CHROME,
        FIREFOX,
        IE,
        EDGE
    }


    public boolean connected;

    public String pageTitle = "";
    private String url = "https://uqfinal.com/";

    WebDriver driver = null;

    public MySeleniumClass(DriverTypes dt) {
        try {
            switch (dt) {
                case EDGE:
                    System.setProperty("webdriver.edge.driver","src/test/resources/MicrosoftWebDriver.exe");
                    driver = new EdgeDriver();
                    break;
                case CHROME:
                    System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case IE:
                    System.setProperty("webdriver.ie.driver","src/test/resources/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    throw new Exception("Unsupported Driver Provided");
            }

            driver.get(url);
            pageTitle = driver.getTitle();

            this.connected = true;
        } catch (Exception e) {
            this.connected = false;
            e.printStackTrace();
        }
    }

    public void close() {
        if (driver != null) {
            driver.close();
            this.connected = false;
        }
    }

    //courseCode
    public boolean elementExistsByID(String id) {
        if (driver != null) {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
            }
            return driver.findElement(By.id(id)).isDisplayed();
        }
        return false;
    }

    public boolean elementClickById(String id) {
        if (elementExistsByID(id)) {
            driver.findElement(By.id(id)).click();
        }
        return false;
    }

    public boolean textFieldEnter(String id, String text) {
        if (elementExistsByID(id)) {
            driver.findElement(By.id(id)).clear();
            driver.findElement(By.id(id)).sendKeys(text);
            return true;
        }
        return false;
    }

    public Boolean pageHasText(String text) {
        return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]")).isDisplayed();
    }

    public boolean urlEndsIn(String text) {
        return driver.getCurrentUrl().endsWith(text);
    }




}
