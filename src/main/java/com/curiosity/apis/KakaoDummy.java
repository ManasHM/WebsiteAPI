package com.curiosity.apis;

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
        explicitWait(GlobalData.TYPE_ID, useridFieldid, 10);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_XPATH, submitButtonid);
        explicitWait(GlobalData.TYPE_XPATH, KakaoData.successLogin, 50);
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
        //Click on Membership button
      //  explicitWait(GlobalData.TYPE_ID, useridFieldid, 10);
        setFieldValue(GlobalData.TYPE_ID, useridFieldid, userid);
        setFieldValue(GlobalData.TYPE_ID, passwordFieldid, password);
        buttonClick(GlobalData.TYPE_XPATH, submitButtonid);
        waitForRedirect("marvel",10);
      //  String text = findElement(GlobalData.TYPE_XPATH, KakaoData.successLogin).getTagName();
      //  printOnScreen(text + "Final Print");
        //  explicitWait(GlobalData.TYPE_XPATH, KakaoData.successLogin, 300);
        return readCurrentUrl();
    }
}


