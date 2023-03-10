import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumTest {
    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void correctLogIn() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector(".header_secondary_container"));
        assert (elements.size() > 0);
    }

    @Test
    public void emptyLoginAndPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username is required"));
    }

    @Test
    public void correctPasswordWrongUsername() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("user");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    @Test
    public void emptyLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username is required"));
    }

    @Test
    public void correctUsernameWrongPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("password");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    @Test
    public void emptyPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Password is required"));
    }

    @Test
    public void swappedUsernameAndPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }

    @Test
    public void usernameAndPasswordRandom() {
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1050, 832));
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("aaaa");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("bbbb");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-test=\"error\"]"));
        assert (elements.size() > 0);
        assertThat(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), is("Epic sadface: Username and password do not match any user in this service"));
    }
}
