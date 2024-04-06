package com.task.automation.utiities;

public class Constants {

    public static final String USER_DIR = System.getProperty("user.dir");

    public static final String REPORTS_DIRECTORY = USER_DIR + "//target/surefire-reports/extent";

    public static final String SCREENSHOT_DIRECTORY = USER_DIR + "//target/surefire-reports/screenshots";

    //My SQ Database Detais
    public static final String USER_NAME = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/amazonDB";

    public static final String REMOTE_URL = "http://localhost:4444";

    public static final String REMOTE_CHROME = "chrome";

    public static final String REMOTE_FIREFOX = "firefox";

}
