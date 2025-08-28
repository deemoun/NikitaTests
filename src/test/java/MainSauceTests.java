import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class MainSauceTests {

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createFirefox();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    public void doLogin(){
        loginPage.navigateToPage(BASE_URL);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.pressLoginButton();
    }

    public void doLogin(String user, String password){
        loginPage.navigateToPage(BASE_URL);
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.pressLoginButton();
    }

    @Test
    @Order(1)
    @DisplayName("Login Test")
    public void loginTest() {
        doLogin("problem_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        Assertions.assertEquals("Swag Labs", loginPage.checkTitle(), "Title doesn't match");
    }
    @Test
    @Order(2)
    public void removeBackpackFromCartTest () {
        doLogin();
        inventoryPage.addBackpackToCart();
        inventoryPage.removeBackpackFromCart();
    }

    @Test
    @Order(3)
    public void addBikeLightTest() {
        doLogin();
        inventoryPage.addBikeLightToCart();
    }
    @Test
    @Order(4)
    public void removeBikeLightTest() {
        doLogin();
        inventoryPage.addBikeLightToCart();
        inventoryPage.removeBikeLightFromCart();
    }

    @Test
    @Order(5)
    public void navigateToCart(){
        doLogin();
        inventoryPage.goToCart();
    }

    @Test
    @Order(6)
    @DisplayName("Logout Test")
    public void logoutTest() {
        doLogin();
        inventoryPage.pressMenuButton();
        inventoryPage.pressLogoutButton();
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.closeFirefox(driver);
    }
}
