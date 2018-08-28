import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ChooseNewPasswordPage extends BasePage {

    @FindBy(xpath ="//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement retypeNewPasswordField;

    @FindBy(xpath = "//*[@id='reset-password-submit-button']")
    private WebElement submitButton;


    public ChooseNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void submitNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
        retypeNewPasswordField.sendKeys(newPassword);
        submitButton.click();
    }

    public boolean isLoaded() {
        return  submitButton.isDisplayed()
                && getCurrentPageTitle().contains("Reset Your Password | LinkedIn")
                && getCurrentPageUrl().contains("/password-reset");
    }

}


