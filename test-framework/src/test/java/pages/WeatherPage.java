package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeatherPage extends BasePage {
    public WeatherPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "logo")
    WebElement weatherPageLogo;
    @FindBy(id = "loading")
    WebElement loadingBar;
    @FindBy(css = "input[type='text'][id='searchBox']")
    WebElement citySearchBox;




}
