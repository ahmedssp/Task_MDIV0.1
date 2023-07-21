package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Random;

public class HomePage  {
    private WebDriver d;
    public HomePage(WebDriver d){this.d=d;}

    private By Hello_filed = By.id("nav-link-accountList-nav-line-1");
    private By EN_Language=By.xpath("//div[@id=\"nav-al-wishlist\"]//span");
    private By HelloTap_filed = By.id("nav-link-accountList");
    private By SignIn_button_filed=By.cssSelector("span[class=\"nav-action-inner\"]");


    public  void  Hover_Hello(){
        Actions ac=new Actions(d);
        waitf().until(ExpectedConditions.visibilityOfElementLocated(Hello_filed));
        ac.moveToElement(d.findElement(Hello_filed)).build().perform();
    }

    public LoginPage Click_sigenin(){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"nav-action-inner\"]")));
        d.findElement(By.xpath("//span[@class=\"nav-action-inner\"]")).click();
        return new LoginPage(d);
    }


    public  Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
