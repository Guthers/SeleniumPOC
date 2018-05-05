package selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyTestClass {
    private MySeleniumClass selenium = new MySeleniumClass(MySeleniumClass.DriverTypes.IE);

    private String ID_COURSE_ENTRY = "courseCode";
    private String ID_SEMESTER_DROPDOWN = "btn-semester";
    private String ID_LOAD_BTN = "btn-load";

    @BeforeClass
    public void setup() {
        // Make sure that there were no exceptions in setup
        Assert.assertTrue(selenium.connected);

        // Make sure the page title is still as expected (page hasn't changed since setup)
        Assert.assertEquals("UQ Final Exam Calculator", selenium.pageTitle);
    }

    @AfterClass
    public void teardown() {
        // Close the webpage opened
        selenium.close();

        // Make sure the page closed correctly
        Assert.assertFalse(selenium.connected);
    }


    //  Make sure the main elements we will use exist
    @Test
    public void MainCourceEntryExists() {
        Assert.assertTrue(selenium.elementExistsByID(ID_COURSE_ENTRY));
    }

    @Test
    public void MainSemesterDropdownExists() {
        Assert.assertTrue(selenium.elementExistsByID(ID_SEMESTER_DROPDOWN));
    }

    @Test
    public void MainLoadButtonExists() {
        Assert.assertTrue(selenium.elementExistsByID(ID_LOAD_BTN));
    }

    @Test
    public void clickTheThings() {
        selenium.elementClickById(ID_COURSE_ENTRY);
        selenium.elementClickById(ID_SEMESTER_DROPDOWN);
        selenium.elementClickById(ID_LOAD_BTN);
    }

    @Test
    public void loadNewPage() {
        String val = "CSSE3002";
        Assert.assertTrue(selenium.textFieldEnter(ID_COURSE_ENTRY,val));
        selenium.elementClickById(ID_LOAD_BTN);
        Assert.assertTrue(selenium.urlEndsIn(val));
    }



}
