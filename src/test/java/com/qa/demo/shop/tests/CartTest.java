package com.qa.demo.shop.tests;

import com.qa.demo.shop.pages.AddToCart;
import com.qa.demo.shop.pages.Cart;
import com.qa.demo.shop.pages.Homepage;
import com.qa.demo.shop.pages.LoginPage;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CartTest {

    private WebDriver driver;

    private Cart cart;
    private LoginPage loginPage;
    private AddToCart addToCart;
    private Homepage homepage;

    // Invoke the function to get the list of WebElements

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
    }

    @Test
    public void getCartProductTest(){
        List<WebElement> elementList = cart.getCartProduct("Tokyo Talkies");
        Assert.assertTrue(elementList.isEmpty(), "The list of WebElements should not be empty");
    }

    @Test
    public void getCartProductDisplayTest(){
        List<WebElement> elementList = cart.getCartProduct("Tokyo Talkies");
        for (WebElement element : elementList) {
            Assert.assertTrue(element.isDisplayed(), "Element should be displayed");
        }
    }

    @Test
    public void updateQuantityOfProductTest(){
        WebElement quantityInput = driver.findElement(By.id("noo-quantity-4969"));
        int initialQuantity = Integer.parseInt(quantityInput.getAttribute("value"));
        Assert.assertEquals(cart.updateQuantityOfProduct(), initialQuantity, "Quantity size is incorrect");
    }

    @Test
    public void deleteProductOnCartTest(){
//        addToCart.getProductByName("Tokyo Talkies");
        addToCart.addProductToCart("Tokyo Talkies");
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addToCart.addProductOptions("White", "M");
        Assert.assertTrue(cart.deleteProductOnCart(), "Product not deleted");
    }

    @Test
    public void proceedToCheckoutTest(){
        addToCart.addProductToCart("Tokyo Talkies");
        addToCart.addProductOptions("White", "M");
        Assert.assertTrue(cart.proceedToCheckout(), "The proceed to checkout button not clicked");
    }

    @AfterClass
    public void cleanup() {
        WebDriverUtil.quitWebDriver(driver);
    }
}
