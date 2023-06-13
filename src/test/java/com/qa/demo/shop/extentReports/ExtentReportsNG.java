package com.qa.demo.shop.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;

public class ExtentReportsNG {

    @BeforeTest
    public static ExtentReports getReportObject(){
        String reportsHtmlFile = System.getProperty("user.dir") + "\\src\\test\\java\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportsHtmlFile);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Sanelisiwe Madesi");
        return extent;
    }
}
