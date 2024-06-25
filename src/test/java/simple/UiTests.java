package simple;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


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

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    void loginTest(){
        open("/signin");
        $(By.cssSelector("[name=\"usernameOrEmail\"]")).setValue("admin@gmail.com");
        $(By.cssSelector("[name=\"password\"]")).setValue("test");
        takeScreenshot();
        $(By.cssSelector("[type=\"submit\"]")).click();
        $(By.cssSelector("[href=\"./navigation\"]")).shouldBe(Condition.visible);
        takeScreenshot();
        System.out.println("NNNNNNNNAAAAAME " + WebDriverRunner.driver().browser().isSafari());
    }
}
