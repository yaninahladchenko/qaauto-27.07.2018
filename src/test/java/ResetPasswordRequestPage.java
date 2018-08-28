import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ResetPasswordRequestPage extends BasePage {

    @FindBy(xpath ="//button[@class='resend__link']")
    private WebElement resendLinkButton;

    @FindBy(xpath = "//h2[@class='form__subtitle']")
    private WebElement resetPasswordRequestPageSubtitle;


    public ResetPasswordRequestPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void resendLinkButton() {
        resendLinkButton.click();
    }

    public boolean isLoaded() {
        return  resetPasswordRequestPageSubtitle.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("/checkpoint/rp/request-password-reset-submit");
    }

    public ChooseNewPasswordPage navigateToUrlFromEmail() {
        return new ChooseNewPasswordPage(browser);
    }
}


