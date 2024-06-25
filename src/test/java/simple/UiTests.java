package simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@Tag("remote_test")
public class UiTests {


    @BeforeAll
    static void beforeAll(){
        Configuration.baseUrl = "https://allpeople-aws.noveogroup.com";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "124.0";
        Configuration.remote = "http://172.30.31.22:8080/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttchment(){
        Attach.addPageSource();
        Attach.addScreenshot();
        Attach.addConsoleLog();
        Attach.addVideo();
    }

    @Test
    void loginTest(){

        step("Open website", () -> {
            open("/signin");
            Attach.addScreenshot();
        });

        step("Fill in the input fields", () -> {
            $(By.cssSelector("[name=\"usernameOrEmail\"]")).setValue("admin@gmail.com");
            $(By.cssSelector("[name=\"password\"]")).setValue("test");
            Attach.addScreenshot();
        });

        step("Submit creds and check login is successfull", () -> {
            $(By.cssSelector("[type=\"submit\"]")).click();
            $(By.cssSelector("[href=\"./navigation\"]")).shouldBe(Condition.visible);
            Attach.addScreenshot();
        });
    }
}
