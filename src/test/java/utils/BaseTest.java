package utils;

import api.ApiClient;
import io.restassured.response.Response;
import models.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//добавил дополнительный базовый класс, который содержит общий код для открытия и закрытия браузера
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected UserData testUser;
    protected String accessToken;
    protected final String url = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void browserSelect() throws Exception {
        String browser = System.getProperty("browser", "chrome"); //или "yandex", или "chrome"
        System.out.println("Starting tests in: " + browser.toUpperCase());

        driver = BrowserFactory.createDriver(browser);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Явное ожидание
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Before
    public void setUp() throws Exception {
        // cоздание тестового пользователя через API
        testUser = UserGenerator.generateRandomUser();
        Response response = ApiClient.createUser(testUser);
        if (response.statusCode() == 200) {
            this.accessToken = response.path("accessToken");
        }
    }

    @After
    public void tearDown() throws Exception {
        if (accessToken != null) {
            ApiClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}