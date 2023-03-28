package com.github.ncr0s.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

@Tags({@Tag("mobile"), @Tag("iOS")})
public class IosSearchTests extends TestBase {
    Faker faker = new Faker();

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
