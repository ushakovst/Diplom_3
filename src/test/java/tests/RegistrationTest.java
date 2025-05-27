package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.*;
import models.UserData;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import locators.RegistrationPageLocators;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;
import utils.BrowserFactory;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.*;

@Epic("Регистрация пользователя")
public class RegistrationTest extends BaseTest {

    @Override
    @Before
    public void setUp() throws Exception {
        // Инициализация драйвера
        driver = BrowserFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    @Story("Успешная регистрация")
    @Step("Регистрация с валидными данными")
    public void testSuccessfulRegistration() {
        HomePageLocators homePage = new HomePageLocators(driver);
        homePage.clickLoginButton();

        LoginPageLocators loginPage = new LoginPageLocators(driver);
        loginPage.clickRegistration();

        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);
        UserData newUser = UserGenerator.generateRandomUser();

        registrationPage.enterRegisterName(newUser.getName());
        registrationPage.enterRegisterEmail(newUser.getEmail());
        registrationPage.enterRegisterPassword(newUser.getPassword());

        registrationPage.clickRegisterButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/login"));

        assertTrue("Должны быть перенаправлены на страницу логина",
                loginPage.isPageOpened());
    }

    @Test
    @Story("Валидация пароля")
    @Step("Регистрация с коротким паролем (5 символов)")
    public void testRegistrationWithShortPassword() {
        HomePageLocators mainPage = new HomePageLocators(driver);
        mainPage.clickLoginButton();

        LoginPageLocators loginPage = new LoginPageLocators(driver);
        loginPage.clickRegistration();

        RegistrationPageLocators registrationPage = new RegistrationPageLocators(driver);
        UserData invalidUser = UserGenerator.generateUserWithIncorrectPass();

        registrationPage.enterRegisterName(invalidUser.getName());
        registrationPage.enterRegisterEmail(invalidUser.getEmail());
        registrationPage.enterRegisterPassword(invalidUser.getPassword());

        registrationPage.clickRegisterButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                registrationPage.getPasswordErrorLocator()));

        assertTrue("Должна отображаться ошибка пароля",
                registrationPage.isPasswordErrorDisplayed());
    }
}