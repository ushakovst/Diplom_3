package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import models.UserData;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.RegistrationPageLocators;
import org.junit.Before;
import org.junit.Test;
import utils.BaseTest;
import utils.UserGenerator;

@Epic("Регистрация пользователя")
public class RegistrationTest extends BaseTest {

    @Override
    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Story("Успешная регистрация")
    @DisplayName("Регистрация с валидными данными")
    @Description("Проверка регистрации нового пользователя с корректными данными")
    public void testSuccessfulRegistration() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);

        homePage.clickLoginButton();

        loginPage.clickRegistration();

        // регистрация нового пользователя
        UserData newUser = UserGenerator.generateRandomUser();
        registrationPage.registration(newUser.getName(), newUser.getEmail(), newUser.getPassword());

        // должны быть перенаправлены на страницу логина
        loginPage.checkLoginPage();
    }

    @Test
    @Story("Валидация пароля")
    @DisplayName("Регистрация с коротким паролем (5 символов)")
    @Description("Получение ошибки при попытке регистрации нового пользователя с коротким паролем (5 символов)")
    public void testRegistrationWithShortPassword() {
        HomePageLocators homePage = new HomePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);

        homePage.clickLoginButton();

        loginPage.clickRegistration();

        UserData invalidUser = UserGenerator.generateUserWithIncorrectPass();
        registrationPage.registration(invalidUser.getName(), invalidUser.getEmail(), invalidUser.getPassword());

        registrationPage.checkError();
    }
}