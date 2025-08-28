import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    CartPage(WebDriver driver) {
        super(driver);
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public void removeItem(String itemId) {
        driver.findElement(By.id("remove-" + itemId)).click();
    }

    public boolean isInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}
