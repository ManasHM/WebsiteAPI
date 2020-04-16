package com.curiosity.apis;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IkeaWebsiteTest {

    static Website ikeaWebsite;

@BeforeTest
    public static void initiate() {
        ikeaWebsite = new Website();
    }

@Test
    public void testWebsite() {
        ikeaWebsite.loadUrl("https://www.ikea.com/se/sv/");
        ikeaWebsite.driver.manage().window().maximize();
        System.out.println("Url after loading Home Page: " + ikeaWebsite.readCurrentUrl());
        System.out.println("Title of the Home Page: " + ikeaWebsite.readTitle());
//        String url = ikeaWebsite.login(CuriosityData.loginUrl, CuriosityData.useridFieldid,
//                CuriosityData.passwordFieldid, CuriosityData.submitButtonid, CuriosityData.userid,
//                CuriosityData.password);
        ikeaWebsite.explicitWait(GlobalData.TYPE_XPATH,"/html/body/div[6]/div[3]/div/div/div[2]/div/button[2]","Visible",10);
       ikeaWebsite.buttonClick(GlobalData.TYPE_XPATH,"/html/body/div[6]/div[3]/div/div/div[2]/div/button[2]");
//    Alert alert = ikeaWebsite.driver.switchTo().alert();
//    alert.accept();
        ikeaWebsite.explicitWait(GlobalData.TYPE_LINKTEXT,"Bli medlem","Clickable",10);
        ikeaWebsite.navigateToLink(GlobalData.TYPE_LINKTEXT,"Bli medlem");
        System.out.println("Url after login attempt: " + ikeaWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + ikeaWebsite.readTitle());
    }
    @AfterClass
    public static void cleanup(){
        ikeaWebsite.cleanupSession();
    }
}
