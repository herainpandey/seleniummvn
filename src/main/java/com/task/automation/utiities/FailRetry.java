package com.task.automation.utiities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer {

    private int maxretyrcount = 1;
    private int retrycount =0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retrycount<maxretyrcount){
            retrycount++;
            return true;
        }
        return false;
    }
}
