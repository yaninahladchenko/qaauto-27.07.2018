import org.openqa.selenium.WebDriver;
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
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.loginReturnHomePage(userEmail, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Homepage is not loaded");
    }

    @Test
    public void negativeLoginTest() {
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginReturnLoginSubmitPage("a@b.c", "wrong");

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
        linkedinLoginPage.loginReturnLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on Login page.");
    }


    @DataProvider
    public Object[][] invalidLoginErrorMessages() {
        return new Object[][]{
                { "a",
                        "a",
                        "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                        "The password you provided must have at least 6 characters."},
                { "linkedin.tst.yanina@gmail.com",
                        "YANINA123",
                        "",
                        "Hmm, that's not the right password. Please try again or request a new one."},
                { "linkedin.yanina@gmail.com",
                        "Yanina123",
                        "Hmm, we don't recognize that email. Please try again.",
                        ""},
                { "@ukr.net",
                        "wrongpass",
                        "Please enter a valid email address.",
                        ""},
                {"dgdhfgdfgghghgygyghgkhjhgfjhfyfuyffghghfghjfjhhgghgcghfhffhkhjghgff1234567898765fghvbfvbhgfvbhgfvbhgvbhg@hjhhhgkhjgkjhgkjg.bjkhjg",
                        "Yanina123",
                        "The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).",
                        ""},
        };
    }

    @Test(dataProvider = "invalidLoginErrorMessages")
    public void validateUserEmailAndPassword(String userEmail, String userPass,String userEmailValidationText, String userPassValidationText){
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginReturnLoginSubmitPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getEmailErrorMessageText(), userEmailValidationText,
                "Email error is incorrect");
        Assert.assertEquals(linkedinLoginSubmitPage.getPasswordErrorMessageText(), userPassValidationText,
                "Password error is incorrect");
    }


}

