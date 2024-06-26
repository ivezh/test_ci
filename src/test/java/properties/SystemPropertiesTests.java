package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void simplePropertyTest() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName);
    }
    @Test
    @Tags({@Tag("property_test"), @Tag("prod")})
    void simplePropertyTest1() {
//        String browserName = System.getProperty("browser", "chrome");
        System.out.println(System.getProperty("browser"));
    }
}
