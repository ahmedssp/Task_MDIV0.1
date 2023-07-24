package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class Home_Page {
    private WebDriver d;
    public Home_Page(WebDriver d){this.d=d;}
    private By Hello_filed = By.xpath("//span[text()=\"Account & Lists\"]//span[@class=\"nav-icon nav-arrow\"]");
    private By SignIn_button_filed=By.xpath("//span[@class=\"nav-action-inner\"]");
    private By AllTap_filed = By.xpath("//i[@class=\"hm-icon nav-sprite\"]");
    private  By Todays_Deals_filed=By.xpath("//a[text()=\"Today's Deals\" and @class=\"hmenu-item\"]");

    public  void  Hover_Hello(){
        Actions ac=new Actions(d);
        waitf().until(ExpectedConditions.visibilityOfElementLocated(Hello_filed));
        ac.moveToElement(d.findElement(Hello_filed)).build().perform();
    }
    public LoginPage_Page Click_sigenin(){

        waitf().until(ExpectedConditions.visibilityOfElementLocated(SignIn_button_filed));
        d.findElement(SignIn_button_filed).click();
        return new LoginPage_Page(d);
    }
    public  void Click_AllTap() throws InterruptedException {
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"nav-hamburger-menu\" ]//span[text()=\"All\"]")));
        d.findElement(By.xpath("//a[@id=\"nav-hamburger-menu\" ]//span[text()=\"All\"]")).click();
    }
    public void Click_Todays_Deals() throws InterruptedException {
        Thread.sleep(2000);
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//*[text()=\"Today's Deals\"]")));
        d.findElement(By.xpath("//li//*[text()=\"Today's Deals\"]")).click();

    }
    public boolean Assert_seeLists_intro_screen( ){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class=\"al-intro-banner-header\"]")));
        return  d.findElement(By.cssSelector("span[class=\"al-intro-banner-header\"]")).getText().toLowerCase().contains("lists");
    }
    public boolean Assertion_WE_in_SignIN_page( ){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class=\"a-spacing-small\"]")));
        return d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("sign in");
    }
    public  Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
