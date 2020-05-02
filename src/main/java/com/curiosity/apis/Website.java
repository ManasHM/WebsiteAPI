package com.curiosity.apis;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
            System.setProperty("webdriver.chrome.driver", GlobalData.getWDLocation());
           // DesiredCapabilities capabilities = DesiredCapabilities.chrome();
         //   capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
            driver = new ChromeDriver();
            
        } else if ("Firefox".equals(browserName)) {
            System.setProperty("webdriver.gecko.driver", GlobalData.getWDLocation());
            driver = new FirefoxDriver();
        }
        if (driver != null) {
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        }
        throw new IllegalStateException("Unexpected value: " + browserName);
    }

    public void switchTab(boolean closePrevTab) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        if (closePrevTab) {
            driver.switchTo().window(tabs2.get(0));
            driver.close();
        }
        driver.switchTo().window(tabs2.get(1));
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

    public static void printOnScreen(String text) {
        System.out.println(text);
    }

    public WebElement findElement(String type, String attribute) throws IllegalStateException {
        // Go to home Page first
        // driver.get(GlobalData.getHomeUrl());
        WebElement link;
        switch (type) {
            case GlobalData.TYPE_ID: {
                link = driver.findElement(By.id(attribute));
                break;
            }
            case GlobalData.TYPE_NAME: {
                link = driver.findElement(By.name(attribute));
                break;
            }
            case GlobalData.TYPE_CLASS: {
                link = driver.findElement(By.className(attribute));
                break;
            }
            case GlobalData.TYPE_LINKTEXT: {
                link = driver.findElement(By.linkText(attribute));
                break;
            }
            case GlobalData.TYPE_PARTIALLINKTEXT: {
                link = driver.findElement(By.partialLinkText(attribute));
                break;
            }
            case GlobalData.TYPE_XPATH: {
                link = driver.findElement(By.xpath(attribute));
                break;
            }
            default:
                throw new IllegalStateException("Unexpected Id type: " + type);
        }
        if (link == null) {
            throw new IllegalStateException("Element not found with id: " + attribute);
        }
        return link;
    }

    public boolean navigateToLink(String type, String attribute) {
        try {
            WebElement link = findElement(type, attribute);
            link.click();
        } catch (IllegalStateException e) {
            WebElement link = findElement(type, attribute);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", link);
        }
        return true;
    }

    public boolean buttonClick(String type, String attribute) {

        try {
            WebElement button = findElement(type, attribute);
            button.click();
        } catch (Exception e) {
            WebElement button = findElement(type, attribute);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", button);
        }

        return true;
    }

    public boolean setFieldValue(String type, String attribute, String value) {
        WebElement field = findElement(type, attribute);
        field.sendKeys(value);
        return true;
    }

    public void waitFor(long millis){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void explicitWait(String type, String attribute, String condition, int seconds) {
        WebDriverWait myWait = new WebDriverWait(driver, seconds);
        if (condition == GlobalData.VISIBLE) {
            myWait.until(ExpectedConditions.visibilityOf(findElement(type, attribute)));
        } else if (condition == GlobalData.CLICKABLE) {
            myWait.until(ExpectedConditions.elementToBeClickable(findElement(type, attribute)));
        }
    }

    public void waitForRedirect(String url, int seconds) {
        WebDriverWait myWait = new WebDriverWait(driver, seconds);
        myWait.until(ExpectedConditions.urlContains(url));
    }

    public void navigate(String navType) {
        switch (navType) {
            case GlobalData.NAVTYPE_BACK: {
                driver.navigate().back();
                break;
            }
            case GlobalData.NAVTYPE_FORWARD: {
                driver.navigate().forward();
                break;
            }
            case GlobalData.NAVTYPE_REFRESH: {
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
    ) {
        loadUrl(url);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_ID, submitButtonid);
        return readCurrentUrl();
    }

    public String signup(String url,
                         String useridFieldid,
                         String passwordFieldid,
                         String submitButtonid,
                         String userid,
                         String password
    ) {
        loadUrl(url);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_ID, submitButtonid);
        return readCurrentUrl();
    }

    public void  scrolldown(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        // This  will scroll down the page by  1000 pixel vertical
        executor.executeScript("window.scrollBy(0,1000)");
    }
}