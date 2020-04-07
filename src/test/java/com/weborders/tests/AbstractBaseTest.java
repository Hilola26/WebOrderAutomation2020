package com.weborders.tests;

import com.ConfigurationReader;
import com.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.weborders.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class AbstractBaseTest {
protected WebDriver driver;

protected static ExtentReports extentReports;
protected static ExtentHtmlReporter extentHtmlReporter;
protected static ExtentTest extentTest;




@BeforeTest
public void beforeTest(){
extentReports=new ExtentReports();
    String reportPath=System.getProperty("user.dir")+"/test-output/report.html";
    extentHtmlReporter=new ExtentHtmlReporter(reportPath);
    extentReports.attachReporter(extentHtmlReporter);
extentHtmlReporter.config().setReportName("WebOrders Automation");


}

@AfterTest
public void afterTest(){

}

@BeforeMethod
public void setup(){
  driver=Driver.getDriver();
    driver.get(ConfigurationReader.getProperty("url"));
driver.manage().window().maximize();
}
@AfterMethod
public void teardown(ITestResult testresult){
if(testresult.getStatus()==ITestResult.FAILURE) {
    String screenshotLocation = BrowserUtils.getScreenShot(testresult.getName());
    try {
        extentTest.fail(testresult.getName());
        extentTest.addScreenCaptureFromPath(screenshotLocation);
        extentTest.fail(testresult.getThrowable());
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to attach screenshot");
    }
}else if(testresult.getStatus()==ITestResult.SUCCESS){
   extentTest.pass(testresult.getName()) ;
}else if(testresult.getStatus()==ITestResult.SKIP) {
    extentTest.skip(testresult.getName());
}
    BrowserUtils.wait(3);
    Driver.closeDriver();






}}
