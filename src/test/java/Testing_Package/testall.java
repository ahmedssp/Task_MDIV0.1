package Testing_Package;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import static com.google.common.util.concurrent.Futures.withTimeout;
import static java.lang.Double.valueOf;
import static java.lang.Integer.parseInt;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.devtools.v111.page.Page.navigate;

public class testall {
   public WebDriver d;
    @BeforeMethod
    public void start() throws InterruptedException {
        d= new ChromeDriver();
        d.get("https://www.amazon.eg/");
        d.manage().window().maximize();
        waitf();
        //1-make hover of language tap
        Actions ac=new Actions(d);
        ac.moveToElement(d.findElement(By.id("icp-nav-flyout"))).build().perform();
        Thread.sleep(4000);
        //2-click on en
        d.findElement(By.xpath("//div [@id=\"nav-flyout-icp\"] //a[@href=\"#switch-lang=en_AE\"]")).click();
        Thread.sleep(4000);//TO CONFIRM ew are in EN page
        System.out.println("we are in before method!!");
    }
    @AfterMethod
    public void end() throws InterruptedException {
        d.quit();
        Thread.sleep(3000);
        System.out.println("we are in aftermethod!!");

    }
    @Test
    public void login_with_nonREjesterdEmail() throws InterruptedException {

        //1-make hover on hello tab
        new Actions(d).moveToElement(d.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
        //2- press on sigen in tab
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"nav-action-inner\"]")));
        d.findElement(By.xpath("//span[@class=\"nav-action-inner\"]")).click();
        //3-inter valideEmail_not register
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        d.findElement(By.id("ap_email")).sendKeys("ahmedabdelsalame20@gmail.com");
        //4-prtess continu to togin
        d.findElement(By.id("continue")).click();
        //5- asserion for cant logen
        SoftAssert softa=new SoftAssert();
        softa.assertTrue(d.findElement(By.xpath("//*[contains(text(),\"problem\")]")).getText().toLowerCase().contains("problem"));
        softa.assertAll();
    }
    @Test
    public void added_Items() throws InterruptedException {

        //1-click on all tap
        d.findElement(By.xpath("//i[@class=\"hm-icon nav-sprite\"]")).click();

        //2-from list select Today's Deals
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Today's Deals\" and @class=\"hmenu-item\"]")));
        d.findElement(By.xpath("//a[text()=\"Today's Deals\" and @class=\"hmenu-item\"]")).click();

        //3- clich second categoryes
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Home & Kitchen\" and contains(@class,\"GridPresets-module\")]")));

        d.findElement(By.xpath("//span[text()=\"Home & Kitchen\" and contains(@class,\"GridPresets-module\")]")).click();
        //4-click on First product

        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"DealCard-module__contentWithPadding_1mEcEYf1DvbvZJ9zcQCxtw\"]/a)[2]")));

        d.findElement(By.xpath("(//div[@class=\"DealCard-module__contentWithPadding_1mEcEYf1DvbvZJ9zcQCxtw\"]/a)[2]")).click();
        //>>>>>>>>wait for next step -5-


  // if q is not (5 -6)else (6)// some product have only one type
        //5-Click on 2nd item in this product
        try {
            waitf().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@class=\"a-unordered-list a-nostyle a-horizontal a-spacing-none\"]/li)[2]")));
            d.findElement(By.xpath("(//ul[@class=\"a-unordered-list a-nostyle a-horizontal a-spacing-none\"]/li)[2]")).click();

        }catch (Exception e ){System.out.println("this product have one item ");};

        //6-add q= 2
        String  productTitle=null;String price1="";Boolean x=true;
        try {
            productTitle=d.findElement(By.id("productTitle")).getText().toLowerCase();
             price1 = d.findElement(By.cssSelector("span[class=\"a-price-whole\"]")).getText().toLowerCase();
            waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity")));
               Select select=new Select(d.findElement(By.id("quantity")));
               select.selectByValue("2");
        }catch (Exception e ){System.out.println("this product have one Qty ");x=false;}
        //7- prress add add-to-cart-button
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
        d.findElement(By.id("add-to-cart-button")).click();
        try {
        d.findElement(By.id("attachSiNoCoverage-announce")).click();
        }catch (Exception e ){System.out.println("No offers");};
        //9-go to Chart
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count-container")));
        d.findElement(By.id("nav-cart-count-container")).click();
        //10- assert for
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=\"sc-badge-price-to-pay\"]")));
        String price2=d.findElement(By.cssSelector("div[class=\"sc-badge-price-to-pay\"]")).getText().toLowerCase();

        String name2=d.findElement(By.cssSelector("span[class=\"a-truncate-cut\"]")).getText().toLowerCase();
        SoftAssert ass=new SoftAssert();
           //1>assert qty
      if(!x){
            ass.assertTrue( d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("1"));}
      else
      {ass.assertTrue( d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("2"));}
           //2> name assertion
           ass.assertTrue( productTitle.contains(name2.substring(0, 10)));
           //3>price assertion
           ass.assertTrue( price2.contains(price1));
           //4>assert total price
        double totalprice1=Double.parseDouble(price1.replace(",",""));
        double totalprice2= Double.parseDouble(d.findElement(By.id("sc-subtotal-amount-activecart")).getText().toLowerCase().replace("egp","").replace(" ","").replace(",",""));

        if(!x){ ass.assertEquals(totalprice1,totalprice2);}else {ass.assertEquals(totalprice1*2,totalprice2);}
        ass.assertAll();
    }
     @Test
    public void user_cannot_see_orders_OR_Adress(){
         SoftAssert ass=new SoftAssert();
         //1.0-make hover on hello tab
         new Actions(d).moveToElement(d.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
         //2.0- click on order
         d.findElement(By.id("nav_prefetch_yourorders")).click();
         //3.0-assert user can’t see orders and go to  loginpage
         ass.assertTrue(d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("Sign in"));
         //4.0-back
         d.navigate().back();
         //1.1
         new Actions(d).moveToElement(d.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
         //2.1- click on adress
         d.findElement(By.id("nav_prefetch_youraddresses")).click();
         //3.1-assert user can’t see orders and go to  loginpage
         ass.assertTrue(d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("Sign in"));
         //4.1-back
         d.navigate().back();
         //1.2 mak hover on hello tab
         new Actions(d).moveToElement(d.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();
         ass.assertAll();


     };
    public Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }
}
