package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObject {
    protected static WebDriver driver;

    @FindBy(css = "circle.person__head")
    private static WebElement logout;

    @FindBy(xpath = "//*[contains(text(), 'Logout')]")
    private static WebElement logout_Button;

    @FindBy(xpath = "//a[contains(.,'Clients')]")
    private static WebElement clients;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void logOut() {
        logout.click();
        logout_Button.click();

    }

}
