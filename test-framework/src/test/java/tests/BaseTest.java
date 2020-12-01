package tests;

import org.testng.annotations.Test;
import selenium_framework.SeleniumBaseFramework;

public class BaseTest extends SeleniumBaseFramework {
    @Test()
    public void test() {
        driver.get("https://google.com");
    }
}
