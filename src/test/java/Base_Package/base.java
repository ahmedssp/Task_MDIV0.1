package Base_Package;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class base {
    protected WebDriver d;
    protected HomePage homePage_obj;


    @BeforeMethod
    public void Sutup() throws InterruptedException {

        System.out.println("WE are in before method");
        homePage_obj =new HomePage(d);
        d=new ChromeDriver();
        d.get("https://www.amazon.eg/");
        d.manage().window().maximize();




    }
    @AfterMethod
    public void Qiut() throws InterruptedException {
        d.quit();
        System.out.println("WE are in After method");
        }



}
