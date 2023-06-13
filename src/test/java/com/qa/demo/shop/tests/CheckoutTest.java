package com.qa.demo.shop.tests;

import com.qa.demo.shop.pages.*;
import com.qa.demo.shop.utils.ExcelUtils;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest {
    private WebDriver driver;
    private Cart cart;
    private LoginPage loginPage;
    private AddToCart addToCart;
    private Homepage homepage;
    private Checkout checkout;
    private ExcelUtils excelUtils;

    @BeforeClass
    public void setupDriver() {
        try {
            driver = WebDriverUtil.getWebDriver();
            WebDriverUtil.removeDismissBitton();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homepage = new Homepage(driver);
        cart = new Cart(driver);
        loginPage = new LoginPage(driver);
        addToCart = new AddToCart(driver);
        checkout = new Checkout(driver);
    }

    @BeforeTest
    public void setupTestData() throws IOException {
        //Set Test Data Excel and Sheet
        excelUtils = new ExcelUtils();
        excelUtils.setExcelFileSheet("checkoutData");
    }

    @Test(priority = 0, description = "Successful checkout")
    public void successfulCheckoutTest(){
        homepage.clickMyAccountButton();
        loginPage.enterUsername(excelUtils.getCellData(1, 1));
        loginPage.enterPassword(excelUtils.getCellData(1, 2));
        loginPage.clickLoginButton();
        addToCart.addProductToCart("Black Cross Back Maxi Dress");
        addToCart.addProductOptions("Black", "Medium");
        addToCart.viewCartButton();
        cart.proceedToCheckout();
        String firstName = excelUtils.getCellData(1, 3);
        String lastname = excelUtils.getCellData(1, 4);
        String address1 = excelUtils.getCellData(1, 5);
        String address2 = excelUtils.getCellData(1, 6);
        String city = excelUtils.getCellData(1, 7);
        String postcode = excelUtils.getCellData(1, 8);
        String phoneNumber = excelUtils.getCellData(1, 9);
        Assert.assertTrue(checkout.successfulCheckout(firstName, lastname, address1, address2, city, postcode, phoneNumber), "Checkout is unsuccessful");
    }

    @Test(priority = 1, description = "Unsuccessful checkout ")
    public void UnsuccessfulCheckoutTest(){
        homepage.clickMyAccountButton();
        loginPage.enterUsername(excelUtils.getCellData(1, 1));
        loginPage.enterPassword(excelUtils.getCellData(1, 2));
        loginPage.clickLoginButton();
        String productName = "black satin one shoulder cut out midi dress";
        addToCart.addProductToCart(productName);
        addToCart.addProductOptions("Black", "Medium");
        addToCart.viewCartButton();
        cart.proceedToCheckout();

        String firstName = excelUtils.getCellData(2, 3);
        String lastName = excelUtils.getCellData(2, 4);
        String address1 = excelUtils.getCellData(2, 5);
        String address2 = excelUtils.getCellData(2, 6);
        String city = excelUtils.getCellData(2, 7);
        String postCode = excelUtils.getCellData(2, 8);
        String phoneNumber = excelUtils.getCellData(2, 9);
        Assert.assertTrue(checkout.successfulCheckout(firstName, lastName, address1, address2, city, postCode, phoneNumber), "Checkout is successfully");
    }
}
