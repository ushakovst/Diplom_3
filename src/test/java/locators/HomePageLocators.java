package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Ожидание успешной авторизации")
    public void waitForUrlsContains(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    @Step("Переход в личный кабинет")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }

    @Step("Проверка отображения главной страницы")
    public boolean isMainPageDisplayed(String url) {
        return driver.getCurrentUrl().equals(url);
    }

    @Step("Ожидание появления кнопки 'Войти в аккаунт'")
    public WebElement waitForLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    @Step("Ожидание появления кнопки 'Личный Кабинет'")
    public WebElement waitForPersonalAccountButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
    }

    @Step("Ожидание появления кнопки 'Оформить заказ'")
    public WebElement waitForOrderButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeAnOrder));
    }
}