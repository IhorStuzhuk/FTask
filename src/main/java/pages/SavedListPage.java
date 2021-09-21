package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SavedListPage extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'itemCount')]")
    private WebElement itemsCount;

    @FindBy(xpath = "//button[contains(@class, 'deleteButton')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//h2[contains(@class, 'noItems')]")
    private WebElement message;

    public SavedListPage(WebDriver driver) {
        super(driver);
    }

    public String getItemsCount() {
        waitVisibilityOfElement(60,itemsCount);
        String count = itemsCount.getText();
        return count.substring(0, count.indexOf(" "));
    }

    public String getMessageText() {
        waitVisibilityOfElement(60,message);
       return message.getText();
    }

    public void clickDeleteButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
    }
}
