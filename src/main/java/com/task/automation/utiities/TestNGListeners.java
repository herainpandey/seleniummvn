package com.task.automation.utiities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.task.automation.core.BaseTest;
import com.task.automation.helper.CustomHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestNGListeners extends BaseTest implements ITestListener{
        private static ExtentReports extentReports = ExtentManager.getInstance();
        private static ThreadLocal<ExtentTest> extentThread = new ThreadLocal<>();  // Report will be messed up incase classes run in parallel
        private static final Logger log = LogManager.getLogger(TestNGListeners.class.getName());

        public void onStart(ITestContext context) {
            log.info("onStart -> Test Name: " +  context.getName());
            ITestNGMethod methods[] = context.getAllTestMethods();
            log.info("These methods will be executed in this <test> tag");
            for(ITestNGMethod method: methods){
                log.info(method.getMethodName());
            }
        }

        public void onFinish(ITestContext context) {
            log.info("onFinish -> Test Name: " +  context.getName());
            extentReports.flush();
        }


        public void onTestStart(ITestResult result) {
            log.info("onTestStart -> Test Name: " +  result.getName());
            ExtentTest extentTest = extentReports.createTest(result.getInstanceName() + "::" + result.getMethod().getMethodName());
            extentThread.set(extentTest);
        }

        public void onTestSuccess(ITestResult result) {
            log.info("onTestSuccess -> Test Method Name: " +  result.getName());
            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "Test Method" + methodName + "Successful" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentThread.get().log(Status.PASS,m);
        }

        public void onTestFailure(ITestResult result) {
            log.info("onTestFailure -> Test Method Name: " +  result.getName());
            String methodName = result.getMethod().getMethodName();
            String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            extentThread.get().fail("<details>" + "<Summary>" + "<b>" + "<font color =red>" + "Exception Occurred: Click to See details: " + "</font>" + "</b>" + "</summary>" +
                    exceptionMessage.replaceAll(",","<br>") + "</details>" + " \n");
            String path = SccreenShotUtils.takeScreenshot(methodName , CustomHelper.getSimpeDateFormat("DD-MM-YY HH_mm_ss"));
            try{
                extentThread.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            }catch (Exception e){
                extentThread.get().fail("Test Method failed, cannot attach screenshot");

            }

            String logText = "<b>" + "Test Method" + methodName + "Failed" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            extentThread.get().log(Status.FAIL,m);
        }

        public void onTestSkipped(ITestResult result) {
            log.info("onTestSkipped -> Test Method Name: " +  result.getName());
            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "Test Method" + methodName + "Skipped" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            extentThread.get().log(Status.SKIP,m);
        }

        public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
            //Ignore this
        }

        public void onTestFailedWithTimeout(ITestResult result) {
            //Ignore this
            this.onTestFailure(result);
        }


    }
