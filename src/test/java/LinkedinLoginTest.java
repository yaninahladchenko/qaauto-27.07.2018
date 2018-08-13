import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LinkedinLoginTest {

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
    public void successfulLoginTest() {
        linkedinLoginPage.login("linkedin.tst.yanina@gmail.com", "Yanina123");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Homepage is not loaded");
    }

    @Test
    public void negativeLoginTest() {
        linkedinLoginPage.login("a@b.c", "wrong");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message");
    }

    @Test
    public void loginTestWithEmptyEmailAndPasswordFields() {
        linkedinLoginPage.login("", "");
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "LinkedinLoginPage is not loaded");
    }


    @Test
    public void loginTestWithWrongEmailAndPasswordFields() {
        linkedinLoginPage.login("@ukr.net", "wrongpass");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getWrongEmailErrorText(),
                "Please enter a valid email address.",
                "Wrong Email error is incorrect");

        linkedinLoginSubmitPage.setSignInButton();
        Assert.assertEquals(linkedinLoginSubmitPage.getWrongPasswordErrorText(),
                "Please enter a password.",
                "Wrong Password error is incorrect");
     }

    @Test
    public void loginTestWithTooShortEmailAndPassword() {
        linkedinLoginPage.login("q", "1");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getTooShortEmailErrorText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "Too short Email error is incorrect");

        Assert.assertEquals(linkedinLoginSubmitPage.getTooShortPasswordErrorText(),
                "The password you provided must have at least 6 characters.",
                "Too short Password error is incorrect");

    }
}

