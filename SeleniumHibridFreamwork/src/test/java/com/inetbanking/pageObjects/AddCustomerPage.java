package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver) {
        this.ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(how = How.NAME, using = "name")
    @CacheLookup
    WebElement userName;

    @FindBy(name = "userEmail")
    @CacheLookup
    WebElement userEmail;

    @FindBy(how = How.XPATH, using = "//*[@class=\"DateOfBirth\"]")
    @CacheLookup
    WebElement dob;

    public void customerDOB(String dd, String mm, String yy) {
        dob.sendKeys(dd);
        dob.sendKeys(mm);
        dob.sendKeys(yy);

    }

}
