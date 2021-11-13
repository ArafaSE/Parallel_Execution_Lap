import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class bookingTest {

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
    public void navigateToBookingHome() throws InterruptedException {

        driver.navigate().to("https://www.booking.com/");

        Thread.sleep(2000);
    }

    @Test
    public void openBookingSignInPage() throws InterruptedException {
        driver.findElement(By.xpath( "//a[contains(@class, 'js-header-login-link') and contains(., 'Sign in')]")).click();

        Assert.assertTrue(driver.findElement(By.name("username")).isDisplayed());

        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
