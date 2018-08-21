import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage extends BasePage{

    @FindBy (xpath ="//*[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath ="//*[@id='btn-primary']")
    private WebElement signInButton;

    @FindBy(xpath ="//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    @FindBy (xpath ="//span[@id='session_password-login-error']")
    private WebElement userPassValidationText;


    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }


    public String getAlertBoxText() {
        return alertBox.getText();
    }

    public void setSignInButton() {
        signInButton.click();
    }

    public boolean isLoaded() {
        return  alertBox.isDisplayed()
                && getCurrentPageTitle().contains("Sign In to LinkedIn")
                && getCurrentPageUrl().contains("https://www.linkedin.com/uas/login-submit");
    }
    public String getEmailErrorMessageText() {
        return userEmailValidationText.getText();
    }
    public String getPasswordErrorMessageText() {
        return userPassValidationText.getText();
    }
}