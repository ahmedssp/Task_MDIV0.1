package Testing_Package;

import Base_Package.base;
import Pages.Cart_Page;
import Pages.LoginPage_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;


public class testall extends base {
    
    @Test(priority = 1)
    public void login_with_nonREjesterdEmail() throws InterruptedException {

        //1-make hover on hello tab
        Home_PgObj.Hover_Hello();
        //2- press on sigen in tab
        LoginPage_Page loginPagePage_obj = Home_PgObj.Click_sigenin();
        //3-inter valideEmail_not register
        loginPagePage_obj.send_unregister_mail("ahmedabdelsalame20@gmail.com");
        //4-prtess continu to togin
        loginPagePage_obj.click_continue();
        //5- asserion for cant logen
        Assert.assertTrue(loginPagePage_obj.Assertion_login());
    }    @Test(priority = 2)
    public void added_Items() throws InterruptedException {
        //1-click on all tap
        Home_PgObj.Click_AllTap();
        //2-from list select Today's Deals
        Home_PgObj.Click_Todays_Deals();
        //3- click second categoryes
        TodayDeals_PgObj.click_second_categoryes();
        //4-click on First product
        TodayDeals_PgObj.click_Firstproduct();

        //5-Click on 2nd item in this product
        TodayDeals_PgObj.Second_item_Click();
        //6-add qty=2
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
        String productTitle1= d.findElement(By.id("productTitle")).getText().toLowerCase();
        Double Totalprice1= Double.parseDouble(d.findElement(By.cssSelector("span[class=\"a-price-whole\"]")).getText().toLowerCase().replace("egp","").replace(" ","").replace(",",""));

        TodayDeals_PgObj.add_QTY();
//        7- prress add add-to-cart-button
        TodayDeals_PgObj.prress_add_cart_button();
        //9-go to Chart
        Cart_Page cartPage_Obj= TodayDeals_PgObj.GotoCarte_page();

        //10- assert for
//        SoftAssert ass=new SoftAssert();
        //1>assert qty
        Assert.assertTrue(cartPage_Obj.Assertion_Qty());
        //2> name assertion
        System.out.println(productTitle1);
        Assert.assertTrue(cartPage_Obj.Assertion_product_name(productTitle1));
        //3>price assertion
        String price2=d.findElement(By.cssSelector("span[class=\"a-offscreen\"]")).getText().toLowerCase().replace("egp","").replace(" ","").replace(",","");

        System.out.println(TodayDeals_PgObj.getPrice1());System.out.println(price2);
        Assert.assertEquals( Double.parseDouble(price2),Totalprice1);

        //4>assert total price
        Assert.assertTrue(cartPage_Obj.Assertion_TotalPrice(Totalprice1));
        System.out.println(Totalprice1);System.out.println(cartPage_Obj.getPrice2());
//        ass.assertAll();
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
            Home_PgObj.Hover_Hello();
            waitf().until(ExpectedConditions.visibilityOfElementLocated(LI.get(i)));
            d.findElement(LI.get(i)).click();
            if (i == 0 ||i==1) {
                ass.assertTrue(Home_PgObj.Assertion_WE_in_SignIN_page());
                d.navigate().back();}
            else if (i == 2) {
                ass.assertTrue(Home_PgObj.Assert_seeLists_intro_screen());
                d.navigate().back();
            }

        }

    }

}
