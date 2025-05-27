package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class ProfilePageLocators {
    private final WebDriver driver;
    protected WebDriverWait wait;

    // локаторы страницы профиля
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private final By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    public ProfilePageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Выход из аккаунта")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Переход в конструктор через ссылку")
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    @Step("Переход в конструктор через логотип")
    public void clickConstructorLogo() {
        driver.findElement(logo).click();
    }


    @Step("Открыта ли страница профиля")
    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.urlContains("/account"));
        } catch (TimeoutException e) {
            return false;
        }
    }
}