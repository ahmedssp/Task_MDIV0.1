package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Cart_Page {
    private WebDriver d;
    public Cart_Page(WebDriver d){this.d=d;}
    TodayDeals_Page objt=new TodayDeals_Page(d);

    private  double totalprice2;
    private String price2;
    private String name2;
    private String getProductTitle2;

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }


    public double getTotalprice2() {
        return totalprice2;
    }

    public void setTotalprice2(double totalprice2) {
        this.totalprice2 = totalprice2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public  String getGetProductTitle2() {
        return getProductTitle2;
    }
    public  void setGetProductTitle2(String getProductTitle2) {
        this.getProductTitle2 = getProductTitle2;
    }

    private  By Price2_field=By.cssSelector("div[class=\"sc-badge-price-to-pay\"]");
    private By ProductTitle2_field=By.cssSelector("span[class=\"a-truncate-cut\"]");
    public boolean Assertion_Qty(){

        if(!objt.getX()){
           return d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("1");}
        else
        { return d.findElement(By.cssSelector("span[class=\"a-dropdown-prompt\"]")).getText().toLowerCase().contains("2");}

    }
    public boolean Assertion_product_name(String productTitle1) {

        setGetProductTitle2(d.findElement(ProductTitle2_field).getText().toLowerCase());
        return productTitle1.contains(getProductTitle2.substring(0, 10));
    }
    public boolean Assertion_TotalPrice(Double TotalPrice) {
        objt.setTotalprice1((Double.parseDouble(objt.getPrice1().replace(",",""))));
        System.out.println(getTotalprice2());
        if(!objt.getX()){ return (TotalPrice==getTotalprice2());}else {return (TotalPrice*2==totalprice2);}
    }
    public double setprice2_G(){
        setPrice2(d.findElement(By.cssSelector("p[class=\"a-spacing-mini\"]")).getText().toLowerCase().replace("egp","").replace(" ","").replace(",",""));
        return Double.parseDouble(getPrice2());
    };

    public Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        return wait;
    }

}
