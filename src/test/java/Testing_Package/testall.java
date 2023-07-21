package Testing_Package;
import Base_Package.base;
import Pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;


public class testall  extends base {


    @Test(priority = 1)
    public void login_with_nonREjesterdEmail() throws InterruptedException {

        //1-make hover on hello tab
         homePage_obj.Hover_Hello();
        //2- press on sigen in tab
        LoginPage loginPage_obj= homePage_obj.Click_sigenin();
        //3-inter valideEmail_not register
        loginPage_obj.send_unregister_mail("ahmedabdelsalame20@gmail.com");
        //4-prtess continu to togin
        loginPage_obj.click_continue();
        //5- asserion for cant logen
       Assert.assertTrue(loginPage_obj.Assertion_login());

    }    @Test(priority = 2)
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
     @Test(priority = 3)
    public void Scenario_3() {
        //instiate soft assertion
         SoftAssert ass = new SoftAssert();
        // create array list of by locator to use in for loop
         ArrayList<By> LI = new ArrayList<By>();
         LI.add(By.id("nav_prefetch_yourorders"));
         LI.add(By.id("nav_prefetch_youraddresses"));
         LI.add(By.xpath("//span[text()=\"Your Lists\"]"));


         for (int i = 0; i < 3; i++) {
             waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-link-accountList-nav-line-1")));
             new Actions(d).moveToElement(d.findElement(By.id("nav-link-accountList-nav-line-1"))).build().perform();

             System.out.println(d.findElement(LI.get(i)).getText());
             waitf().until(ExpectedConditions.visibilityOfElementLocated(LI.get(i)));
             d.findElement(LI.get(i)).click();

             if (i == 0) {
                 waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class=\"a-spacing-small\"]")));
                 ass.assertTrue(d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("sign in"));
                 d.navigate().back();

             } else if (i == 1) {
                 waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1[class=\"a-spacing-small\"]")));
                 ass.assertTrue(d.findElement(By.cssSelector("h1[class=\"a-spacing-small\"]")).getText().toLowerCase().contains("sign in"));
                 d.navigate().back();

             } else if (i == 2) {
                 waitf().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class=\"al-intro-banner-header\"]")));
                 ass.assertTrue(d.findElement(By.cssSelector("span[class=\"al-intro-banner-header\"]")).getText().toLowerCase().contains("lists"));
                 d.navigate().back();

             }

         }

     }

}
