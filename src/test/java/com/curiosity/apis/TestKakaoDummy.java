package com.curiosity.apis;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestKakaoDummy {

    static KakaoDummy kakaoDummyWebsite;

    @BeforeClass
    public static void initiate() {
        kakaoDummyWebsite = new KakaoDummy();
    }

    @Test
    public void testWebsite() {
       // kakaoDummyWebsite.loadUrl(KakaoData.HomeUrl);
       // System.out.println("Url after loading Home Page: " + kakaoDummyWebsite.readCurrentUrl());
       // System.out.println("Title of the Home Page: " + kakaoDummyWebsite.readTitle());
        String url = kakaoDummyWebsite.login(KakaoData.channelUrl, KakaoData.useridFieldid,
                KakaoData.passwordFieldid, KakaoData.submitButtonid, KakaoData.userid,
                KakaoData.password);
        System.out.println("Url after login attempt: " + kakaoDummyWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + kakaoDummyWebsite.readTitle());

    }
        @AfterClass
    public static void cleanup(){
      //  kakaoDummyWebsite.cleanupSession();
    }
}
