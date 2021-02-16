package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetbankPage extends PageObject {
    public NetbankPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "#txtMyClientNumber_field")
    public WebElement txt_clientNumber;

    @FindBy(id = "#txtMyPassword_field")
    public WebElement txt_clientPassword;

    public void validateLogon(){
        txt_clientNumber.isDisplayed();
        txt_clientNumber.sendKeys("23677277");
        txt_clientPassword.isDisplayed();
        txt_clientPassword.sendKeys("287837373");
    }

}
