import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {

    WebDriver browser;

    private WebElement alertBox;
    private WebElement wrongEmailError;
    private WebElement wrongPasswordError;
    private WebElement tooShortEmailError;
    private WebElement tooShortPasswordError;
    private WebElement signInButton;



    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        wrongEmailError = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        wrongPasswordError = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
        tooShortEmailError = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        tooShortPasswordError = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
        signInButton = browser.findElement(By.xpath("//*[@id='btn-primary']"));


    }

    public String getAlertBoxText() {
        return alertBox.getText();
    }

    public String getWrongEmailErrorText() {
        return wrongEmailError.getText();
    }

    public String getWrongPasswordErrorText() {
        return wrongPasswordError.getText();
    }

    public String getUseShortEmailValidationText() {
        return tooShortEmailError.getText();
    }

    public String getUserShortPasswordValidationText() {
        return tooShortPasswordError.getText();
    }
    public void setSignInButton() {
        signInButton.click();
    }
    public String getCurrentPageTitle(){
        return browser.getTitle();
    }
    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }
    public boolean isLoaded() {
        return  alertBox.isDisplayed()
                && getCurrentPageTitle().contains("Sign In to LinkedIn")
                && getCurrentPageUrl().contains("https://www.linkedin.com/uas/login-submit");
    }
}