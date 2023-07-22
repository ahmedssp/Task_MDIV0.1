package Base_Package;
import Pages.Home_Page;
import Pages.TodayDeals_Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;


public class base {
    protected WebDriver d;
    protected Home_Page Home_PgObj;
    protected TodayDeals_Page TodayDeals_PgObj;


    @BeforeMethod
    public void start() throws InterruptedException {
        d= new ChromeDriver();
        d.get("https://www.amazon.eg/");
        d.manage().window().maximize();
        Home_PgObj =new Home_Page(d);
        TodayDeals_PgObj=new TodayDeals_Page(d);

        //1-make hover of language tap
        Actions ac=new Actions(d);
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("icp-nav-flyout")));
        ac.moveToElement(d.findElement(By.id("icp-nav-flyout"))).build().perform();
        //2-click on en
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div [@id=\"nav-flyout-icp\"] //a[@href=\"#switch-lang=en_AE\"]")));
        d.findElement(By.xpath("//div [@id=\"nav-flyout-icp\"] //a[@href=\"#switch-lang=en_AE\"]")).click();
    }
    @AfterMethod
    public void end(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) d;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);

            try {
                Files.move(screenshot.toPath(), new File("resources/screenshots/" + result.getName() + ".png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        d.quit();

    }

    public Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }


}
