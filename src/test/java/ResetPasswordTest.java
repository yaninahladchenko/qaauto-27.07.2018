import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ResetPasswordTest {
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
    public void BasicResetPasswordTest() throws InterruptedException {
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "LinkedinLoginPage is not loaded.");

        ForgotPasswordPage forgotPasswordPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(forgotPasswordPage.isLoaded(), "ForgotPasswordPage is not loaded.");

        ResetPasswordRequestPage resetPasswordRequestPage = forgotPasswordPage.findAccount("linkedin.tst.yanina@gmail.com");
        Assert.assertTrue(resetPasswordRequestPage.isLoaded(), "ResetPasswordRequestPage is not loaded.");

        sleep(120000);
        //Navigate to URL from email manually while test is sleeping
        ChooseNewPasswordPage chooseNewPasswordPage = resetPasswordRequestPage.navigateToUrlFromEmail();
        Assert.assertTrue(chooseNewPasswordPage.isLoaded(), "ChooseNewPasswordPage is not loaded.");
        chooseNewPasswordPage.submitNewPassword("Mykola123");

    }


}
