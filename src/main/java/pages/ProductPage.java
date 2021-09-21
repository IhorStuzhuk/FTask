package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//select[@data-id = 'sizeSelect']")
    private WebElement selectSizeDropdownList;

    @FindBy(xpath = "//select[@data-id = 'sizeSelect']//option")
    private List<WebElement> sizeList;

    @FindBy(xpath = "//button[@id = 'product-add-button']")
    private WebElement addToBagButton;

    @FindBy(xpath = "//span[@id = 'selectSizeError']")
    private WebElement errorMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickSelectSizeDropdownList() {
        selectSizeDropdownList.click();
    }

    public WebElement getSelectSizeDropdownList() {
        return selectSizeDropdownList;
    }

    public void clickOnFirstSize() {
        sizeList.get(1).click();
    }

    public void clickAddToBagButton() {
        addToBagButton.click();
        waitForPageLoadComplete(60);
        waitForAjaxToComplete(60);
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
