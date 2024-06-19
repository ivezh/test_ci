package simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Simple")
public class SkippedTests {
    @Test
    @Disabled
    void test1(){
        assertTrue(true);
    }
    @Test
    @Disabled
    void test2(){
        assertTrue(true);
    }
    @Test
    @Disabled
    void test3(){
        assertTrue(true);
    }
    @Test
    @Disabled
    void test4(){
        assertTrue(true);
    }
}
