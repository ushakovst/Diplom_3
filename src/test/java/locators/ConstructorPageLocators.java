package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorPageLocators {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // локаторы селекторов
    private final By bunsSection = By.xpath(".//div[contains(@class, 'tab_tab__')]//span[text()='Булки']/ancestor::div[1]");
    private final By saucesSection = By.xpath(".//div[contains(@class, 'tab_tab__')]//span[text()='Соусы']/ancestor::div[1]");
    private final By fillingsSection = By.xpath(".//div[contains(@class, 'tab_tab__')]//span[text()='Начинки']/ancestor::div[1]");

    public ConstructorPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Выбор селектора")
    public void selectSection(String sectionName) {
        By locator = getSectionLocator(sectionName);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        wait.until(ExpectedConditions.attributeContains(locator, "class", "current"));
    }

    @Step("Геттер селектора")
    private By getSectionLocator(String sectionName) {
        switch (sectionName.toLowerCase()) {
            case "булки": return bunsSection;
            case "соусы": return saucesSection;
            case "начинки": return fillingsSection;
            default: throw new IllegalArgumentException("Неизвестный раздел: " + sectionName);
        }
    }

    @Step("Проверка активности селектора")
    public boolean isSectionActive(String sectionName) {
        try{
            By locator = getSectionLocator(sectionName); // метод для получения локатора
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getAttribute("class").contains("current");
        } catch (Exception e) {
            return false;
            }
        }
}