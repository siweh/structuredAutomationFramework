package com.qa.demo.shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart {
    private WebDriver driver;
        LoginPage loginPage = new LoginPage(driver);
    By cartProduct = By.cssSelector("td[class='product-name'] a");

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartProduct(String productName) {
        List<WebElement> cartProductList = driver.findElements(cartProduct);
        cartProductList.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return cartProductList;
    }

    public int updateQuantityOfProduct() {
        int updatedQuantity = 0;
            //Update by adding more quantity of that product because the update button is not working
            // Locate the quantity input field
            WebElement quantityInput = driver.findElement(By.id("noo-quantity-6259"));
            // Get the initial quantity value
            int initialQuantity = Integer.parseInt(quantityInput.getAttribute("value"));
            // Locate the quantity increase button and click on it
            WebElement increaseButton = driver.findElement(By.id("qty-increase"));
            increaseButton.click();
            // Get the updated quantity value
            updatedQuantity = Integer.parseInt(quantityInput.getAttribute("value"));
        return updatedQuantity;
    }

    public boolean deleteProductOnCart(){
//            driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();
            driver.findElement(By.cssSelector(".icon_close_alt2")).click();
            return true;
        }
    public boolean proceedToCheckout(){
        driver.findElement(By.linkText("\n" +
                "\tProceed to checkout")).click();
        return true;
    }

}
