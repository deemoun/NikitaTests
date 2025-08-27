import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private By userNameField = By.id("user-name");
    private By userPasswordField = By.id("password");
    private By loginButton = By.id("login-button");

    LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void navigateToPage(String url){
        driver.get(url);
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
        driver.findElement(loginButton).click();
    }

    public String checkTitle(){
        return driver.getTitle();
    }
    // Зачем это здесь?
    public void quit(){
        driver.quit();
    }
}
