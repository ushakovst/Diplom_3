package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import locators.ConstructorPageLocators;
import utils.BaseTest;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@Epic("Конструктор бургеров")
@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    private final String sectionName;

    public ConstructorTest(String sectionName) {
        this.sectionName = sectionName;
    }

    @Parameterized.Parameters(name = "Проверка раздела")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        });
    }

    @Test
    @Story("Навигация по разделам конструктора")
    public void testSectionActivation() {
        ConstructorPageLocators constructorPage = new ConstructorPageLocators(driver);

        if (sectionName.equals("Булки")) {
            // Проверка возврата к булкам после выбора другого раздела
            constructorPage.selectSection("Соусы");
            constructorPage.selectSection("Булки");
        } else {
            constructorPage.selectSection(sectionName);
        }

        assertTrue("Раздел " + sectionName + " должен быть активен",
                constructorPage.isSectionActive(sectionName));
    }
}