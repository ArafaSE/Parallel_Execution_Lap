package osn;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class OsnStepdefs {
    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
    //    String remoteURL = "http://192.168.56.1:5558/wd/hub/";
    String remoteURL = "http://192.168.56.1:4444/wd/hub/";

    public void startBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Osn test Running with Thread ID: " + Thread.currentThread().getId());
    }

    public void startChromeNode() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("\nOsn test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: " + Thread.activeCount());
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        System.out.println("Thread Group name: " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("Thread Group parent: " + Thread.currentThread().getThreadGroup().getParent().toString());
    }

    @Given("I opened the browser")
    public void iOpenedTheBrowser() throws MalformedURLException {
        //startBrowser();
        startChromeNode();
    }

    @When("I navigate to homepage")
    public void iNavigateToHomepage() throws InterruptedException {
        driver.navigate().to("https://stream.osn.com/en-eg/home");

        Thread.sleep(5000);
    }

    @Then("The home page content is displayed")
    public void theHomePageContentIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains("home"));
        driver.quit();
    }
}