package com;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static ThreadLocal<WebDriver >driverPool=new ThreadLocal<>();
    private Driver() {
    }

        public synchronized static WebDriver getDriver() {
            if(driverPool.get()==null){
                String browser = ConfigurationReader.getProperty("browser");
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions=new ChromeOptions();
                        driverPool.set( new ChromeDriver(chromeOptions));
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set( new FirefoxDriver());
                        break;
                    default:
                        throw new RuntimeException("Wrong browser name!");
                }
            }
            return driverPool.get();
        }

        public static void closeDriver() {
            if (driverPool != null) {
                driverPool.get().quit();
                driverPool.remove() ;
            }
        }


    }






















