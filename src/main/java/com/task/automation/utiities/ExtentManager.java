package com.task.automation.utiities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static final ExtentReports extentReport = new ExtentReports();
    private static synchronized ExtentReports createInstance(){
        //File Name
        String fileName = new StringBuilder().append("AutomationReport_")
                .append(new SimpleDateFormat("dd_MM_yyy_HH_mm_ss")
                        .format(new Date())).append(".html").toString();

        //Report Directory path
        String reportDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportDirectory).mkdirs();
        String path = reportDirectory+"/"+fileName;

        //Layout of Report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Run");
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setReportName(fileName);

        //Initialise ExtentReport
        extentReport.setSystemInfo("Organization","Hellooo");
        extentReport.setSystemInfo("Automation Framework","Selenium WebDriver");
        extentReport.attachReporter(sparkReporter);

        return extentReport;
    }

    public static ExtentReports getInstance(){
        return createInstance();
    }
}
