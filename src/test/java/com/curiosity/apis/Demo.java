package com.curiosity.apis;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo {
    static Website demoSite;

    @BeforeTest
    public static void initialise() {
        demoSite = new Website();
    }

    @Test
    public static void testLogin() {
        demoSite.loadUrl(CuriosityData.HomeUrl);
        demoSite.navigateToLink(GlobalData.TYPE_LINKTEXT, "BLOG");
        demoSite.waitFor(3000);
        demoSite.navigateToLink(GlobalData.TYPE_LINKTEXT, "LOG IN");
        demoSite.waitFor(3000);
        demoSite.scrolldown();
        demoSite.setFieldValue(GlobalData.TYPE_ID, CuriosityData.useridFieldid, CuriosityData.userid);
        demoSite.setFieldValue(GlobalData.TYPE_ID, CuriosityData.passwordFieldid, CuriosityData.password);
        demoSite.waitFor(3000);
        demoSite.buttonClick(GlobalData.TYPE_ID, CuriosityData.submitButtonid);
        demoSite.waitFor(3000);


    }

    @AfterTest
    public static void destroy() {
        demoSite.cleanupSession();
    }
}
