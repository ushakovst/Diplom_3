package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class HomePageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы на главной странице
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By placeAnOrder = By.xpath(".//button[text()='Оформить заказ']");

    public HomePageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Ожидание успешной авторизации")
    public void waitForUrlsContains(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    @Step("Переход в личный кабинет")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Проверка отображения главной страницы")
    public boolean isHomePageDisplayed(String url) {
        return driver.getCurrentUrl().equals(url);
    }

    @Step("Проверка отображения кнопки 'Войти в аккаунт'")
    public void verifyLoginButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).getText();
    }

    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public void verifyOrderButtonVisible() {
        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrder)).getText();
        assertEquals("Неверный текст кнопки", "Оформить заказ", actualText);
    }

    @Step("Проверка отображения кнопки 'Личный кабинет'")
    public void verifyPersonalAccountButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton)).getText();
    }

    @Step("Проверка страницы профиля")
    public void checkHomePage(String url) {
        assertTrue("Должны вернуться на главную страницу",
                isHomePageDisplayed(url));
    }
}