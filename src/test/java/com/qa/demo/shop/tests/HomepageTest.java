package com.qa.demo.shop.tests;

import com.qa.demo.shop.pages.Homepage;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomepageTest {
    private WebDriver driver;
    private Homepage homepage;

    @BeforeClass
    public void setupDriver() {
        try {
            driver = WebDriverUtil.getWebDriver();
            WebDriverUtil.removeDismissBitton();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homepage = new Homepage(driver);
    }

    @Test
    public void logoTest(){
        boolean isLogoDisplayed = homepage.logo().isDisplayed();
        Assert.assertTrue(isLogoDisplayed, "Logo in not displayed on the page");
    }

    @Test(priority = 0)
    public void homepageTitleTest()  {
        String expectedTitle = "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site";
        String actualTitle = homepage.getHomePageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "The home page title is incorrect");
    }

    @Test
    public void searchInputFieldResultsPageTest() {
        homepage.searchInputField("sale");
        WebElement searchResult = driver.findElement(By.cssSelector("body"));
        Assert.assertTrue(searchResult.isDisplayed(), "Search results should be displayed");
    }

    @Test
    public void searchInputResultsTextTest(){
        homepage.searchInputField("sale");
        WebElement searchResult = driver.findElement(By.cssSelector("body"));
        String searchResultsText = searchResult.findElement(By.cssSelector("p:first-child")).getText();
        String expectedText = driver.findElement(By.cssSelector(".noo-container-shop p")).getText();
        Assert.assertEquals(searchResultsText, expectedText,"The search results text is incorrect");
    }

    @Test
    public void viewBlogButtonTest(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 500)");

        // Scroll to the top of the page
        homepage.viewBlogButton();
        // Validate that the page is scrolled to the top
        Long scrollPosition = (Long) jsExecutor.executeScript("return window.pageYOffset;");
        Assert.assertEquals(scrollPosition, 0, "Page should be scrolled to the top");
    }

    @Test
    public void clickedItemTest(){
        Assert.assertTrue(homepage.clickOnAProduct(), "The product is not clicked");
    }

    @Test
    public void getItemTextTest(){
        String expectedText = "black lux graphic t-shirt";
        Assert.assertEquals(homepage.getItemText(), expectedText, "The actual product text is incorrect");
    }

    @Test
    public void clickedBlogImageTest(){
        Assert.assertTrue(homepage.clickOnABlogImage(), "The product is not clicked");
    }


    @Test
    public void clickedPhoneNumberTest(){
        Assert.assertTrue(homepage.clickOnPhoneNumber(), "The phone number link is not clicked");
    }

    @Test
    public void clickedEmailTest(){
        Assert.assertTrue(homepage.clickOnEmail(), "The email link is not clicked");
    }

    @Test
    public void clickedMyWishlistLinkTest(){
        Assert.assertTrue(homepage.clickMyWishlink(), "The my wishlist link is not clicked");
    }

    @AfterClass
    public void cleanup() {
        WebDriverUtil.quitWebDriver(driver);
    }
}
