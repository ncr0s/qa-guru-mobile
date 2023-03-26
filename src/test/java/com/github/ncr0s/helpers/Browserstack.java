package com.github.ncr0s.helpers;

import com.github.ncr0s.config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;

import static com.github.ncr0s.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String getVideoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

        return given()
            .log().all()
            .filter(withCustomTemplates())
            .auth().basic(config.getUserName(), config.getPassword())
            .when()
            .get(url)
            .then()
            .log().all()
            .statusCode(200)
            .extract().path("automation_session.video_url");
    }
}
