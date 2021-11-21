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
    String remoteURL = "http://192.168.1.6:5558/wd/hub/";

    public void startBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Osn test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
    }
    public void startChromeNode() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Osn test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
    }

    @Given("I opened the browser")
    public void iOpenedTheBrowser() throws MalformedURLException {
        //startBrowser();
        startChromeNode();
    }

    @When("I navigate homepage")
    public void iNavigateHomepage() throws InterruptedException {
        driver.navigate().to("https://stream.osn.com/en-eg/home");

        Thread.sleep(5000);
    }

    @Then("The home page content is displayed")
    public void theHomePageContentIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains("home"));
        driver.close();
    }
}
