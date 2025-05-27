package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import org.junit.Test;
import locators.*;
import org.openqa.selenium.WebElement;
import utils.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Epic("Авторизация пользователя")
public class LoginTest extends BaseTest {

    @Test
    @Story("Основной сценарий")
    @Step("Вход через кнопку 'Войти в аккаунт'")
    public void testLoginViaMainButton() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        // исходное состояние - кнопка "Войти в аккаунт"
        WebElement loginButton = homePage.waitForLoginButton();
        assertTrue("Кнопка входа не отображается", loginButton.isDisplayed());
        assertEquals("Неверный текст кнопки до авторизации",
                "Войти в аккаунт", loginButton.getText());

        // переход на страницу авторизации
        loginButton.click();

        // заполнение данных и отправка формы
        loginPage.waitForEmailField().sendKeys(testUser.getEmail());
        loginPage.waitForPasswordField().sendKeys(testUser.getPassword());
        loginPage.waitForSubmitButton().click();

        // проверка изменения состояния кнопки
        WebElement orderButton = homePage.waitForOrderButton();
        assertTrue("Кнопка оформления заказа не отображается",
                orderButton.isDisplayed());
        assertEquals("Неверный текст кнопки после авторизации",
                "Оформить заказ", orderButton.getText());
    }

    @Test
    @Story("Альтернативные пути")
    @Step("Вход через личный кабинет")
    public void testLoginViaPersonalAccount() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        // исходное состояние - кнопка "личный кабинет"
        assertTrue("Главная страница не загружена", homePage.isMainPageDisplayed(url));
        WebElement personalAccountButton = homePage.waitForPersonalAccountButton();

        // переход на страницу авторизации
        personalAccountButton.click();
        assertTrue("Не произошел переход на страницу авторизации",
                loginPage.isPageOpened());

        // заполнение данных и отправка формы
        loginPage.waitForEmailField().sendKeys(testUser.getEmail());
        loginPage.waitForPasswordField().sendKeys(testUser.getPassword());
        loginPage.waitForSubmitButton().click();

        // проверка изменения состояния кнопки
        WebElement orderButton = homePage.waitForOrderButton();
        assertTrue("Кнопка оформления заказа не отображается",
                orderButton.isDisplayed());
        assertEquals("Неверный текст кнопки после авторизации",
                "Оформить заказ", orderButton.getText());
    }

    @Test
    @Story("Альтернативные пути")
    @Step("Вход через кнопку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);

        // переход на страницу авторизации через главную кнопку
        homePage.waitForLoginButton().click();

        // переход к форме регистрации
        loginPage.waitForRegistrationButton().click();

        // возврат на форму авторизации через кнопку "Войти"
        registrationPage.waitForLoginLink().click();

        // заполнение данных и авторизация
        loginPage.waitForEmailField().sendKeys(testUser.getEmail());
        loginPage.waitForPasswordField().sendKeys(testUser.getPassword());
        loginPage.waitForSubmitButton().click();

        // проверка успешной авторизации через главную страницу
        WebElement orderButton = homePage.waitForOrderButton();
        assertTrue("Кнопка 'Оформить заказ' не отображается", orderButton.isDisplayed());
        assertEquals("Неверный текст кнопки после авторизации",
                "Оформить заказ", orderButton.getText());
    }

    @Test
    @Story("Альтернативные пути")
    @Step("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaRecoverPasswordForm() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);
        ForgotPasswordPageLocators forgotPasswordPage = new ForgotPasswordPageLocators(driver);

        // переход на страницу авторизации через главную кнопку
        homePage.waitForLoginButton().click();

        // переход к форме восстановления пароля
        loginPage.waitForRecoverButton().click();

        // возврат на форму авторизации через кнопку "Войти"
        forgotPasswordPage.waitForLoginLink().click();

        // заполнение данных и авторизация
        loginPage.waitForEmailField().sendKeys(testUser.getEmail());
        loginPage.waitForPasswordField().sendKeys(testUser.getPassword());
        loginPage.waitForSubmitButton().click();

        // проверка успешной авторизации через главную страницу
        WebElement orderButton = homePage.waitForOrderButton();
        assertTrue("Кнопка 'Оформить заказ' не отображается", orderButton.isDisplayed());
        assertEquals("Неверный текст кнопки после авторизации",
                "Оформить заказ", orderButton.getText());
    }
}