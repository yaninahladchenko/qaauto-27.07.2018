import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LinkedinSearchTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test
    public void basicSearchTest(){
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "LinkedinLoginPage is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginReturnHomePage("linkedin.tst.yanina@gmail.com", "Yanina123");
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Homepage is not loaded");
        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search("HR");
        Assert.assertTrue(linkedinSearchPage.isLoaded(), "SearchPage is not loaded");
        Assert.assertEquals(linkedinSearchPage.getSearchResultsCount(), 10, "Search results count is wrong.");
        //Assert.assertEquals();



    }


}
