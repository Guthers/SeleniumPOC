package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MySeleniumClass {
    public boolean connected;

    public String pageTitle = "";
    private String url = "https://uqfinal.com/";

    WebDriver driver = null;

    public MySeleniumClass() {
        try {
            // Set the location of the chrome driver
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");

            // Load the chrome driver
            driver = new ChromeDriver();

            driver.get(url);
            pageTitle = driver.getTitle();

            this.connected = true;
        } catch (Exception e) {
            this.connected = false;
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
