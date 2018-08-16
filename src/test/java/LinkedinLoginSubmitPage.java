import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {

    WebDriver browser;

    private WebElement alertBox;
    private WebElement signInButton;
    private WebElement emailErrorMessage;
    private WebElement passwordErrorMessage;


    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        signInButton = browser.findElement(By.xpath("//*[@id='btn-primary']"));
        emailErrorMessage = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        passwordErrorMessage = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String getAlertBoxText() {
        return alertBox.getText();
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
    public String getEmailErrorMessageText() {
        return emailErrorMessage.getText();
    }
    public String getPasswordErrorMessageText() {
        return passwordErrorMessage.getText();
    }
}