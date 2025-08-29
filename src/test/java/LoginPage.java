import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By userNameField = By.id("user-name");
    private final By userPasswordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(){
        driver.findElement(userNameField).sendKeys("standard_user");
    }

    public void enterUsername(String username){
        driver.findElement(userNameField).sendKeys(username);
    }

    public void enterPassword(){
        driver.findElement(userPasswordField).sendKeys("secret_sauce");
    }

    public void enterPassword(String password){
        driver.findElement(userPasswordField).sendKeys(password);
    }

    public void pressLoginButton(){
        click(loginButton);
    }

    public String checkTitle(){
        return driver.getTitle();
    }
}
