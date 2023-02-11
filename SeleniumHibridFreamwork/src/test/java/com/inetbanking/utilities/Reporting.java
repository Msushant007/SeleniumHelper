package com.inetbanking.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Reporting extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports      extent;
    public ExtentTest         extentLog;

    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-"+timeStamp+".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("/Users/sushant/Documents/SeleniumTestHelper/SeleniumHibridFreamwork/reports"+repName));
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
        System.out.println("htmlReporter :"+htmlReporter);System.out.println("htmlReporter :");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "sushant");
        htmlReporter.config().setDocumentTitle("InetBanking Test Project");
        htmlReporter.config().setReportName("Function Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult testResult) {
        extentLog = extent.createTest(testResult.getTestName());
        extentLog.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN));

    }

    public void onTestFailure(ITestResult testResult) {
        extentLog = extent.createTest(testResult.getTestName());
        extentLog.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED));
        String screenshotPath = System.getProperty("user.dir")+"/screenshots"+testResult.getName()+".png";
        File file = new File(screenshotPath);
        if (file.exists()) {
            try {
                extentLog.fail("ScreenShot is below :" + extentLog.addScreenCaptureFromPath(screenshotPath));
            }
            catch (IOException e) {

            }
        }

    }

    public void onTestSkipped(ITestResult testResult) {

        extentLog = extent.createTest(testResult.getName());
        extentLog.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.BLUE));

    }

}
