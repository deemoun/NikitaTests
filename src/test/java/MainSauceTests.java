import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class MainSauceTests {

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createFirefox();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
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

    private void addThreeItems() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.addItemToCart("sauce-labs-bolt-t-shirt");
    }

    @Test
    @Order(6)
    public void removeItemsFromCartTest() {
        doLogin();
        addThreeItems();
        inventoryPage.goToCart();
        cartPage.removeItem("sauce-labs-backpack");
        cartPage.removeItem("sauce-labs-bike-light");
        cartPage.removeItem("sauce-labs-bolt-t-shirt");
        Assertions.assertEquals(0, driver.findElements(By.className("cart_item")).size());
    }

    @Test
    @Order(7)
    public void checkoutAfterAddingThreeItems() {
        doLogin();
        addThreeItems();
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        Assertions.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }

    @Test
    @Order(8)
    public void returnToInventoryFromCart() {
        doLogin();
        inventoryPage.goToCart();
        cartPage.continueShopping();
        Assertions.assertTrue(cartPage.isInventoryPage());
    }

    @Test
    @Order(9)
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
