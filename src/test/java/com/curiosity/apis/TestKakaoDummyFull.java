package com.curiosity.apis;


import org.testng.annotations.*;

public class TestKakaoDummyFull {

    static KakaoDummy kakaoDummyWebsite;

    @BeforeMethod
    public static void initiate() {
        kakaoDummyWebsite = new KakaoDummy();
    }

    @Test(priority = 1)
    public void testSignup() {
       // kakaoDummyWebsite.loadUrl(KakaoData.HomeUrl);
       // System.out.println("Url after loading Home Page: " + kakaoDummyWebsite.readCurrentUrl());
       // System.out.println("Title of the Home Page: " + kakaoDummyWebsite.readTitle());
        String url = kakaoDummyWebsite.signup(KakaoData.channelUrl, KakaoData.useridFieldid,
                KakaoData.passwordFieldid, KakaoData.submitButtonid, KakaoData.userid,
                KakaoData.password);
        System.out.println("Url after login attempt: " + kakaoDummyWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + kakaoDummyWebsite.readTitle());

    }

    @Test(priority = 2)
    public void testLogin() {
        // kakaoDummyWebsite.loadUrl(KakaoData.HomeUrl);
        // System.out.println("Url after loading Home Page: " + kakaoDummyWebsite.readCurrentUrl());
        // System.out.println("Title of the Home Page: " + kakaoDummyWebsite.readTitle());
        String url = kakaoDummyWebsite.login(KakaoData.channelUrl, KakaoData.useridFieldid,
                KakaoData.passwordFieldid, KakaoData.submitButtonid, KakaoData.userid,
                KakaoData.password);
        System.out.println("Url after login attempt: " + kakaoDummyWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + kakaoDummyWebsite.readTitle());

    }
        @AfterMethod
    public static void cleanup(){
        kakaoDummyWebsite.cleanupSession();
    }
}
