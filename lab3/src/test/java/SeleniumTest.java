import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {
    private static WebDriver driver;
    static WebStorage webStorage;
    static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        webStorage = (WebStorage) driver;
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    public void loadSite() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
    }

    public void logIn(String username, String password) {
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
    }

    public void addTwoProductsToCart() {
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]")).click();
    }

    @Test
    @Order(1)
    public void loadSiteScenario() {
        loadSite();
        assertThat(driver.findElement(By.cssSelector(".login_logo")).getText(), is("Swag Labs"));
    }

    @Test
    @Order(2)
    public void loginScenario() {
        correctLogInCheck();
        emptyLoginAndPassword();
        correctPasswordWrongUsername();
        emptyLogin();
        correctUsernameWrongPassword();
        emptyPassword();
        swappedUsernameAndPassword();
        usernameAndPasswordRandom();
    }

    @Test
    @Order(3)
    public void cartScenario() {
        addToCartSameSiteNumberTest();
        checkNumberOfProductsAfterRefresh();
        checkNumberOfProductsOnDifferentSite();
        addNewItemToExistingCart();
    }


    public void correctLogInCheck() {
        loadSite();
        logIn("standard_user", "secret_sauce");

        List<WebElement> elements = driver.findElements(By.cssSelector(".header_secondary_container"));
        assert (elements.size() > 0);
    }


    public void emptyLoginAndPassword() {
        loadSite();
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username is required"));
    }


    public void correctPasswordWrongUsername() {
        loadSite();
        logIn("user", "secret_sauce");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }


    public void emptyLogin() {
        loadSite();
        logIn("", "secret_sauce");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username is required"));
    }


    public void correctUsernameWrongPassword() {
        loadSite();
        logIn("standard_user", "password");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }


    public void emptyPassword() {
        loadSite();
        logIn("standard_user", "");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Password is required"));
    }


    public void swappedUsernameAndPassword() {
        loadSite();
        logIn("secret_sauce", "standard_user");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void usernameAndPasswordRandom() {
        loadSite();
        logIn("aaa", "bbb");

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    public void addToCartSameSiteNumberTest() {
        loadSite();
        logIn("standard_user", "secret_sauce");

        addTwoProductsToCart();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));
        webStorage.getLocalStorage().removeItem("cart-contents");
    }

    public void checkNumberOfProductsAfterRefresh() {
        loadSite();
        logIn("standard_user", "secret_sauce");
        addTwoProductsToCart();

        driver.navigate().refresh();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));

        webStorage.getLocalStorage().removeItem("cart-contents");
    }

    public void checkNumberOfProductsOnDifferentSite() {
        loadSite();
        logIn("standard_user", "secret_sauce");
        addTwoProductsToCart();

        driver.findElement(By.cssSelector("#item_0_img_link > .inventory_item_img")).click();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));

        driver.findElement(By.cssSelector("*[data-test=\"back-to-products\"]")).click();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));

        driver.findElement(By.cssSelector("#item_5_img_link > .inventory_item_img")).click();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("2"));

        webStorage.getLocalStorage().removeItem("cart-contents");
    }

    public void addNewItemToExistingCart() {
        loadSite();
        logIn("standard_user", "secret_sauce");
        addTwoProductsToCart();

        driver.findElement(By.cssSelector("#item_5_img_link > .inventory_item_img")).click();
        driver.findElement(By.cssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
        assertThat(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), is("3"));

        webStorage.getLocalStorage().removeItem("cart-contents");
    }
}
