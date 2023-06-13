package com.qa.demo.shop.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {
    private WebDriver driver;
    LoginPage loginPage = new LoginPage(driver);
    By billingFirstName = By.id("billing_first_name");
    By billing_last_name = By.id("billing_last_name");
    By country = By.id("billing_country");
    By billingAddress1 = By.id("billing_address_1");
    By billingAddress2 = By.id("billing_address_2");
    By billingCity = By.id("billing_city");
    By billingStateElem = By.id("billing_state");
    By postcode = By.id("billing_postcode");
    By billing_phone = By.id("billing_phone");
    By placeOrderBtn = By.id("place_order");


    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    public String successfulCheckoutMessage(String name, String lastName, String address1, String address2, String city, String postCode, String phoneNumber){
        WebElement nameInput = driver.findElement(billingFirstName);
        nameInput.sendKeys(name);
        WebElement lastNameInput = driver.findElement(billing_last_name);
        lastNameInput.sendKeys(lastName);

        WebElement countryDropdown = driver.findElement(country);
        Select dropdownCountry = new Select(countryDropdown);
        dropdownCountry.selectByVisibleText("Albania");

        WebElement addressInput1 = driver.findElement(billingAddress1);
        addressInput1.sendKeys(address1);
        WebElement addressInput2 = driver.findElement(billingAddress2);
        addressInput2.sendKeys(address2);
        WebElement cityInput = driver.findElement(billingCity);
        cityInput.sendKeys(city);

        WebElement billingState = driver.findElement(billingStateElem);
        Select dropdownState = new Select(billingState);
        dropdownState.selectByIndex(2);

        driver.findElement(postcode).sendKeys(postCode);
        driver.findElement(billing_phone).sendKeys(phoneNumber);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='terms and conditions']"))).click();

        driver.findElement(placeOrderBtn).click();
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String successOrderMessage = driver.findElement(By.cssSelector(".woocommerce-thankyou-order-received")).getText();
        return successOrderMessage;
    }

    public boolean successfulCheckout(String name, String lastName, String address1, String address2, String city, String postCode, String phoneNumber) {
        WebElement nameInput = driver.findElement(billingFirstName);
        nameInput.clear();
        nameInput.sendKeys(name);
        WebElement lastNameInput = driver.findElement(billing_last_name);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        WebElement countryDropdown = driver.findElement(country);
        Select dropdownCountry = new Select(countryDropdown);
        dropdownCountry.selectByVisibleText("Albania");

        WebElement addressInput1 = driver.findElement(billingAddress1);
        addressInput1.clear();
        addressInput1.sendKeys(address1);
        WebElement addressInput2 = driver.findElement(billingAddress2);
        addressInput2.clear();
        addressInput2.sendKeys(address2);
        WebElement cityInput = driver.findElement(billingCity);
        cityInput.clear();
        cityInput.sendKeys(city);

        WebElement billingState = driver.findElement(billingStateElem);
        billingState.clear();
        Select dropdownState = new Select(billingState);
        dropdownState.selectByIndex(2);

        WebElement billingPostCode = driver.findElement(postcode);
        billingPostCode.clear();
        billingPostCode.sendKeys(postCode);
        WebElement billingPhoneNumber = driver.findElement(billing_phone);
        billingPhoneNumber.clear();
        billingPhoneNumber.sendKeys(phoneNumber);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='terms and conditions']"))).click();

        driver.findElement(placeOrderBtn).click();
        return true;
    }

}
