package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Paths;

//созжаем фабрику для выбора одного из двух браузеров
public class BrowserFactory {
    //далее необходимо указать путь, который ведет к файлу yandexdriver.exe
    private static final String YANDEX_DRIVER_PATH = "D:/Java_System/Selenium/YandexDriver/yandexdriver.exe";
    private static final String YANDEX_BROWSER_PATH =
            Paths.get(System.getenv("LOCALAPPDATA"),
                            "Yandex",
                            "YandexBrowser",
                            "Application",
                            "browser.exe")
                    .toString();

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "yandex":
                return setupYandexDriver();

            case "chrome":
            default:
                // настройка
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--window-size=1920,1080");
                return new ChromeDriver(chromeOptions);
        }
    }
    private static WebDriver setupYandexDriver() {
        // установка пути к драйверу
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);

        // настройка
        ChromeOptions options = new ChromeOptions();
        options.setBinary(YANDEX_BROWSER_PATH);
        options.addArguments(
                "--window-size=1920,1080",
                "--remote-allow-origins=*",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        return new ChromeDriver(options);
    }
}