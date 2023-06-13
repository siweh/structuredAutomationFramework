package com.qa.demo.shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.name("login");
    private  By rememberBtn = By.xpath("//input[@id='rememberme']");
    private By lostPasswordLink = By.linkText("Lost your password?");

    private By myAccountBtn = By.linkText("My Account");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean myAccounBtn(){
        driver.findElement(myAccountBtn).click();
        return true;
    }
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        driver.get("https://shop.demoqa.com/shop/");
    }

    public Boolean rememberButton(){
        driver.findElement(rememberBtn).click();
        return true;
    }

    public Boolean lostPasswordLink(){
        driver.findElement(lostPasswordLink).click();
        return true;
    }

    public boolean resetPassword(String username){
        lostPasswordLink();
            driver.findElement(By.id("user_login")).sendKeys(username);
            driver.findElement(By.cssSelector(".woocommerce-Button.button.wp-element-button")).click();
        return true;
    }

}
