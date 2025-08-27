import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class OptimizedTests {
    private WebDriver driver;
    private static final String BASE_URL = "https://www.saucedemo.com/";
    LoginPage loginPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void doLogin() {
        //LoginPage login = new LoginPage(driver);
        loginPage.navigateToPage(BASE_URL);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.pressLoginButton();
    }

    @Test
    @DisplayName("User can open site and log in")
    void loginTest() {
        doLogin();
        //LoginPage login = new LoginPage(driver);
        Assertions.assertEquals("Swag Labs", loginPage.checkTitle(), "App title should be 'Swag Labs'");
    }

    @Test
    @DisplayName("Add & remove Backpack")
    void addAndRemoveBackpack() {
        doLogin();
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBackpackToCart();
        inventory.removeBackpackFromCart();
    }

    @Test
    @DisplayName("Add Bike Light")
    void addBikeLight() {
        doLogin();
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBikeLightToCart();
    }

    @Test
    @DisplayName("Remove Bike Light")
    void removeBikeLight() {
        doLogin();
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addBikeLightToCart();
        inventory.removeBikeLightFromCart();
    }

    @Test
    @DisplayName("Navigate to Cart")
    void navigateToCart(){
        doLogin();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.navigateToPage("https://www.saucedemo.com/cart.html");

    }

    @Test
    @DisplayName("Logout returns to Login page")
    void logoutTest() {
        doLogin();
        InventoryPage inventory = new InventoryPage(driver);
        inventory.pressMenuButton();
        inventory.pressLogoutButton();
    }
}
