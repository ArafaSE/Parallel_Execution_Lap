package booking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BookingStepdefs {

    public static WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
    String IEPath = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
    // String remoteURL = "http://192.168.56.1:5557/wd/hub/";
    String remoteURL = "http://192.168.56.1:4444/wd/hub/";

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

    public void startIENode() throws MalformedURLException {
        System.setProperty("webdriver.ie.driver", IEPath);
        InternetExplorerOptions ieCapabilities = new InternetExplorerOptions();

        ieCapabilities.setCapability("requireWindowFocus()", true);
        ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
        ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
        ieCapabilities.setCapability("disable-popup-blocking", true);
        ieCapabilities.setCapability("enablePersistentHover", true);
        ieCapabilities.setCapability("ignoreZoomSetting", true);
        ieCapabilities.setCapability("nativeEvents", false);

        driver = new RemoteWebDriver(new URL(remoteURL), ieCapabilities);

        System.out.println("\nBooking test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        System.out.println("Thread Group name: " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("Thread Group parent: " + Thread.currentThread().getThreadGroup().getParent().toString());

    }

    @Given("I am in the homepage")
    public void iAmInTheHomepage() throws InterruptedException, MalformedURLException {
        startChromeNode();

        Thread.sleep(3000);g

        driver.navigate().to("https://www.booking.com/");

        Thread.sleep(3000);
//        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"));
    }

    @When("I navigate to signIn page")
    public void iNavigateToSignInPage() throws InterruptedException {
        //driver.findElement(By.xpath( "//a[contains(@class, 'js-header-login-link') and contains(., 'Sign in')]")).click();
        driver.navigate().to("https://account.booking.com/sign-in/");

        Thread.sleep(4000);
    }

    @Then("The signIn form displayed")
    public void theSignInFormDisplayed() throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.name("username")).isDisplayed());

        Thread.sleep(2000);
        driver.quit();
    }
}
