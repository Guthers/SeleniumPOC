package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class UQFinalDriver {

    public String ID_COURSE_ENTRY = "courseCode";
    public String ID_SEMESTER_DROPDOWN = "btn-semester";
    public String ID_LOAD_BTN = "btn-load";

    public String CLASS_NAME_ASSESMENT = "assessment";

    public String URL = "https://uqfinal.com/";

    MySeleniumClass driver;
    Boolean connected = false;

    public UQFinalDriver(MySeleniumClass driver) {
        this.driver = driver;
        this.connected = driver.connected;
    }

    public void loadCourse(String course) {
        loadMainPage();
        driver.textFieldEnter(ID_COURSE_ENTRY,course);
        driver.elementClickById(ID_LOAD_BTN);
    }

    public boolean enterScores(String[] scores) {
        int size = driver.driver.findElements(By.className("assessmentScore")).size();
        if (size == scores.length) {
            for (int i=0; i < size; i++) {
                WebElement we = driver.driver.findElements(By.className("assessmentScore")).get(i);
                we.sendKeys(scores[i]);
            }
            return true;
        }
        return false;
    }

    public String getPercentage() {
        if (driver.driver.getCurrentUrl().equals(URL)) {
            return null;
        }
        driver.waitDriver.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@data-bind='text: requiredScoreForSelectedGrade']")));
        driver.waitDriver.until(ExpectedConditions.visibilityOf(driver.driver.findElement(By.xpath("//span[@data-bind='text: requiredScoreForSelectedGrade']"))));
        WebElement we = driver.driver.findElement(By.xpath("//span[@data-bind='text: requiredScoreForSelectedGrade']"));
        return we.getText();
    }

    public void changeGrade(int grade) {
        driver.waitDriver.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//select[@data-bind='options: possibleGrades, value: selectedGrade']")));
        driver.waitDriver.until(ExpectedConditions.visibilityOf(driver.driver.findElement(By.xpath("//select[@data-bind='options: possibleGrades, value: selectedGrade']"))));
        WebElement we = driver.driver.findElement(By.xpath("//select[@data-bind='options: possibleGrades, value: selectedGrade']"));
        we.click();
        we.sendKeys(String.valueOf(grade));
        we.sendKeys(Keys.RETURN);
    }

    public void loadMainPage() {
        driver.gotoURL(URL);
    }
}
