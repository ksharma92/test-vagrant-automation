package selenium_framework;

import enums.Browsers;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Properties;

@Slf4j
public class SeleniumBaseFramework {
    public WebDriver driver;
    public Properties properties = new Properties();
    protected String className;
    boolean isUiTest = true;

    private void setProperties() throws Exception {
        properties.load(this.getClass().getResourceAsStream("/configuration.properties"));
    }

    @Parameters({"browser"})
    @BeforeClass
    public void browserSetup(@Optional("CHROME") String browser) throws Exception {
        String buildTag = System.getProperty("buildTag");
        setTestType();
        System.out.println("<<<<RUNNING TEST CLASS: " + className + " >>>>");
        setProperties();
        if (isUiTest)
            driver = WebDriverFactory.getDriver(Browsers.valueOf(browser), properties);
        log.info("<<<<<<<<<<<<<<<<{}>>>>>>>>>>>>>>>", System.getProperty("buildTag"));
    }

    @AfterClass(alwaysRun = true)
    public void browserQuit() {
        if (isUiTest) {
            try {
                driver.close();
            } catch (NullPointerException e) {
                return;
            }
            try {
                driver.quit();

            } catch (NullPointerException e) {
                return;
            }
        }
    }

    private void setTestType() {
        String[] nameArray = this.getClass().getName().split("\\.");
        className = nameArray[nameArray.length - 1];
        if (className.contains("BackendTest")) isUiTest = false;
    }


}
