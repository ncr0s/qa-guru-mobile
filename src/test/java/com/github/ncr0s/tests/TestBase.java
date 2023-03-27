package com.github.ncr0s.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.ncr0s.drivers.BrowserstackMobileDriver;
import com.github.ncr0s.drivers.LocalMobileDriver;
import com.github.ncr0s.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.github.ncr0s.helpers.Attach.getSessionId;

public class TestBase {

    public static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {

        switch (deviceHost) {
            case "appium":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "android":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
        }

        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        Attach.pageSource();
        closeWebDriver();

        if (deviceHost.equals("android")) {
            Attach.addVideo(sessionId);
        } else if (deviceHost.equals("ios")) {
            Attach.addVideo(sessionId);
        }
    }
}
