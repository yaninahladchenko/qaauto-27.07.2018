import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;


public class ForgotPasswordPage extends BasePage {

    @FindBy(xpath ="//*[@id='username']")
    private WebElement emailOrPhoneField;

    @FindBy (xpath ="//*[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    @FindBy(xpath ="//*[@class='form__cancel']")
    private WebElement cancelButton;


    public ForgotPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void cancelButton() {
        cancelButton.click();
    }

    public ResetPasswordRequestPage findAccount(String userEmail) {
        emailOrPhoneField.sendKeys(userEmail);
        findAccountButton.click();
        return new ResetPasswordRequestPage(browser);

    }

    public boolean isLoaded() {
        return emailOrPhoneField.isDisplayed()
                && getCurrentPageTitle().contains("Reset Password | LinkedIn")
                && getCurrentPageUrl().contains("/request-password-reset");
    }

}


