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

    @Step("Переход на страницу регистрации")
    public void clickRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton)).click();
    }

    @Step("Открыта ли страница логина")
    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.and(
                    ExpectedConditions.urlContains("/login"),
                    ExpectedConditions.visibilityOfElementLocated(loginButton)
            )) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Ожидание перехода на страницу логина")
    public void waitLoginPage(){
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Авторизация зарегестрированного пользователя")
    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailLoginField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton)).click();
    }

    @Step("Клик по кнопке восстановление пароля")
    public void clickRecoverButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordButton)).click();
    }

    @Step("Проверка страницы логина")
    public void checkLoginPage() {
        assertTrue("Должны быть перенаправлены на страницу логина", isPageOpened());
    }
}