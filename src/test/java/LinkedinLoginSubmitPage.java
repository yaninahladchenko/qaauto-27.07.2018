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

    public String getTooShortEmailErrorText() {
        return tooShortEmailError.getText();
    }

    public String getTooShortPasswordErrorText() {
        return tooShortPasswordError.getText();
    }
    public void setSignInButton() {
        signInButton.click();
    }
}