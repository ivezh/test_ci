package simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
                // By default saved video files are named <session-id>.mp4 where <session-id> is a unique identifier of Selenium session.
//                "videoName", "my-cool-video.mp4",
//                "videoScreenSize", "1024x768",
//                "videoFrameRate", 24, // Default video frame rate is 12
//                "videoCodec", "mpeg4", // libx264 is used by default
//                "enableLog", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    void loginTest(){

        step("Open website", () -> {
            open("/signin");
        });

        step("Fill in the input fields", () -> {
            $(By.cssSelector("[name=\"usernameOrEmail\"]")).setValue("admin@gmail.com");
            $(By.cssSelector("[name=\"password\"]")).setValue("test");
            takeScreenshot();
        });

        step("Submit creds and check login is successfull", () -> {
            $(By.cssSelector("[type=\"submit\"]")).click();
            $(By.cssSelector("[href=\"./navigation\"]")).shouldBe(Condition.visible);
            takeScreenshot();
        });
    }
}
