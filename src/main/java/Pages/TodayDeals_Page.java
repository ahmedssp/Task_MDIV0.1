package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
public class TodayDeals_Page {
    private WebDriver d;
    public TodayDeals_Page(WebDriver d){this.d=d;}
    private String  productTitle1=null;
    private String price1="";
    private Boolean x=true;
    public  String getProductTitle1() {
        return productTitle1;
    }
    public void setProductTitle1(String productTitle) {
        this.productTitle1 = productTitle;
    }
    public String getPrice1() {
        return price1;
    }
    public void setPrice1(String price1) {
        this.price1 = price1;
    }
    public  Boolean getX() {
        return x;
    }
    public void setX(Boolean x) {
        this.x = x;
    }
    public double getTotalprice1() {
        return totalprice1;
    }
    public void setTotalprice1(double totalprice1) {
        this.totalprice1 = totalprice1;
    }
    private   double totalprice1;
    private By second_categoryes_filed = By.xpath("//span[text()=\"Home & Kitchen\" and contains(@class,\"GridPresets-module\")]");
    private By Firstproduct_filed = By.xpath("(//div[@class=\"DealCard-module__contentWithPadding_1mEcEYf1DvbvZJ9zcQCxtw\"]/a)[2]");
    private By Second_item_filed = By.xpath("(//ul[@class=\"a-unordered-list a-nostyle a-horizontal a-spacing-none\"]/li)[2]");
    private By productTitle1_Field=By.id("productTitle");
    private By Bouton_announcer_field=By.id("warranty_no_button-announce");
    public void click_second_categoryes(){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(second_categoryes_filed));
        d.findElement(second_categoryes_filed).click();
    }
    public  void click_Firstproduct(){
        waitf().until(ExpectedConditions.visibilityOfElementLocated(Firstproduct_filed));
        d.findElement(Firstproduct_filed).click();
    }
    public void Second_item_Click(){
        try {
            waitf().until(ExpectedConditions.visibilityOfElementLocated(Second_item_filed));
            d.findElement(Second_item_filed).click();
        }catch (Exception e ){System.out.println("this product have one item ");};
    }
    public void add_QTY(){
        try {
        setProductTitle1(d.findElement(productTitle1_Field).getText().toLowerCase());
        setPrice1(d.findElement(By.cssSelector("span[class=\"a-price-whole\"]")).getText().toLowerCase());
        waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity")));
        Select select=new Select(d.findElement(By.id("quantity")));
        select.selectByValue("2");
        setX(true);System.out.println("this Product Have more Than One items ");
      }catch (Exception e ){System.out.println("this product have one Qty ");setX(false);}
    }
   public void prress_add_cart_button() throws InterruptedException {
        Thread.sleep(1000);
       waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
       d.findElement(By.id("add-to-cart-button")).click();

       try {
           waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("attachSiNoCoverage")));
           d.findElement(By.id("attachSiNoCoverage")).click();

       }catch (Exception e ){System.out.println("No offers");};

   }
   public Cart_Page GotoCarte_page() throws InterruptedException {

       waitf().until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count-container")));
       d.findElement(By.id("nav-cart-count-container")).click();
        return new  Cart_Page(d);
   }

    public Wait waitf(){
        Wait wait = new FluentWait( d)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class);
        return wait;
    }

}
