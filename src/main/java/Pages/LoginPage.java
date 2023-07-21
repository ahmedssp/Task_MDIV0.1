package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class LoginPage {
    private WebDriver d;
    public LoginPage(WebDriver d){this.d=d;}
    private By firestnameField = By.id("nf-field-17");
    public  void send_unregister_mail(String unregMail){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        d.findElement(By.id("ap_email")).sendKeys(unregMail);
    }
    public void click_continue(){
        d.findElement(By.id("continue")).click();

    }
    public boolean Assertion_login(){
        return d.findElement(By.xpath("//*[contains(text(),\"problem\")]")).getText().toLowerCase().contains("problem");
    }
    public Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
