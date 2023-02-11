package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest01 extends BaseTest {

    LoginPage lp;

    @Test
    public void validUserLoginTest(){
        lp= new LoginPage(driver);
        lp.setUserName(readConfig.getUser());
        lp.setPassword(readConfig.getPass());
        lp.clickButton();
        logger.info(" Get Current Titel: " + driver.getTitle());

        if(driver.getTitle().equals("Guru99 Bank Manager HomePagerr")){
           Assert.assertTrue(true);
           logger.info("Loing Test Passed");

        }else {
            Assert.assertTrue(false);
            logger.info("Loing Test Failed");
            captureScreenShot(driver,"validUserLoginTest");

        }
    }


}
