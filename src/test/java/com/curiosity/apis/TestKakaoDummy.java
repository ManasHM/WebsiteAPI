package com.curiosity.apis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestKakaoDummy {

    static KakaoDummy kakaoDummyWebsite;

    @BeforeClass
    public static void initiate() {
        kakaoDummyWebsite = new KakaoDummy();
    }

    @Test
    public void testWebsite() {
        kakaoDummyWebsite.loadUrl(KakaoData.HomeUrl);
        System.out.println("Url after loading Home Page: " + kakaoDummyWebsite.readCurrentUrl());
        System.out.println("Title of the Home Page: " + kakaoDummyWebsite.readTitle());
        String url = kakaoDummyWebsite.login(KakaoData.loginUrl, KakaoData.useridFieldid,
                KakaoData.passwordFieldid, KakaoData.submitButtonid, KakaoData.userid,
                KakaoData.password);
        System.out.println("Url after login attempt: " + kakaoDummyWebsite.readCurrentUrl());
        System.out.println("Title after the login attempt: " + kakaoDummyWebsite.readTitle());
    }
    @AfterClass
    public static void cleanup(){
        kakaoDummyWebsite.cleanupSession();
    }
}
