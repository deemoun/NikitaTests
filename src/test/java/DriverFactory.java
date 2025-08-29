import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver createFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-width=1920", "-height=1080");
        return new FirefoxDriver(opts);
    }

    public static void closeFirefox(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit(); // корректно убивает всю сессию
            } catch (WebDriverException e) {
                try {
                    driver.close(); // попытка закрыть текущее окно (если ещё живо)
                } catch (Exception ignored) {}
            }
        }
    }
}