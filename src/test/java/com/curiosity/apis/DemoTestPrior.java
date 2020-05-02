package com.curiosity.apis;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.plaf.IconUIResource;

public class DemoTestPrior {
    static Website demoSite;


    @BeforeTest
    public  static void initiate(){
        demoSite = new Website();
    }

    @Test
    public static void testLaunch(){
        demoSite.loadUrl(CuriosityData.HomeUrl);
        demoSite.navigateToLink(GlobalData.TYPE_PARTIALLINKTEXT,"BLOG");
        demoSite.waitFor(3000);
        demoSite.navigateToLink(GlobalData.TYPE_PARTIALLINKTEXT,"LOG IN");
        demoSite.waitFor(3000);
        demoSite.scrolldown();
        demoSite.setFieldValue(GlobalData.TYPE_ID,CuriosityData.useridFieldid,CuriosityData.userid);
        demoSite.setFieldValue(GlobalData.TYPE_ID,CuriosityData.passwordFieldid,CuriosityData.password);

        demoSite.waitFor(3000);
        demoSite.buttonClick(GlobalData.TYPE_ID, CuriosityData.submitButtonid);
        demoSite.waitFor(5000);

    }

    @AfterTest
    public static  void destroy(){
        demoSite.cleanupSession();
    }
}
