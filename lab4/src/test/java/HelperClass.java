import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HelperClass {
    private WebDriver driver;
    private final WebStorage webStorage;
    private final JavascriptExecutor js;

    public HelperClass(String driverType) {
        switch (driverType) {
            case "chrome" -> this.driver = new ChromeDriver();
            case "edge" -> this.driver = new EdgeDriver();
            case "firefox" -> this.driver = new FirefoxDriver();
        }
        this.js = (JavascriptExecutor) driver;
        this.webStorage = (WebStorage) driver;
    }

    public void driverQuit() {
        driver.quit();
    }

    public void driverGetAddress(String address) {
        driver.get(address);
    }

    public void setWindow(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void assertByCssSelector(String cssSelector, String value) {
        assertThat(driver.findElement(By.cssSelector(cssSelector)).getText(), is(value));
    }

    public void clickByCssSelector(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void fillInputByCssSelector(String cssSelector, String value) {
        clickByCssSelector(cssSelector);
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(value);
    }

    public void clearLocalStorage(String item) {
        webStorage.getLocalStorage().removeItem(item);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
