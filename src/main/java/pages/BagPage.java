package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BagPage extends BasePage {
    @FindBy(xpath = "//div [@class='bag-item-padding']")
    private WebElement bagItemsForm;

    @FindBy(xpath = "//span[@aria-label = 'Quantity']/span[@class = 'select2-selection__rendered']")
    private List<WebElement> quantityList;

    @FindBy(xpath = "//button[@class = 'bag-item-remove']")
    private WebElement removeProductButton;

    @FindBy(xpath = "//h2[@class ='empty-bag-title']")
    private WebElement emptyBagText;

    @FindBy(xpath = "//select[contains(@class, 'bag-item-quantity')]")
    private WebElement dropDownQuantityList;

    @FindBy(xpath = "//select[contains(@class, 'bag-item-quantity')]//option[2]")
    private WebElement quantityTwo;

    @FindBy(xpath = "//div[contains(@class, 'bag-item-edit-slider')]")
    private WebElement confirmationForm;

    @FindBy(xpath = "//div[contains(@class, 'bag-item-edit-slider')]//button[contains(@class, 'update')]")
    private WebElement updateButton;

    @FindBy(xpath = "//span[@class = 'bag-subtotal-price']")
    private WebElement subtotalPrice;

    public BagPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getConfirmationForm() {
        return confirmationForm;
    }

    public int getAmountQuantityOfProductsInBag() {
        int sum = 0;
        for (WebElement webElement : quantityList) {
            sum += Integer.parseInt(webElement.getAttribute("title"));
        }
        return sum;
    }

    public void clickRemoveProductButton() {
        removeProductButton.click();
    }

    public void clickDropDownQuantityList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dropDownQuantityList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickQuantityTwo() {
        quantityTwo.click();
    }

    public void clickUpdateButton() {
        updateButton.click();
    }

    public WebElement getEmptyBagText() {
        return emptyBagText;
    }

    public String getSubtotalPriceText() {
        String getPrice = subtotalPrice.getText().replace(",", "");
        int i = 0;
        while (!Character.isDigit(getPrice.charAt(i))) {
            i++;
        }
        String price = getPrice.substring(i);
        return price;
    }

    public WebElement getBagItemsForm() {
        return bagItemsForm;
    }
}
