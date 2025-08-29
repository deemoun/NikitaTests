import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainSauceTests {

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
        loginPage.navigateToBaseUrl();
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.pressLoginButton();
    }

    public void doLogin(String user, String password){
        loginPage.navigateToBaseUrl();
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.pressLoginButton();
    }

    @Test
    @Order(1)
    @DisplayName("Login Test: Assert Standard User")
    public void loginTest() {
        doLogin();
        inventoryPage.addItemToCart("sauce-labs-backpack");
        //Assertions.assertEquals("Swag Labs", loginPage.checkTitle(), "Title doesn't match");
        inventoryPage.assertTitle("Swag Labs");
    }

    @Test
    @Order(2)
    @DisplayName("Login Test: Problem user")
    public void loginTestProblemUser() {
        doLogin("problem_user", "secret_sauce");
        inventoryPage.addItemToCart("sauce-labs-backpack");
        Assertions.assertEquals("Swag Labs", loginPage.checkTitle(), "Title doesn't match");
    }


    @ParameterizedTest
    @ValueSource(strings = {"sauce-labs-backpack", "sauce-labs-bike-light"})
    @Order(3)
    public void addItemToCartTest(String itemId) {
        doLogin();
        inventoryPage.addItemToCart(itemId);
        Assertions.assertTrue(inventoryPage.isItemInCart(itemId));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sauce-labs-backpack", "sauce-labs-bike-light"})
    @Order(4)
    public void removeItemFromCartTest(String itemId) {
        doLogin();
        inventoryPage.addItemToCart(itemId);
        inventoryPage.removeItemFromCart(itemId);
        Assertions.assertTrue(inventoryPage.isItemRemoved(itemId));
    }

    @Test
    @Order(5)
    public void navigateToCart(){
        doLogin();
        inventoryPage.goToCart();
    }

    public void addThreeItems() {
        String[] ids = {
                "sauce-labs-backpack",
                "sauce-labs-bike-light",
                "sauce-labs-bolt-t-shirt"
        };

        String[] ids2 = { "test", "test2", "test3" };

        for (String id : ids) {
            inventoryPage.addItemToCart(id);
        }
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
