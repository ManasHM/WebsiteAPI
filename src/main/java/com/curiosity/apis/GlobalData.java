package com.curiosity.apis;

public class GlobalData {
    public static final String TYPE_ID = "ID";
    public static final String TYPE_NAME = "NAME";
    public static final String TYPE_CLASS = "CLASS";
    public static final String TYPE_LINKTEXT = "LINKTEXT";
    public static final String TYPE_PARTIALLINKTEXT = "PARTIAL_LINKTEXT";
    public static final String TYPE_XPATH = "XPATH";

    public static final String NAVTYPE_BACK = "BACK";
    public static final String NAVTYPE_FORWARD = "FORWARD";
    public static final String NAVTYPE_REFRESH = "REFRESH";

    public static final String VISIBLE = "Visible";
    public static final String CLICKABLE = "Clickable";
    public static final String REDIRECT = "Redirect";

    public static String getWDLocation() {
        return "C:\\apps\\chromedriver.exe";

    }

    public static String getBrowserName() {
        return "Chrome";
    }
}
