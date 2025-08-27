import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SauceDemoTests {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
        clickUserName(driver, "standard_user");
        clickPassword(driver, "secret_sauce");
        clickLoginButton(driver);
        clickMenuBar(driver);
        clickLogoutButton(driver);
        driver.quit();
    }

    public static void clickUserName(WebDriver driver, String username) {
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.clear();
        userField.sendKeys(username);
    }

    public static void clickPassword (WebDriver driver, String password) {
        WebElement passField = driver.findElement(By.id("password"));
        passField.clear();
        passField.sendKeys(password);
    }
    public static void clickLoginButton(WebDriver driver) {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    public static void clickMenuBar(WebDriver driver) {
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
    }
    public static void clickLogoutButton(WebDriver driver) {

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
    }
}
