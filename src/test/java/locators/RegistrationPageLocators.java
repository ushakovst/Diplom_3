package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @Step("Регистрация пользователя: name")
    public void enterRegisterName(String name) {
        driver.findElement(nameRegisterField).sendKeys(name);
    }

    @Step("Регистрация пользователя: email")
    public void enterRegisterEmail(String email) {
        driver.findElement(emailRegisterField).sendKeys(email);
    }

    @Step("Регистрация пользователя: password")
    public void enterRegisterPassword(String password) {
        driver.findElement(passwordRegisterField).sendKeys(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Проверка ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return driver.findElement(passwordRegisterError).isDisplayed();
    }

    @Step("Геттер ошибки")
    public By getPasswordErrorLocator() {
        return passwordRegisterError;
    }

    @Step("Ожидание появления кнопки 'Войти'")
    public WebElement waitForLoginLink() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}