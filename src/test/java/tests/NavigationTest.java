package tests;

import api.ApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import locators.LoginPageLocators;
import models.UserData;
import org.junit.Before;
import org.junit.Test;
import locators.HomePageLocators;
import locators.ProfilePageLocators;
import utils.BaseTest;
import utils.BrowserFactory;
import utils.UserGenerator;

import static org.junit.Assert.*;

@Epic("Навигация")
public class NavigationTest extends BaseTest {

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
    @Story("Личный кабинет")
    @DisplayName("Переход в личный кабинет авторизованного пользователя")
    @Description("Проверка перехода авторизованного пользователя в личный кабинет по кнопке 'Личный Кабинет'")
    public void testNavigateToPersonalAccount() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);

        homePage.clickPersonalAccountButton();

        //проверка страницы профиля
        profilePage.checkProfilePage();
    }

    @Test
    @Story("Конструктор")
    @DisplayName("Возврат в конструктор из личного кабинета")
    @Description("Проверка перехода на главную страницу из личного кабинета при нажатии кнопки 'Конструктор'")
    public void testNavigateBackToConstructor() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);

        homePage.clickPersonalAccountButton();

        profilePage.clickConstructorLink();

        //проверка домашней страницы
        homePage.checkHomePage(url);
    }

    @Test
    @Story("Логотип")
    @DisplayName("Возврат через логотип из личного кабинета")
    @Description("Проверка перехода на главную страницу из личного кабинетапри нажатии на лого")
    public void testNavigateViaLogo() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);

        homePage.clickPersonalAccountButton();

        profilePage.clickConstructorLogo();

        //должны вернуться на главную страницу
        homePage.checkHomePage(url);
    }
}