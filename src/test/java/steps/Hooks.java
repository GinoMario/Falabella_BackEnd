package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.example.framework.browserManager.DriverManager;
import org.example.framework.browserManager.DriverManagerFactory;
import org.example.framework.browserManager.DriverType;
import org.example.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hooks{
    private static WebDriver driver;
    private static int numberCase = 0;
    private DriverManager driverManager;

    @Before
    public void setUp() throws IOException {
        numberCase++;
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Se esta ejecutando el escenario: "+numberCase);
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.get("https://www.falabella.com.pe/");
//        driver.manage().window().maximize();
        ConfigReader.PopulateSettings();

    }

    @After
    public void tearDown() {
        System.out.println("El escenario "+numberCase+" se ejecutó correctamente");
        driverManager.quitDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
