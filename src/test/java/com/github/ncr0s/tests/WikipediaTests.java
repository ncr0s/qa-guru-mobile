package com.github.ncr0s.tests;

import com.github.ncr0s.data.DialogButtons;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

@Tags({@Tag("mobile"), @Tag("wikipedia"), @Tag("Android")})
public class WikipediaTests extends TestBase {

    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Wikipedia article search test")
    @Test
    void successfulSearchTest() {
        step("Skip onboarding screen", () ->
            back());
        step("Enter 'java' in the search bar", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify that some content found", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .shouldHave(sizeGreaterThan(0)));
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Walkthrough onboarding flow test")
    @EnumSource(DialogButtons.class)
    @ParameterizedTest(name = "Walkthrough onboarding flow with {0} send statistics")
    void onboardingFlowTest(DialogButtons button) {
        String firstPage = "The Free Encyclopedia …in over 300 languages";
        String secondPage = "New ways to explore";
        String thirdPage = "Reading lists with sync";
        String lastPage = "Send anonymous data";

        step(String.format("Check %s text and tap Continue", firstPage), () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text(firstPage));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step(String.format("Check %s text and tap Continue", secondPage), () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text(secondPage));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step(String.format("Check %s text and tap Continue", thirdPage), () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text(thirdPage));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step(String.format("Check %s text", lastPage), () ->
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text(lastPage)));
        step(String.format("Tap on %s button", button), () ->
            $(id(button.getChoiceButton())).click());
        step("Main page should be shown", () ->
            $(id("org.wikipedia.alpha:id/search_container")).shouldBe(visible));
    }
}
