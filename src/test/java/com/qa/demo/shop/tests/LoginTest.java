package com.qa.demo.shop.tests;

import com.qa.demo.shop.pages.Homepage;
import com.qa.demo.shop.pages.LoginPage;
import com.qa.demo.shop.utils.ExcelUtils;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Homepage homepage;
    private ExcelUtils excelUtils;

    @BeforeClass
    public void setupDriver() {
        try {
            driver = WebDriverUtil.getWebDriver();
            WebDriverUtil.removeDismissBitton();
            loginPage.myAccounBtn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loginPage = new LoginPage(driver);
    }

    @BeforeTest
    public void setupTestData(){
        excelUtils = new ExcelUtils();
        try {
            excelUtils.setExcelFileSheet("loginData");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

//    @BeforeMethod
//    public void methodLevelSetup() {
//        homepage = new Homepage(driver);
//        loginPage.myAccounBtn();
//    }

    @Test(priority = 0, description = "Valid Login Scenario with correct username and password.")
    public void validUserLogin(){
        loginPage.enterUsername(excelUtils.getCellData(1, 1));
        loginPage.enterPassword(excelUtils.getCellData(1, 2));
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check if the login is successful
        boolean isLoggedIn = driver.findElement(By.cssSelector(".woocommerce-MyAccount-content")).isDisplayed();
        Assert.assertTrue(isLoggedIn, "Login should be successful");
    }

    @Test(priority = 1, description = "Invalid Login Scenario with correct username and incorrect password.")
    public void invalidUserLogin(){
        String username = excelUtils.getCellData(2, 1);
        String password = excelUtils.getCellData(2, 2);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check if the login is successful
        boolean isLoggedIn = driver.findElement(By.cssSelector(".woocommerce-notices-wrapper")).isDisplayed();
        Assert.assertTrue(isLoggedIn, "Login is unsuccessful");
    }

//    @Test(priority = 1, description = "")
//    public void lostPasswordClickedTest(){
//        homepage.clickOnDismissBtn();
//         loginPage.myAccounBtn();
//        loginPage.lostPasswordLink();
//        try {
//            Thread.sleep(2000); // Wait for 2 seconds
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        boolean isPasswordLost = driver.findElement(By.cssSelector(".woocommerce-ResetPassword.lost_reset_password")).isDisplayed();
//        Assert.assertTrue(isPasswordLost, "Lost password link is not clicked and lostPassword page not open");
//    }

    @Test(priority = 3, description = "Invalid Login Scenario with empty username and password.")
    public void loginWithEmptyFieldsTest(){
        String username = excelUtils.getCellData(4, 1);
        String password = excelUtils.getCellData(4, 2);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedText = "Error: Username is required.";
        // Check if the login is successful
        String actualText = driver.findElement(By.cssSelector(".woocommerce-notices-wrapper")).getText();
        Assert.assertEquals(actualText, expectedText,"Login is unsuccessful");
    }

    @Test(priority = 2, description = "Invalid Login Scenario with incorrect username and correct password.")
    public void loginWithIncorrectUsernameTest(){
        String username = excelUtils.getCellData(3, 1);
        String password = excelUtils.getCellData(3, 2);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedText = "Error: Username is required.";
        // Check if the login is successful
        String actualText = driver.findElement(By.cssSelector(".woocommerce-notices-wrapper")).getText();
        Assert.assertEquals(actualText, expectedText,"Login is unsuccessful");
    }

    @Test(priority = 4, description = "Invalid Login Scenario with username and empty password.")
    public void loginWithUsernameOnlyTest(){
        String username = excelUtils.getCellData(5, 1);
        String password = excelUtils.getCellData(5, 2);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedText = "Error: The password field is empty.";
        // Check if the login is successful
        String actualText = driver.findElement(By.cssSelector(".woocommerce-notices-wrapper")).getText();
        Assert.assertEquals(actualText, expectedText,"Login is unsuccessful");
    }

    @Test(priority = 5, description = "Invalid Login Scenario with password and empty username.")
    public void loginWithPasswordOnlyTest(){
        String username = excelUtils.getCellData(6, 1);
        String password = excelUtils.getCellData(6, 2);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Wait for the login process to complete (you can adjust the wait time if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedText = "Error: Username is required.";
        // Check if the login is successful
        String actualText = driver.findElement(By.cssSelector(".woocommerce-notices-wrapper")).getText();
        Assert.assertEquals(actualText, expectedText,"Login is unsuccessful");
    }


    @Test(priority = 6, description = "Lost password message test")
    public void lostPasswordPageTextTest(){
        loginPage.lostPasswordLink();
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actualtext = "Lost your password? Please enter your username or email address. You will receive a link to create a new password via email.";
        String expectedText = driver.findElement(By.cssSelector(".woocommerce-ResetPassword.lost_reset_password p:first-child")).getText();
        Assert.assertEquals(actualtext, expectedText,"The lost password actual text is incorrect");
    }

    @Test(priority = 7, description = "Reset password field")
    public void resetPassword(){
        String username = "linda";
        Assert.assertTrue(loginPage.resetPassword(username), "Password reset successful");
    }

    @Test(priority = 8, description = "lost password scenario test")
    public void lostPasswordTest(){
        loginPage.lostPasswordLink();
        Assert.assertTrue(loginPage.lostPasswordLink(), "Lost password link is not clicked");
    }

    @Test(priority = 1, description = "Remember me button test")
    public void rememberMeButtonTest(){
        Assert.assertTrue(loginPage.rememberButton(), "Remeberme link is not clicked");
    }

    @AfterClass
    public void cleanup() {
        WebDriverUtil.quitWebDriver(driver);
    }
}
