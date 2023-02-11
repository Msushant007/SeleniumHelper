package com.inetbanking.testCases;

import com.inetbanking.utilities.ReadConfig;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    ReadConfig readConfig = new ReadConfig();
    public static WebDriver driver;

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br) {
        String log4jConfPath = System.getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger = Logger.getLogger("eBanking Selenium Hybrid Framework");

        if (br.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
            driver = new ChromeDriver();

        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxDriverPath());
            driver = new FirefoxDriver();

        }
        driver.get(readConfig.getBaseUrl());
        logger.info("Get URL :" + readConfig.getBaseUrl());

    }

    public void initBrowser() {

    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }

    public void captureScreenShot(WebDriver driver, String tname) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "screenshots" + tname + ".png");
        try {
            FileUtils.copyFile(source, target);
            logger.info(" screenshot taken ");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
