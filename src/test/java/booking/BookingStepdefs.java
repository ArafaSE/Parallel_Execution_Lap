package booking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BookingStepdefs {

    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
    String remoteURL = "http://192.168.1.6:5557/wd/hub/";


    public void startBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Booking test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
    }

    public void startChromeNode() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Booking test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
    }

    @Given("I am in the homepage")
    public void iAmInTheHomepage() throws InterruptedException, MalformedURLException {
        //startBrowser();
        startChromeNode();

        driver.navigate().to("https://www.booking.com/");

        Thread.sleep(2000);
    }

    @When("I navigate to signIn page")
    public void iNavigateToSignInPage() throws InterruptedException {
        driver.findElement(By.xpath( "//a[contains(@class, 'js-header-login-link') and contains(., 'Sign in')]")).click();

        Thread.sleep(2000);
    }

    @Then("The signIn form displayed")
    public void theSignInFormDisplayed() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.name("username")).isDisplayed());

        Thread.sleep(2000);
        driver.close();
    }
}
