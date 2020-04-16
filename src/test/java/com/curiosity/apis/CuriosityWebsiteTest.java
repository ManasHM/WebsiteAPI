package com.curiosity.apis;

import com.curiosity.apis.CuriosityData;
import com.curiosity.apis.Website;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CuriosityWebsiteTest {

    static Website curiosityWebsite;

    @BeforeClass
    public static void initiate() {
        curiosityWebsite = new Website();
    }

    @Test
    public void testWebsite() {
        curiosityWebsite.loadUrl(CuriosityData.HomeUrl);
        System.out.println("Url after loading Home Page: " + curiosityWebsite.readCurrentUrl());
        System.out.println("Title of the Home Page: " + curiosityWebsite.readTitle());
        String url = curiosityWebsite.login(CuriosityData.loginUrl, CuriosityData.useridFieldid,
                CuriosityData.passwordFieldid, CuriosityData.submitButtonid, CuriosityData.userid,
                CuriosityData.password);
        System.out.println("Url after login attempt: " + curiosityWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + curiosityWebsite.readTitle());
    }

    @Test
    public void testWebsite2() {
        curiosityWebsite.loadUrl(CuriosityData.HomeUrl);
        System.out.println("Url after loading Home Page: " + curiosityWebsite.readCurrentUrl());
        System.out.println("Title of the Home Page: " + curiosityWebsite.readTitle());
    }
    @AfterClass
    public static void cleanup(){
        curiosityWebsite.cleanupSession();
    }
}
