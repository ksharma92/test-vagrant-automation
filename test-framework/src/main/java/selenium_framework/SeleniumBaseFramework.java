package selenium_framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class SeleniumBaseFramework {
    public WebDriver driver;
    public Properties properties = new Properties();

    private void setProperties() throws Exception {
        properties.load(this.getClass().getResourceAsStream("/configuration.properties"));
    }

    @Parameters({"browser"})
    @BeforeClass
    public void browserSetup(@Optional("CHROME") String browser) throws Exception {
        setProperties();
        driver = WebDriverFactory.getDriver(Browsers.valueOf(browser), properties);
    }
}
