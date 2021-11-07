import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class UdemyTest {
    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

    @BeforeClass
    @Parameters({"remoteUrl"})
    public void start(String remoteURL) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Udemy test Running with ID: " + Thread.currentThread().getId());
    }

    @Test
    public void navigateToUdemyHomePage() throws InterruptedException {

        driver.navigate().to("https://www.udemy.com/");
        Assert.assertTrue(driver.getTitle().contains("Online Courses"));

        Thread.sleep(5000);
    }
    @Test
    public void openUdemyAboutPage() throws InterruptedException {
       driver.navigate().to("https://about.udemy.com/");
       Thread.sleep(3000);
       Assert.assertTrue(driver.findElement(By.className("c_hero__title")).isDisplayed());

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
