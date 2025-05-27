package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локатор кнопки Войти
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Ожидание появления кнопки 'Войти'")
    public WebElement waitForLoginLink() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}