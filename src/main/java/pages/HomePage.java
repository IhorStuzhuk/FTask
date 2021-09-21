package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//a[contains(@href,'facebook')]")
    protected WebElement facebookIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickFacebookIcon() {
        waitVisibilityOfElement(60, facebookIcon);
        facebookIcon.click();
    }

    public WebElement getFacebookIcon() {
        return facebookIcon;
    }
}
