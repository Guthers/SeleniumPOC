package selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestClass {
    private MySeleniumClass selenium = new MySeleniumClass(MySeleniumClass.DriverTypes.CHROME);
    private UQFinalDriver UQFDriver = new UQFinalDriver(selenium);


    @BeforeClass
    public void setup() {
        // Make sure that there were no exceptions in setup
        Assert.assertTrue(selenium.connected);

        // Make sure the page title is still as expected (page hasn't changed since setup)
        selenium.gotoURL(UQFDriver.URL);
        Assert.assertEquals("UQ Final Exam Calculator", selenium.pageTitle);
    }

    @AfterClass
    public void teardown() {
        // Close the webpage opened
        selenium.close();

        // Make sure the page closed correctly
        Assert.assertFalse(selenium.connected);
    }

    @BeforeTest
    public void refreshPage() {
        UQFDriver.loadMainPage();
    }


    //  Make sure the main elements we will use exist
    @Test
    public void MainCourseEntryExists() {
        Assert.assertTrue(selenium.elementExistsByID(UQFDriver.ID_COURSE_ENTRY));
    }

    @Test
    public void MainSemesterDropdownExists() {
        Assert.assertTrue(selenium.elementExistsByID(UQFDriver.ID_SEMESTER_DROPDOWN));
    }

    @Test
    public void MainLoadButtonExists() {
        Assert.assertTrue(selenium.elementExistsByID(UQFDriver.ID_LOAD_BTN));
    }

    @Test
    public void clickTheThings() {
        selenium.elementClickById(UQFDriver.ID_LOAD_BTN);
        selenium.elementClickById(UQFDriver.ID_COURSE_ENTRY);
        selenium.elementClickById(UQFDriver.ID_SEMESTER_DROPDOWN); //Needs to go last otherwise dropdown menu causes issues
    }

    @Test
    public void loadNewPage() {
        UQFDriver.loadMainPage();
        String val = "CSSE3002";
        Assert.assertTrue(selenium.textFieldEnter(UQFDriver.ID_COURSE_ENTRY,val));
        selenium.elementClickById(UQFDriver.ID_LOAD_BTN);
        Assert.assertTrue(selenium.urlEndsIn(val));
        Assert.assertTrue(selenium.waitUntilElementExistsByClass(UQFDriver.CLASS_NAME_ASSESMENT));
    }



    @Test
    public void loadNewPageDSL() {
        UQFDriver.loadCourse("CSSE3002");
        Assert.assertTrue(selenium.urlEndsIn("CSSE3002"));
        Assert.assertTrue(selenium.waitUntilElementExistsByClass(UQFDriver.CLASS_NAME_ASSESMENT));
    }

    @Test public void getPercentage() {
        UQFDriver.loadCourse("CSSE3002");
        Assert.assertEquals(UQFDriver.getPercentage(),"50%");
    }

    @Test public void enterValues() {
        UQFDriver.loadCourse("CSSE3002");
        String[] scores = {"1/1","1/2","1/3","1/4",""};
        Assert.assertTrue(UQFDriver.enterScores(scores));
        Assert.assertEquals(UQFDriver.getPercentage(),"59%");
        UQFDriver.changeGrade(5);
        Assert.assertEquals(UQFDriver.getPercentage(),"96%");
    }




}
