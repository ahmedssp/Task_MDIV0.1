package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class HomePage {
    private WebDriver d;
    public HomePage(WebDriver d){this.d=d;}//

    private By LanguageTap_filed = By.id("icp-nav-flyouty");
    private By EN_Language=By.xpath("//div[@id=\"nav-al-wishlist\"]//span");
    private By HelloTap_filed = By.id("nav-link-accountList");
    private By SignIn_button_filed=By.cssSelector("span[class=\"nav-action-inner\"]");

    public void EN_Homepage() throws InterruptedException {
        d.findElement(By.id("icp-nav-flyout")).click();
//        d.findElement(LanguageTap_filed).click();
//        Thread.sleep(2000);
//        Actions action = new Actions(d);
//        action.moveToElement(d.findElement(LanguageTap_filed));
//        d.findElement(EN_Language).click();
    }
    public LoginPage LoginPage_CLICK(){
        Actions action = new Actions(d);
        action.moveToElement(d.findElement(HelloTap_filed)).moveToElement(d.findElement(SignIn_button_filed)).click();
        return new LoginPage(d);
    }

    public void print(){
        System.out.println("this is method in pages");
    }

}
