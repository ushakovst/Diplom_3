package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы страницы авторизации
    private final By emailLoginField = By.xpath(".//input[@name='name']");
    private final By passwordLoginField = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    private final By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Логин пользователя: email")
    public void enterEmail(String email) {
        driver.findElement(emailLoginField).sendKeys(email);
    }

    @Step("Логин пользователя: пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordLoginField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переход на страницу регистрации")
    public void clickRegistration() {
        driver.findElement(registrationButton).click();
    }

    @Step("Открыта ли страница логина")
    public boolean isPageOpened() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.and(
                            ExpectedConditions.urlContains("/login"),
                            ExpectedConditions.visibilityOfElementLocated(loginButton)
                    ));
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Ожидание перехода на страницу логина")
    public void waitLoginPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Ожидание появления поля Email")
    public WebElement waitForEmailField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailLoginField));
    }

    @Step("Ожидание появления поля Password")
    public WebElement waitForPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginField));
    }

    @Step("Ожидание появления кнопки 'Войти'")
    public WebElement waitForSubmitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Ожидание появления кнопки 'Зарегестрироваться'")
    public WebElement waitForRegistrationButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
    }

    @Step("Ожидание появления кнопки 'Восстановить пароль'")
    public WebElement waitForRecoverButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordButton));
    }
}