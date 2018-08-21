import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkedinSearchPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//h3[contains(@class, 'total')]")
    private WebElement searchResultsTotal;


    public LinkedinSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
                && getCurrentPageTitle().contains("| Search | LinkedIn")
                && getCurrentPageUrl().contains("/search/results/");
    }

    public int getSearchResultsCount() {
        return searchResults.size();
    }
}
