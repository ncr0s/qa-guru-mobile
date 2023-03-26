package com.github.ncr0s.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

@Tags({@Tag("mobile"), @Tag("wikipedia")})
public class WikipediaTests extends TestBase {
    Faker faker = new Faker();

    @Tag("Android")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Wikipedia article search test")
    @Test
    void successfulSearchTest() {
        step("Enter 'java' in the search bar", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("java");
        });
        step("Verify that some content found", () ->
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("Android")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check current date in News block")
    @Test
    void checkCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        String date = formatter.format(new Date());
        step("Check date in the news block", () -> {
            $(id("org.wikipedia.alpha:id/view_card_header_subtitle")).shouldHave(text(date));
        });
    }

    @Tag("iOS")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Text input display test")
    @Test
    void checkSearchBarIosTest() {
        String value = faker.elderScrolls().race();
        step(String.format("Enter %s in the search bar", value), () -> {
            $(id("Text Button")).click();
            $(id("Text Input")).sendKeys(value);
            $(id("Text Input")).pressEnter();
        });
        step(String.format("Text '%s' should be shown", value), () -> {
            $(id("Text Output")).shouldHave(text(value));
        });
    }
}
