import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage{
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");
    private final By backpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By bikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");


    InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void pressMenuButton() {
        driver.findElement(menuButton).click();
    }

    public void pressLogoutButton() {
        driver.findElement(logoutButton).click();
    }
    public void addBackpackToCart() {
        driver.findElement(backpackButton).click();
    }
    public void addBikeLightToCart() {
        driver.findElement(bikeLightButton).click();
    }
    public void removeBikeLightFromCart() {
        driver.findElement(removeBikeLightButton).click();
    }

    public void removeBackpackFromCart() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    public void goToCart() {
        //driver.findElement(By.className("shopping_cart_link")).click();
        navigateToPage("https://www.saucedemo.com/cart.html");
    }
    public boolean isBackpackInCart() {
        return driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();
    }

    public boolean isBikeLightInCart() {
        return driver.findElement(By.id("remove-sauce-labs-bike-light")).isDisplayed();
    }

    public boolean isBackpackRemoved() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed();
    }

    public boolean isBikeLightRemoved() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).isDisplayed();
    }
    public boolean isCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }
}
