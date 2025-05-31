package tests;

import api.ApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.ProfilePageLocators;
import models.UserData;
import org.junit.Before;
import org.junit.Test;
import utils.BaseTest;
import utils.UserGenerator;

@Epic("Авторизация")
public class LogoutTest extends BaseTest {

    @Override
    @Before
    public void setUp() throws Exception {
        // создание пользователя через API
        UserData testUser = UserGenerator.generateRandomUser();
        Response response = ApiClient.createUser(testUser);

        // авторизация
        HomePageLocators homePage = new HomePageLocators(driver);
        homePage.clickLoginButton();
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        // ожидание успешной авторизации
        homePage.waitForUrlsContains(url);
    }

    @Test
    @Story("Выход из системы")
    @DisplayName("Успешный выход из аккаунта по кнопке 'Выйти'")
    @Description("Проверка успешного выхода из аккаунта по кнопке 'Выйти'")
    public void testUserLogout() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        homePage.clickPersonalAccountButton();

        //ожидание и клик по кнопке выхода
        profilePage.clickLogoutButton();

        // ожидание перехода на страницу логина
        loginPage.waitLoginPage();

        //проверка страницы логина
        loginPage.checkLoginPage();
    }

    @Test
    @Story("Выход из системы")
    @DisplayName("Успешный выход из аккаунта по кнопке 'Выйти'")
    @Description("После нажатия кнопки 'Выйти' не возможно вернуться обратно без регистрации")
    public void testUserAccountUnavailable() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);
        LoginPageLocators loginPage = new LoginPageLocators(driver);

        homePage.clickPersonalAccountButton();

        //ожидание и клик по кнопке выхода
        profilePage.clickLogoutButton();

        // ожидание перехода на страницу логина
        loginPage.waitLoginPage();

        // Проверка что выход действительно выполнен
        profilePage.checkProfilePageOut();
    }
}