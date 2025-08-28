import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");
    
    public void addItemToCart(String itemId) {
        driver.findElement(By.id("add-to-cart-" + itemId)).click();
    }

    public void removeItemFromCart(String itemId) {
        driver.findElement(By.id("remove-" + itemId)).click();
    }

    public boolean isItemInCart(String itemId) {
        return driver.findElement(By.id("remove-" + itemId)).isDisplayed();
    }

    public boolean isItemRemoved(String itemId) {
        return driver.findElement(By.id("add-to-cart-" + itemId)).isDisplayed();
    }

    InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void pressMenuButton() {
        driver.findElement(menuButton).click();
    }

    public void pressLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void goToCart() {
        //driver.findElement(By.className("shopping_cart_link")).click();
        navigateToPage("https://www.saucedemo.com/cart.html");
    }

    public boolean isCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }
}
