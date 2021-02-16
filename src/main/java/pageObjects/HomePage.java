package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import sun.tools.jconsole.JConsole;

import java.util.List;

public class HomePage extends PageObject {
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[contains(text(),'Activity log')]")
    private WebElement btn_activityLog;

    @FindBy(css = "#data-target-body-1 > ul > li:nth-child(3) > a")
    private List<WebElement> link_transferMoney;

    @FindBy(css = "body > header > div > div.commbank-header-module > ul.commbank-header-search-wrapper > li:nth-child(2) > a > span.login")
    private WebElement link_netLogon;

    @FindBy(css= "#logonModal > div > div > ul > li:nth-child(1) > a")
    private WebElement link_netbankLogin;

    public void validateUIForHomePage(){
        try {
            link_netLogon.wait(10);
            Assert.assertTrue(link_netLogon.isDisplayed());
        }
        catch(Exception ex){

        }
    }

    public void clickOnTransferMoney(){
        link_transferMoney.get(0).click();
    }

    public void clickOnNetBank(){
        link_netLogon.click();
        Assert.assertTrue(link_netbankLogin.isDisplayed());
        link_netbankLogin.click();

    }

}
