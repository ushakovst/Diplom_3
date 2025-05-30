package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationPageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы страницы регистрации
    private final By nameRegisterField = By.xpath(".//div[label[text()='Имя']]/input");
    private final By emailRegisterField = By.xpath(".//div[label[text()='Email']]/input");
    private final By passwordRegisterField = By.cssSelector("input[type='password']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordRegisterError = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Ожидание появления ошибки")
    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRegisterError));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Регистрация нового пользователя")
    public void registration(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameRegisterField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailRegisterField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRegisterField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Проверка появления ошибки при регистрации")
    public void checkError() {
        assertTrue("Должна отображаться ошибка пароля",
                isErrorDisplayed());
    }
}