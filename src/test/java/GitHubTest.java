import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GitHubTest {
    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

    @BeforeClass
    @Parameters({"remoteUrl"})
    public void start(String remoteURL) throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("GitHub test Running with ID: " + Thread.currentThread().getId());
    }

    @Test
    public void navigateToGitHubHomePage() throws InterruptedException {

        driver.navigate().to("https://github.com/");
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.id("user_email")).isDisplayed());
    }
    @Test
    public void openSignInPage() throws InterruptedException {
        driver.findElement(By.xpath( "//a[contains(@class, 'HeaderMenu-link') and contains(., 'Sign in')]")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.id("login_field")).isDisplayed());

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
