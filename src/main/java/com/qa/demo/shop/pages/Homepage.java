package com.qa.demo.shop.pages;

import org.openqa.selenium.*;

public class Homepage {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    private By searchInput = By.cssSelector(".noo-search");
    private By productsBy = By.cssSelector(".noo-product-item");

    public Homepage(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public boolean clickMyAccountButton(){
        driver.findElement(By.linkText("My Account")).click();
        return true;
    }

    public void searchInputField(String search){
        WebElement searchInputfields = driver.findElement(searchInput);
        searchInputfields.sendKeys(search);
        searchInputfields.sendKeys(Keys.ENTER);
    }

    public void viewBlogButton(){
        jsExecutor.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToTopButton(){
        WebElement scrollToTop = driver.findElement(By.cssSelector(".go-to-top.hidden-print.on"));
        scrollToTop.click();
        jsExecutor.executeScript("window.scrollTo(0, 0)");
    }

    public boolean clickOnAProduct(){
        WebElement item = driver.findElement(By.xpath("//a[@href='https://shop.demoqa.com/product/black-lux-graphic-t-shirt/']/../../h3"));
        item.click();
        return true;
    }

    public String getItemText(){
            String item = driver.findElement(By.xpath("//a[@href='https://shop.demoqa.com/product/black-lux-graphic-t-shirt/']/../../h3")).getText();
            return item.toLowerCase();

    }

    public boolean clickOnABlogImage(){
        WebElement item = driver.findElement(By.xpath("//a[@href='https://shop.demoqa.com/2016/05/09/the-new-marc-jacobs-gotham-saddle-bag/']"));
        item.click();
        return true;
    }

    public Boolean clickOnPhoneNumber(){
        WebElement phoneNumber = driver.findElement(By.linkText("+(099) 999 - 9999"));
        phoneNumber.click();
        return true;
    }

    public Boolean clickOnEmail(){
        WebElement phoneNumber = driver.findElement(By.cssSelector("a[href='mailto:nomail@toolsqa.com']"));
        phoneNumber.click();
        return true;
    }

    public WebElement logo(){
        WebElement logoImg = driver.findElement(By.xpath("//div[@class='navbar-logo noo-md-2']"));
        return logoImg;
    }

    public Boolean clickMyWishlink(){
        WebElement wishlist = driver.findElement(By.linkText("My Wishlist"));
        wishlist.click();
        return true;
    }
    public String getHomePageTitle() {
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        return actualTitle;
    }
}
