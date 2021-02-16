package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TravelMoney extends PageObject {
    public TravelMoney(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "body > div.container-fluid.app > div.container.hero-container > div > div > div > div.banner-content-panel > div > h1")
    private WebElement text_pageTitle;

    public void validateTravelPageHasLoaded(){
        Assert.assertTrue(text_pageTitle.isDisplayed(), "Travel page has not loaded");
    }
}
