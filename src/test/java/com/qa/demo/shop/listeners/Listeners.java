package com.qa.demo.shop.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.demo.shop.extentReports.ExtentReportsNG;
import com.qa.demo.shop.utils.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends WebDriverUtil implements ITestListener {

    ExtentTest test;
    ExtentReports extentReports = ExtentReportsNG.getReportObject();

    @Override
    public void onTestStart(ITestResult results){
        test = extentReports.createTest(results.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String filePath;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //take a screenshot and Attach screenshot
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        // not implemented
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
