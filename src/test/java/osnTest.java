import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class osnTest {

    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

    @BeforeClass
    @Parameters({"remoteUrl"})
    public void start(String remoteURL) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Booking test Running with ID: " + Thread.currentThread().getId());
    }

    @Test
    public void osnTest1() throws InterruptedException {

        driver.navigate().to("https://stream.osn.com/en-eg/home");

        Thread.sleep(5000);
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }

}
