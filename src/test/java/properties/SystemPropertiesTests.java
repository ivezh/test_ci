package properties;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @Test
    void simplePropertyTest() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName);
    }
    @Test
    @Tag("property_test")
    void simplePropertyTest1() {
//        String browserName = System.getProperty("browser", "chrome");
        System.out.println("HELOOOOOOOOOOOO  " + System.getProperty("browser", "chrome"));
    }
}
