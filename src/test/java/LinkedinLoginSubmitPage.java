import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage extends BasePage{

    private WebElement alertBox;
    private WebElement signInButton;
    private WebElement userEmailValidationText;
    private WebElement userPassValidationText;


    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        signInButton = browser.findElement(By.xpath("//*[@id='btn-primary']"));
        userEmailValidationText = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        userPassValidationText = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
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