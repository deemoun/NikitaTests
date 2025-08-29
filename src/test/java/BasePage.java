import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void navigateToPage(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(By locator, String text) {
        find(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Assert that the current page title matches the expected title.
     *
     * @param expectedTitle expected title of the current page
     */
    protected void assertTitleEquals(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle,
                "Expected page title to be \"" + expectedTitle + "\" but was \"" + actualTitle + "\"");
    }

    /**
     * Assert that the given locator resolves to the expected number of elements.
     *
     * @param locator       locator to search for
     * @param expectedCount expected number of matching elements
     */
    protected void assertElementCount(By locator, int expectedCount) {
        int actualCount = driver.findElements(locator).size();
        Assertions.assertEquals(expectedCount, actualCount,
                "Expected " + expectedCount + " elements for locator " + locator + " but found " + actualCount);
    }

    /**
     * Assert that the text of the element identified by the locator matches the expected text.
     *
     * @param locator      locator of the element to check
     * @param expectedText expected text value
     */
    protected void assertTextEquals(By locator, String expectedText) {
        String actualText = getText(locator);
        Assertions.assertEquals(expectedText, actualText,
                "Expected text \"" + expectedText + "\" for locator " + locator + " but was \"" + actualText + "\"");
    }
}
