import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTest {

    HelperClass helperClass;

    @BeforeAll
    static void setDrivers() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @AfterEach
    public void tearDown() {
        this.helperClass.driverQuit();
    }

    @Test
    @Order(1)
    public void chromeScenarios() {
        this.helperClass = new HelperClass("chrome");
        loadSiteAndAddToBasketScenario();
    }

    @Test
    @Order(2)
    public void edgeScenarios() {
        this.helperClass = new HelperClass("edge");
        loadSiteAndAddToBasketScenario();
    }

    @Test
    @Order(3)
    public void firefoxScenarios() {
        this.helperClass = new HelperClass("firefox");
        loadSiteAndAddToBasketScenario();
    }

    public void loadSiteAndAddToBasketScenario() {
        loadSiteTest();
        addToCartSameSiteNumberTest();
        checkNumberOfProductsAfterRefresh();
        checkNumberOfProductsOnDifferentSites();
        addNewItemToExistingCart();
    }

    public void loadSiteTest() {
        loadSite();
        helperClass.assertByCssSelector(".login_logo", "Swag Labs");
    }

    public void addToCartSameSiteNumberTest() {
        loadSite();
        logIn();

        addTwoProductsToCart();
        helperClass.assertByCssSelector(".shopping_cart_badge", "2");
        helperClass.clearLocalStorage("cart-contents");
    }

    public void checkNumberOfProductsAfterRefresh() {
        loadSite();
        logIn();

        addTwoProductsToCart();
        helperClass.refreshPage();
        helperClass.assertByCssSelector(".shopping_cart_badge", "2");
        helperClass.clearLocalStorage("cart-contents");
    }

    public void checkNumberOfProductsOnDifferentSites() {
        loadSite();
        logIn();
        addTwoProductsToCart();

        helperClass.clickByCssSelector("#item_0_img_link > .inventory_item_img");
        helperClass.assertByCssSelector(".shopping_cart_badge", "2");

        helperClass.clickByCssSelector("*[data-test=\"back-to-products\"]");
        helperClass.assertByCssSelector(".shopping_cart_badge", "2");

        helperClass.clickByCssSelector("#item_5_img_link > .inventory_item_img");
        helperClass.assertByCssSelector(".shopping_cart_badge", "2");

        helperClass.clearLocalStorage("cart-contents");
    }


    public void addNewItemToExistingCart() {
        loadSite();
        logIn();
        addTwoProductsToCart();

        helperClass.clickByCssSelector("#item_5_img_link > .inventory_item_img");
        helperClass.clickByCssSelector("*[data-test=\"add-to-cart-sauce-labs-fleece-jacket\"]");
        helperClass.assertByCssSelector(".shopping_cart_badge", "3");

        helperClass.clearLocalStorage("cart-contents");
    }

    public void loadSite() {
        helperClass.driverGetAddress("https://www.saucedemo.com/");
        helperClass.setWindow(1050, 850);
    }

    public void logIn() {
        helperClass.fillInputByCssSelector("*[data-test=\"username\"]", "standard_user");
        helperClass.fillInputByCssSelector("*[data-test=\"password\"]", "secret_sauce");
        helperClass.clickByCssSelector("*[data-test=\"login-button\"]");
    }

    public void addTwoProductsToCart() {
        helperClass.clickByCssSelector("*[data-test=\"add-to-cart-sauce-labs-backpack\"]");
        helperClass.clickByCssSelector("*[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
    }
}
