package selenium_framework;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver getDriver(Browsers browser, Properties properties) {
        WebDriver driver = null;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            File f = new File("src\\test\\chromedriver\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
        }

        //support for more browsers can easily be added by adding the browser name in the enum
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.addArguments("--disable-translate");
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                options.setExperimentalOption("w3c", true);
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        setTimeout(driver);
        return driver;
    }

    private static void setTimeout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }
}
