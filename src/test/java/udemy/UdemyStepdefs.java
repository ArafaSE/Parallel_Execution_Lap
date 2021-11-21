package udemy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class UdemyStepdefs {
    WebDriver driver;
    String ChromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
    // String remoteURL = "http://192.168.56.1:5557/wd/hub/";
    String remoteURL = "http://192.168.56.1:4444/wd/hub/";


    public void startBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        System.out.println("Udemy test Running with Thread ID: " + Thread.currentThread().getId());
    }

    public void startChromeNode() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", ChromePath);

        driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        driver.manage().window().maximize();

        System.out.println("\nUdemy test Running with Thread ID: " + Thread.currentThread().getId());
        System.out.println("Active Thread Count: "+ Thread.activeCount());
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        System.out.println("Thread Group name: " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("Thread Group parent: " + Thread.currentThread().getThreadGroup().getParent().toString());

    }

    @Given("I opened the homepage")
    public void iOpenedTheHomepage() throws InterruptedException, MalformedURLException {
        startChromeNode();
        driver.navigate().to("https://www.udemy.com/");
        Assert.assertTrue(driver.getTitle().contains("Online Courses"));

        Thread.sleep(5000);
    }

    @When("I navigate to about")
    public void iNavigateToAbout() throws InterruptedException {
        driver.navigate().to("https://about.udemy.com/");
        Thread.sleep(3000);

    }

    @Then("The about page content is displayed")
    public void theAboutPageContentIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.className("c_hero__title")).isDisplayed());
        driver.quit();
    }
}
