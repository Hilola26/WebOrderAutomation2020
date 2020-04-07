package com.weborders.pages;

import com.ConfigurationReader;
import com.weborders.BrowserUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractBasePage {

@FindBy(id="ctl00_MainContent_username")
    private WebElement username;

@FindBy(id="ctl00_MainContent_password")
private WebElement password;

public void login() {
    BrowserUtils.wait(4);
    String usernameValue = ConfigurationReader.getProperty("username");
    String passwordValue = ConfigurationReader.getProperty("password");
username.sendKeys(usernameValue);
password.sendKeys(passwordValue,Keys.ENTER);
}

public void login(String usernameValue,String passwordValue){
BrowserUtils.wait(4);
    username.sendKeys(usernameValue);
password.sendKeys(passwordValue, Keys.ENTER);

}


}
