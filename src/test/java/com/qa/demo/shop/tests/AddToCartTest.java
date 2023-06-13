package com.qa.demo.shop.tests;

import com.qa.demo.shop.pages.AddToCart;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest {
    private WebDriver driver;
    private AddToCart addToCart;

    @BeforeClass
    public void setupDriver() {
        try {
            driver = WebDriverUtil.getWebDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addToCart = new AddToCart(driver);
    }

    @Test
    public void addToCartListTest(){
        int expectedList = 8;
        int actualProductlist = addToCart.getProductlist().size();
        Assert.assertEquals(actualProductlist, expectedList,"Product list should not be empty");
    }

    @Test
    public void getProductbByNameTest(){
        String productName = "Tokyo Talkies";
        String actualProductName = "Tokyo Talkies";
        String actualName = addToCart.getProductByName(actualProductName).getText().toLowerCase();
        Assert.assertEquals(actualName, productName, "Product name don't match");
    }

    @Test
    public void addProductToCartTest(){
        String productName = "Tokyo Talkies";
        Assert.assertTrue(addToCart.addProductToCart(productName), "Product is not added to cart");
    }

    @Test
    public void addProductOptionsTest(){
        String size ="M";
        String color = "White";
        Assert.assertTrue(addToCart.addProductOptions(color, size), "Product options are not added and product is not added to cart");
    }

    @Test
    public void notAddingProductOptionsTest(){
        String size =" ";
        String color = " ";
        Assert.assertTrue(addToCart.addProductOptions(color, size), "Product options are not added and product is not added to cart");
    }

    @AfterClass
    public void cleanup() {
        WebDriverUtil.quitWebDriver(driver);
    }
}
