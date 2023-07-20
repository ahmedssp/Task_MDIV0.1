package Testing_Package;

import org.testng.annotations.Test;

public class Login extends Base_Package.base{
    @Test
    public void Login_Valid_registered_Mail() throws InterruptedException {
        homePage_obj.EN_Homepage();
//        homePage_obj.LoginPage_CLICK();
        homePage_obj.print();


    }
    @Test
    public void Login_NON_registered_M(){

    }


}
