package tests;

import api.ApiClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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
        // инициализация драйвера
        driver = BrowserFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get(url);

        // создание пользователя через API
        UserData testUser = UserGenerator.generateRandomUser();
        Response response = ApiClient.createUser(testUser);

        // авторизация
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
    @Story("Личный кабинет")
    @Step("Переход в личный кабинет авторизованного пользователя")
    public void testNavigateToPersonalAccount() {
        HomePageLocators homePage = new HomePageLocators(driver);
        ProfilePageLocators profilePage = new ProfilePageLocators(driver);

        homePage.clickPersonalAccountButton();

        assertTrue("Страница профиля не открылась",
                profilePage.isPageOpened());
    }

    @Test
    @Story("Конструктор")
    @Step("Возврат в конструктор из личного кабинета")
    public void testNavigateBackToConstructor() {
        HomePageLocators homePage = new HomePageLocators(driver);
        homePage.clickPersonalAccountButton();

        ProfilePageLocators profilePage = new ProfilePageLocators(driver);
        profilePage.clickConstructorLink();

        assertTrue("Должны вернуться на главную страницу",
                homePage.isMainPageDisplayed(url));
    }

    @Test
    @Story("Логотип")
    @Step("Возврат через логотип из личного кабинета")
    public void testNavigateViaLogo() {
        HomePageLocators homePage = new HomePageLocators(driver);
        homePage.clickPersonalAccountButton();

        ProfilePageLocators profilePage = new ProfilePageLocators(driver);
        profilePage.clickConstructorLogo();

        assertTrue("Должны вернуться на главную страницу",
                homePage.isMainPageDisplayed(url));
    }
}