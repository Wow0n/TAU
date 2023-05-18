package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.CoreMatchers;
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
    }

    @Before
    public static void setUpScenario() {
        driver = new ChromeDriver();
    }

    @After
    public void closeBrowser() {
        driver.quit();
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

    @And("I login correctly by username {string} and password {string}")
    public void iLoginCorrectlyByUsernameAndPassword(String username, String password) {
        iEnterTheUsername(username);
        iEnterPassword(password);
        iClickTheLoginButton();
    }

    @And("I add two products to cart")
    public void iAddTwoProductsToCart() {
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]")).click();
    }

    @When("I refresh the page")
    public void iRefreshThePage() {
        driver.navigate().refresh();
    }

    @And("I click on product {string}")
    public void iClickOnProduct(String product) {
        driver.findElement(By.cssSelector(product)).click();
    }

    @And("I go back to products page")
    public void iGoBackToProductsPage() {
        driver.findElement(By.cssSelector("*[data-test=\"back-to-products\"]")).click();
    }

    @And("I add it to the cart")
    public void iAddItToTheCart() {
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
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

    @Then("I check if cart badge equals {string}")
    public void iCheckIfCartBadgeEquals(String numberOfProducts) {
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is(numberOfProducts));
    }

    @And("I filter by {string}")
    public void iFilterBy(String filterOption) {
        WebElement dropdown = driver.findElement(By.cssSelector("*[data-test=\"product_sort_container\"]"));
        dropdown.findElement(By.xpath("//option[. = '" + filterOption + "']")).click();
    }

    @Then("I check if the first product is {string}")
    public void iCheckIfTheFirstProductIs(String firstProduct) {
        driver.findElement(By.xpath("//div[@id='header_container']/div[2]/div/span/select")).click();
        assertThat(driver.findElement(By.xpath("//div[@id='inventory_container']/div/div/div[2]/div/a/div")).getText(), CoreMatchers.is(firstProduct));
    }
}
