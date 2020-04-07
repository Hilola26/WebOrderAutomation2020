package com.weborders;

import com.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtils {
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static String getScreenShot(String name){
        name=(new Date().toString()+"_"+name).replace(":","_");
        String path=System.getProperty("user.dir")+"/test-output/screenshots/"+name+".png";
        System.out.println("screenshot is here "+path);
        TakesScreenshot takesScreenshot=(TakesScreenshot)Driver.getDriver();
        File source=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination=new File(path);
        try {
            FileUtils.copyFile(source, destination);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return path;


    }

    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getText(List<WebElement>elements){
        List<String>textValues=new ArrayList<>();
        for(int x=0;x<elements.size();x++) {
            if(!elements.get(x).getText().isEmpty())
                textValues.add(elements.get(x).getText());
        }
        return textValues;
    }}























