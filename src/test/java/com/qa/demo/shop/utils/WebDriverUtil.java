package com.qa.demo.shop.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    public static WebDriver driver;
    public static Properties property = new Properties();
    public static FileReader fileReader;
    public static final String       currentDir            = System.getProperty("user.dir");

    public static WebDriver getWebDriver() throws IOException {
        if(driver == null){
            System.out.println(currentDir);
            FileReader fileReader = new FileReader(currentDir+"\\src\\test\\java\\config\\config.properties");
            property.load(fileReader);
        }
        //Check if the browser is equal to chrome/firefox
        if(property.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(property.getProperty("testurl"));
        } else if (property.getProperty("browser").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(property.getProperty("testurl"));
        }
        //Mazimize current window
//        driver.manage().window().maximize();
        // Set implicit wait to 2 seconds
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }

    public static void removeDismissBitton(){
        driver.findElement(By.xpath("//a[normalize-space()='Dismiss']")).click();
    }
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(currentDir + "//src//test//java//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return currentDir + "//src//test//java//reports//" + testCaseName + ".png";
    }

    public static void quitWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
