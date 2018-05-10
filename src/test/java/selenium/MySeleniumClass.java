package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MySeleniumClass {
    public enum DriverTypes {
        CHROME,
        FIREFOX,
        IE,
        EDGE
    }

    public boolean connected;

    public String pageTitle;

    WebDriver driver = null;
    WebDriverWait waitDriver = null;

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

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            waitDriver = new WebDriverWait(driver, 10);
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
            return driver.findElement(By.id(id)).isDisplayed();
        }
        return false;
    }

    public boolean waitUntilElementExistsByClass(String cls) {
        if (driver != null) {
            waitDriver.until(ExpectedConditions.elementToBeClickable(By.className(cls)));
            return true;
        }
        return false;
    }

    public boolean elementClickById(String id) {
        if (elementExistsByID(id)) {
            waitDriver.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
//            driver.findElement(By.id(id)).click();
            return true;
        }
        return false;
    }

    public void gotoURL(String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
            pageTitle = driver.getTitle();
        } else {
            driver.navigate().refresh();
        }
    }

    public boolean textFieldEnter(String id, String text) {
        if (elementExistsByID(id)) {
            driver.findElement(By.id(id)).clear();
            driver.findElement(By.id(id)).sendKeys(text);
            return true;
        }
        return false;
    }

    public boolean urlEndsIn(String text) {
        return driver.getCurrentUrl().endsWith(text);
    }



}
