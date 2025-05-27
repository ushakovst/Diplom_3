package tests;

import api.ApiClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.ProfilePageLocators;
import models.UserData;
import org.junit.Before;
import org.junit.Test;
import utils.BaseTest;
import utils.BrowserFactory;
import utils.UserGenerator;

import static org.junit.Assert.*;

@Epic("Авторизация")
public class LogoutTest extends BaseTest {

    @Override
    @Before
    public void setUp() throws Exception {
        // инициализация драйвера
        driver = BrowserFactory.createDriver("chrome");
        driver.manage().window().maximize();

        // создание пользователя через API
        UserData testUser = UserGenerator.generateRandomUser();
        Response response = ApiClient.createUser(testUser);

        // авторизация
        driver.get(url);
        HomePageLocators homePage = new HomePageLocators(driver);
        homePage.clickLoginButton();

        LoginPageLocators loginPage = new LoginPageLocators(driver);
        loginPage.enterEmail(testUser.getEmail());
        loginPage.enterPassword(testUser.getPassword());
        loginPage.clickLoginButton();

        // ожидание успешной авторизации
        homePage.waitForUrlsContains(url);
    }

    @Test
    @Story("Выход из системы")
    @Step("Успешный выход из аккаунта по кнопке 'Выйти'")
    public void testUserLogout() {
        HomePageLocators homePage = new HomePageLocators(driver);

        homePage.clickPersonalAccountButton();

        //ожидание и клик по кнопке выхода
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);
        profilePage.clickLogoutButton();

        // ожидание перехода на страницу логина
        LoginPageLocators loginPage = new LoginPageLocators(driver);
        loginPage.waitLoginPage();

        //проверка страницы логина
        assertTrue("Должны быть перенаправлены на страницу логина",
                loginPage.isPageOpened());

        // Проверка что выход действительно выполнен
        driver.navigate().back();
        assertFalse("Не должны иметь доступа к профилю после выхода",
                profilePage.isPageOpened());
    }
}