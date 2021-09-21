package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Header extends BasePage {
    @FindBy(xpath = "//a[@data-testid='asoslogo']")
    private WebElement asosLogo;

    @FindBy(xpath = "//input[@type = 'search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@data-testid= 'search-button-inline']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@data-testid='clear-recent-searches']")
    private WebElement clearButton;

    @FindBy(xpath = "//div[@data-testid='recent-searches']//li")
    private List<WebElement> recentSearchesList;

    public Header(WebDriver driver) {
        super(driver);
    }

    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickClearButton() {
        waitVisibilityOfElement(90, clearButton);
        clearButton.click();
    }

    public int getRecentSearchesListSize() {
        return recentSearchesList.size();
    }

    public void clickLogo() {
        asosLogo.click();
    }
}
