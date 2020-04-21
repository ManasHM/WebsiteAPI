package com.curiosity.apis;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class KakaoDummy extends Website {
    public String loginFromChannel(String url,
                                   String useridFieldid,
                                   String passwordFieldid,
                                   String submitButtonid,
                                   String userid,
                                   String password
    ) {
        loadUrl(url); //Load the Kakao Channel
        //Click on Membership button
        navigateToLink(GlobalData.TYPE_XPATH, KakaoData.mshButtonid);
        explicitWait(GlobalData.TYPE_ID, useridFieldid, "Visible", 10);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_XPATH, submitButtonid);
        explicitWait(GlobalData.TYPE_XPATH, KakaoData.successLogin, "Visible", 50);
        return readCurrentUrl();
    }

    public String login(String url,
                        String useridFieldid,
                        String passwordFieldid,
                        String submitButtonid,
                        String userid,
                        String password
    ) {
        loadUrl(url); //Load the Kakao Channel

        //click on membership button
        buttonClick(GlobalData.TYPE_XPATH, KakaoData.mshButtonid);

        waitForRedirect("kakao", 10);

        //close the old tab and switch to new tab
//        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs2.get(0));
//        driver.close();
//        driver.switchTo().window(tabs2.get(1));
        switchTab(false);

        //  explicitWait(GlobalData.TYPE_ID, useridFieldid, 10);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_XPATH, submitButtonid);
        waitForRedirect("marvel", 10);
        //  String text = findElement(GlobalData.TYPE_XPATH, KakaoData.successLogin).getTagName();
        //  printOnScreen(text + "Final Print");
        //  explicitWait(GlobalData.TYPE_XPATH, KakaoData.successLogin, 300);
        return readCurrentUrl();
    }

    public String signup(String url,
                         String useridFieldid,
                         String passwordFieldid,
                         String submitButtonid,
                         String userid,
                         String password
    ) {
        loadUrl(url); //Load the Kakao Channel

        //click on membership button
        buttonClick(GlobalData.TYPE_XPATH, KakaoData.mshButtonid);

        waitForRedirect("kakao", 10);

        switchTab(true);


        //  explicitWait(GlobalData.TYPE_ID, useridFieldid, 10);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_XPATH, submitButtonid);
        waitForRedirect("kauth.kakao.com", 10);

//        WebElement checkAll = findElement(GlobalData.TYPE_XPATH,KakaoData.acceptall);
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click();", checkAll);
//        System.out.println(checkAll.isSelected());
        //checkAll.click();

        buttonClick(GlobalData.TYPE_XPATH, KakaoData.acceptall);

        buttonClick(GlobalData.TYPE_ID, KakaoData.continueto);

        waitForRedirect("marvel", 10);
        //  String text = findElement(GlobalData.TYPE_XPATH, KakaoData.successLogin).getTagName();
        //  printOnScreen(text + "Final Print");
        //  explicitWait(GlobalData.TYPE_XPATH, KakaoData.successLogin, 300);
        return readCurrentUrl();
    }
}


