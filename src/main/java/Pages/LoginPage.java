package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver d;
    public LoginPage(WebDriver d){this.d=d;}
    private By firestnameField = By.id("nf-field-17");
}
