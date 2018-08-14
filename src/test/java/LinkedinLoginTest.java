import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Yanina123"},
                { "linkedin.TST.yanina@gmail.com", "Yanina123"},
        };
    }

    @Test (dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
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

    @DataProvider
    public Object[][] EmptyFieldsCombination() {
        return new Object[][]{
                { "", ""},
                { "", "P@ssword123"},
                { "someone@domain.com", ""}
        };
    }

    @Test(dataProvider = "EmptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on Login page.");
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
    public void validateShortUserEmailAndPassword() {
        linkedinLoginPage.login("q", "1");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
        "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUseShortEmailValidationText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "Too short Email error is incorrect");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserShortPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "Too short Password error is incorrect");

    }
}

