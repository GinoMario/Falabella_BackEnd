package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestBase {
    protected WebDriver driver = Hooks.getDriver();
}
