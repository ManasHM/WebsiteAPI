package com.curiosity.apis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Website {
    WebDriver driver;

    public Website() {
       driver = getDriver();
    }

    private WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        String browserName = GlobalData.getBrowserName();
        if ("Chrome".equals(browserName)) {
            System.setProperty("webdriver.chrome.driver", GlobalData.getWDLocation());
            driver = new ChromeDriver();
            return driver;
        } else if ("Firefox".equals(browserName)) {
            System.setProperty("webdriver.gecko.driver", GlobalData.getWDLocation());
            driver = new FirefoxDriver();
            return driver;
        }
        throw new IllegalStateException("Unexpected value: " + browserName);
    }

    public void cleanupSession() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver loadUrl(String url) {
        driver.get(url);
        return driver;
    }

    public String readCurrentUrl() {
        getDriver();
        return driver.getCurrentUrl();
    }

    public String readTitle() {
        getDriver();
        return driver.getTitle();
    }

    public boolean checkTitle(String url, String title) {
        driver = loadUrl(url);
        String actualTitle = driver.getTitle();
        if (actualTitle.startsWith(title)) {
            return true;
        } else {
            System.out.println("The actual title does not match the expected title for this page!:"
                    + actualTitle + "---"
                    + title);
        }
        return false;
    }

    private WebElement findElement(String type, String attribute) throws IllegalStateException {
        // Go to home Page first
        // driver.get(GlobalData.getHomeUrl());
        WebElement link;
        switch (type){
            case GlobalData.TYPE_ID:{
               link = driver.findElement(By.id(attribute));
               break;
            }
            case GlobalData.TYPE_NAME:{
                link = driver.findElement(By.name(attribute));
                break;
            }
            case GlobalData.TYPE_CLASS:{
                link = driver.findElement(By.className(attribute));
                break;
            }
            case GlobalData.TYPE_LINKTEXT:{
                link = driver.findElement(By.linkText(attribute));
                break;
            }
            case GlobalData.TYPE_PARTIALLINKTEXT:{
                link = driver.findElement(By.partialLinkText(attribute));
                break;
            }
            default:
                throw new IllegalStateException("Unexpected Id type: " + type);
        }
        if (link == null){
            throw new IllegalStateException("Element not found with id: " + attribute);
        }
        return link;
    }

    public boolean navigateToLink(String type, String attribute){
        WebElement link = findElement(type, attribute);
        link.click();
        return true;
    }
    public boolean buttonClick(String type, String attribute){
        WebElement button = findElement(type, attribute);
        button.click();
        return true;
    }
    public boolean setFieldValue(String type, String attribute, String value){
        WebElement field = findElement(type, attribute);
        field.sendKeys(value);
        return true;
    }

    public void navigate(String navType){
        switch (navType){
            case GlobalData.NAVTYPE_BACK:{
                driver.navigate().back();
                break;
            }
            case GlobalData.NAVTYPE_FORWARD:{
                driver.navigate().forward();
                break;
            }
            case GlobalData.NAVTYPE_REFRESH:{
                driver.navigate().refresh();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected Navigation type: " + navType);
        }

    }

    public String login(String url,
                      String useridFieldid,
                      String passwordFieldid,
                      String submitButtonid,
                      String userid,
                      String password
                      ){
        loadUrl(url);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_ID,submitButtonid);
        return readCurrentUrl();
    }
}