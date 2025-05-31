package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import locators.ConstructorPageLocators;
import utils.BaseTest;
import java.util.Arrays;
import java.util.Collection;

@Epic("Конструктор бургеров")
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final String sectionName;

    public ConstructorTest(String sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "Тестовые данные: sectionName={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        });
    }

    @Test
    @Story("Навигация по разделам конструктора")
    @DisplayName("Проверка работы перехода по разделам")
    @Description("Проверка навигации по разделам конструктора")
    public void testSectionActivation() {
        ConstructorPageLocators constructorPage = new ConstructorPageLocators(driver);

        if (sectionName.equals("Булки")) {
            // Проверка возврата к булкам после выбора другого раздела
            constructorPage.selectSection("Соусы");
            constructorPage.selectSection("Булки");
        } else {
            constructorPage.selectSection(sectionName);
        }

        //проверка активности секции
        constructorPage.checkSectionActive(sectionName);
    }
}