package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.ArrayList;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SmokeSteps {
    public static final long DEFAULT_TIMEOUT = 90;
    WebDriver driver;
    HomePage homePage;
    BagPage bagPage;
    ProductPage productPage;
    SearchResultsPage searchResultsPage;
    SavedListPage savedListPage;
    Header header;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @Given("User opens {string}")
    public void userOpensPage(final String url) {
        driver.get(url);
    }

    @And("User goes to the 'ProductPage'")
    public void userGoesToTheProductPage() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickOnFirstProduct();
    }

    @When("User makes search by keyword {string}")
    public void userMakesSearchByKeyword(final String keyword) {
        header = pageFactoryManager.getHeader();
        header.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        header.enterTextToSearchField(keyword);
    }

    @And("User clicks search button")
    public void userClicksSearchButton() {
        header = pageFactoryManager.getHeader();
        header.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        header.clickSearchButton();
    }

    @And("User checks that the 'ProductPage' is visibility")
    public void userChecksThatTheProductPageIsVisibility() {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }

    @And("User selects size of the product")
    public void userSelectsSizeOfTheProduct() {
        productPage.clickSelectSizeDropdownList();
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getSelectSizeDropdownList());
        productPage.clickOnFirstSize();
        productPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
    }

    @And("User clicks 'Add to bag' button")
    public void userClicksAddToBagButton() {
        productPage.clickAddToBagButton();
        productPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        productPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
    }

    @Then("User checks that the 'MyBag' page has {int} products")
    public void userChecksThatTheMyBagPageHasCountProducts(final int count) {
        bagPage = pageFactoryManager.getBagPage();
        bagPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        bagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, bagPage.getBagItemsForm());
        assertEquals(count, bagPage.getAmountQuantityOfProductsInBag());
    }

    @And("User clicks remove button")
    public void userClicksRemoveButton() {
        bagPage = pageFactoryManager.getBagPage();
        bagPage.clickRemoveProductButton();
    }

    @Then("User checks the {string} is about product deleted from BagPage")
    public void userChecksThatTheProductDeletedFromBagPage(final String message) {
        bagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        bagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, bagPage.getEmptyBagText());
        assertEquals(message, bagPage.getEmptyBagText().getText());
    }

    @Then("User checks that the {string} is displayed")
    public void userChecksThatTheErrorMessageIsDisplayed(final String message) {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getErrorMessage());
        assertEquals(message, productPage.getErrorMessage().getText());
    }

    @Then("User increases quantity of product")
    public void userIncreasesQuantityOfProduct() {
        bagPage.clickDropDownQuantityList();
        bagPage.clickQuantityTwo();
        bagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, bagPage.getConfirmationForm());
        bagPage.clickUpdateButton();
    }

    @And("User checks that {string} was calculated correctly after increasing quantity of product")
    public void userChecksThatSumOfOrderWasCalculatedCorrectlyAfterIncreasingQuantityOfProduct(final String expectedPrice) {
        bagPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        bagPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        assertEquals(expectedPrice, bagPage.getSubtotalPriceText());
    }

    @Then("User checks that the url of 'Search result' page contains {string}")
    public void userChecksThatTheUrlOfSearchResultPageContainsQuery(final String query) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(driver.getCurrentUrl().contains(query));
    }

    @Then("User checks that the 'Search result' page displays a {string}")
    public void userChecksThatTheSearchResultPageDisplaysAMessage(final String message) {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(searchResultsPage.getMessageNothingFound().contains(message));
    }

    @When("User clicks the Facebook icon")
    public void userClicksTheFacebookIcon() {
        homePage = pageFactoryManager.getHomePage();
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getFacebookIcon());
        homePage.clickFacebookIcon();
    }

    @Then("User checks that the {string} page is open")
    public void userChecksThatTheFaceBookPageIsOpen(final String facebookPage) {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        assertEquals(driver.getCurrentUrl(), facebookPage);
    }

    @And("User clicks clear button")
    public void userClicksClearButton() {
        header = pageFactoryManager.getHeader();
        header.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        header.clickClearButton();
    }

    @Then("User checks that the history of search is cleared")
    public void userChecksThatTheHistoryOfSearchIsCleared() {
        assertTrue(header.getRecentSearchesListSize() == 0);
    }

    @And("User clicks 'Add to saved List' button")
    public void userClicksAddToSavedListButton() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitVisibilityOfAllElements(DEFAULT_TIMEOUT, searchResultsPage.getAddToSavedListButtons());
        searchResultsPage.clickAddSavedListButtons();
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
    }

    @And("User checks that the 'Saved List' page has {string} products")
    public void userChecksThatTheSavedListPageHasCountProducts(final String expectedCount) {
        savedListPage = pageFactoryManager.getSavedListPage();
        savedListPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        savedListPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        savedListPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        assertEquals(expectedCount, savedListPage.getItemsCount());
    }

    @Then("User clicks 'Delete from saved List' button")
    public void userClicksDeleteFromSavedListButton() {
        savedListPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        savedListPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        savedListPage.clickDeleteButton();
    }

    @And("User checks that the 'Saved List' page displays a {string}")
    public void userChecksThatTheSavedListPageDisplaysAMessage(final String message) {
        savedListPage.clickDeleteButton();
        savedListPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(savedListPage.getMessageText(), message);
    }

    @Then("User clicks ASC sorting")
    public void userClicksASCSorting() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.clickOnSortButton();
        searchResultsPage.clickOnAscSort();
    }

    @And("User checks that products are sorted by ASC")
    public void userChecksThatProductsAreSortedByASC() {
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        searchResultsPage.waitForAjaxToCompletePdp(DEFAULT_TIMEOUT);
        assertTrue(searchResultsPage.getProductsPrices().get(0) < searchResultsPage.getProductsPrices().get(1));
    }

    @When("User clicks on logo")
    public void userClicksOnLogo() {
        header = pageFactoryManager.getHeader();
        header.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        header.clickLogo();
    }

    @Then("User checks that the {string} is opened")
    public void userChecksThatTheHomepageIsOpened(final String url) {
        header.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(driver.getCurrentUrl().contains(url));
    }

    @Then("User checks that the price is displayed correctly taking into account the discount")
    public void userChecksThatThePriceIsDisplayedCorrectlyTakingIntoAccountTheDiscount() {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        double actualPrice = searchResultsPage.getFirstProductSaleAmount();
        double discount = searchResultsPage.getFirstProductDiscount();
        double firstPrice = searchResultsPage.getFirstProductPrice();
        double expectedPrice = firstPrice / 100 * discount;
        expectedPrice = firstPrice - expectedPrice;
        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

