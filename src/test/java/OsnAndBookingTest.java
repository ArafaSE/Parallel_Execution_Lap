import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class OsnAndBookingTest {
    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

    @BeforeTest
    public void beforeTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void bookingTest() throws InterruptedException {

        driver.navigate().to("https://www.booking.com/");
        driver.manage().window().maximize();
        System.out.println("Booking test Running with ID: " + Thread.currentThread().getId());
        Thread.sleep(3000);
    }

    @Test
    public void osnTest() throws InterruptedException {

        driver.get("https://stream.osn.com/en-eg/home");
        driver.manage().window().maximize();
        System.out.println("Booking test Running with ID: " + Thread.currentThread().getId());
        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
