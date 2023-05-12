package StepDefinition;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepsTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @And("I enter the username {string}")
    public void iEnterTheUsername(String username) {
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".header_secondary_container"));
        assert (elements.size() > 0);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String msg) {
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is(msg));
    }

    @AfterEach()
    public void closeBrowser() {
        driver.quit();
    }
}
