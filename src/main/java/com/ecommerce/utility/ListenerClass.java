package com.ecommerce.utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ecommerce.actiondriver.Action;
import com.ecommerce.base.BaseClass;

public class ListenerClass extends ExtentManager implements ITestListener {
    // Initialize Action instance for screenshot functionality
    Action action = new Action();
    
    // ThreadLocal for thread-safe ExtentTest instances
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        // Initialize ExtentReports at the start of the test suite
        try {
            setExtent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report when a test starts
        ExtentTest test = extent.createTest(result.getName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        // Log the success status when a test passes
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.get().log(Status.PASS, "Test case passed: " + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        // Log the failure status and capture a screenshot when a test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTest test = extentTest.get();
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test case failed", ExtentColor.RED));
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test case failed", ExtentColor.RED));
            
            // Capture and attach screenshot
            String imgPath = action.screenShot(BaseClass.getDriver(), result.getName());
            try {
                test.fail("Screenshot is attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        // Log the skipped status when a test is skipped
        if (result.getStatus() == ITestResult.SKIP) {
            extentTest.get().log(Status.SKIP, "Test case skipped: " + result.getName());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method can be implemented if needed for handling test failures within success percentage
    }

    public void onFinish(ITestContext context) {
        // Flush the report when the test suite finishes
        endReport();
    }
}