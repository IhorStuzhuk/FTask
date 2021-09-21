package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//section//article//a")
    private List<WebElement> productList;

    @FindBy(xpath = "//span[@data-auto-id='productTileSaleAmount']")
    private List<WebElement> productSaleAmountList;

    @FindBy(xpath = "//span[@data-auto-id='productTilePrice']//span")
    private List<WebElement> productPriceList;

    @FindBy(xpath = "//div[@class='_1MVUcS8']")
    private List<WebElement> productDiscountList;

    @FindBy(xpath = "//li[@data-dropdown-id='sort']")
    private WebElement sortButton;

    @FindBy(xpath = "//li[@id='plp_web_sort_price_low_to_high']")
    private WebElement ascSort;

    @FindBy(xpath = "//h2[text()[contains(.,'NOTHING')]]")
    private WebElement messageNothingFound;

    @FindBy(xpath = "//button[@data-auto-id = 'saveForLater']")
    private List<WebElement> savedListButtons;

    @FindBy(xpath = "//ul[@data-test-id='sortOptions']")
    private WebElement sortOptions;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSortButton() {
        sortButton.click();
    }

    public String getMessageNothingFound() {
        return messageNothingFound.getText().toLowerCase();
    }

    public void clickOnAscSort() {
        waitVisibilityOfElement(60, sortOptions);
        ascSort.click();
    }

    public void clickOnFirstProduct() {
        productList.get(0).click();
    }

    public List<Double> getProductsPrices() {
        double productPrice1 = getPrice(productSaleAmountList.get(0).getText());
        double productPrice2 = getPrice(productSaleAmountList.get(1).getText());
        return Arrays.asList(productPrice1, productPrice2);
    }

    public List<WebElement> getAddToSavedListButtons() {
        return savedListButtons;
    }

    public void clickAddSavedListButtons() {
        waitVisibilityOfAllElements(90, savedListButtons);
        savedListButtons.get(0).click();
    }

    public double getFirstProductPrice() {
        return getPrice(productPriceList.get(0).getText());
    }

    public double getFirstProductSaleAmount() {
        return getPrice(productSaleAmountList.get(0).getText());
    }

    public double getFirstProductDiscount() {
        return getPrice(productDiscountList.get(0).getText());
    }

    private double getPrice(String price) {
        int i = 0;
        while (!Character.isDigit(price.charAt(i))) {
            i++;
        }
        if (price.contains("%"))
            return Double.parseDouble(price.substring(i, price.indexOf("%")));
        else return Double.parseDouble(price.substring(i));
    }
}
