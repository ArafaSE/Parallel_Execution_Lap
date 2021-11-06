import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class osnTest {

    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

    @BeforeTest
    @Parameters({"remoteUrl"})
    public void beforeTest(String remoteURL) throws MalformedURLException {
        ChromeOptions chromeOptions= new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);
        if (remoteURL.isEmpty()){
            driver = new ChromeDriver(chromeOptions);
        }else {
            driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        }

    }
    @Test
    public void osnTest1() throws InterruptedException {

        driver.get("https://stream.osn.com/en-eg/home");
        driver.manage().window().maximize();
        System.out.println("Booking test Running with ID: " + Thread.currentThread().getId());
        Thread.sleep(5000);
    }
    @AfterSuite
    public void tearDown(){
        driver.close();
    }

}