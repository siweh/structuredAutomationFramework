package com.qa.demo.shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddToCart {
    private WebDriver driver;
    By productsBy = By.cssSelector(".noo-product-item");
    By addToCartBtn = By.xpath("//button[normalize-space()='Add to cart']");
    By productColor = By.id("color");
    By productSize = By.id("size");

//    LoginPage loginPage = new LoginPage(driver);

    public AddToCart(WebDriver driver) {
        this.driver = driver;
    }
    public List<WebElement> getProductlist(){
        List<WebElement> products = driver.findElements(productsBy);
//        System.out.println("product size: " + products.size());
        return products;
    }


    public WebElement getProductByName(String productName) {
        WebElement item = getProductlist().stream().filter(product -> product.findElement(By.cssSelector("h3 a")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
//        System.out.println(item.getText());
        return item;
    }

    public boolean addProductToCart(String productName){
        WebElement product = getProductByName(productName);
        product.click();
        return true;
    }

    public boolean addProductOptions(String color, String size){
        String productName = "Black Cross Back Maxi Dress";
        addProductToCart(productName);
        //Select a dropdown with select tag
        WebElement 	colorDropdown = driver.findElement(productColor);
        WebElement 	sizeDropdown = driver.findElement(productSize);

        Select dropdownColor = new Select(colorDropdown);
        Select dropdownSize = new Select(sizeDropdown);
        dropdownColor.selectByVisibleText(color);
        dropdownSize.selectByVisibleText(size);

        driver.findElement(addToCartBtn).click();
        return true;
    }

    public void viewCartButton(){
        driver.findElement(By.linkText("View cart")).click();
    }
}
