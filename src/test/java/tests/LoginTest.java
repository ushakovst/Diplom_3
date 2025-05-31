package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import locators.*;
import utils.BaseTest;

@Epic("Авторизация пользователя")
public class LoginTest extends BaseTest {

    @Test
    @Story("Основной сценарий")
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    @Description("Проверка авторизации пользователя через кнопку 'Войти в аккаунт'")
    public void testLoginViaMainButton() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        // исходное состояние - кнопка "Войти в аккаунт"
        homePage.verifyLoginButtonVisible();

        // переход на страницу авторизации
        homePage.clickLoginButton();

        // заполнение данных и отправка формы
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // проверка изменения состояния кнопки
        homePage.verifyOrderButtonVisible();
    }

    @Test
    @Story("Альтернативные пути")
    @DisplayName("Вход через личный кабинет")
    @Description("Проверка авторизации пользователя через кнопку 'Личный Кабинет'")
    public void testLoginViaPersonalAccount() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        // исходное состояние - кнопка "личный кабинет"
        homePage.verifyPersonalAccountButtonVisible();

        // переход на страницу авторизации
        homePage.clickPersonalAccountButton();

        // заполнение данных и отправка формы
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // проверка изменения состояния кнопки
        homePage.verifyOrderButtonVisible();
    }

    @Test
    @Story("Альтернативные пути")
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка авторизации пользователя через кнопку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);

        // переход на страницу авторизации через главную кнопку
        homePage.verifyLoginButtonVisible();
        homePage.clickLoginButton();

        // переход к форме регистрации
        loginPage.clickRegistrationButton();

        // возврат на форму авторизации через кнопку "Войти"
        registrationPage.clickLoginButton();

        // заполнение данных и авторизация
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // проверка успешной авторизации через главную страницу
        homePage.verifyOrderButtonVisible();
    }

    @Test
    @Story("Альтернативные пути")
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации пользователя через кнопку в форме восстановления пароля")
    public void testLoginViaRecoverPasswordForm() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        ForgotPasswordPageLocators forgotPasswordPage = new ForgotPasswordPageLocators(driver);

        homePage.verifyLoginButtonVisible();

        // переход на страницу авторизации через главную кнопку
        homePage.clickLoginButton();

        // переход к форме восстановления пароля
        loginPage.clickRecoverButton();

        // возврат на форму авторизации через кнопку "Войти"
        forgotPasswordPage.clickLoginButton();

        // заполнение данных и авторизация
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // проверка успешной авторизации через главную страницу
        homePage.verifyOrderButtonVisible();
    }
}