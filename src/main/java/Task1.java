import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Task1 {

    @BeforeClass
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
    }

    @AfterClass
    public static void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void testNavigationToNexignNord() {
        // 1. Открыть сайт
        open("https://nexign.com/ru");

        // 2. Перейти в раздел "Продукты и решения"
        $(By.xpath("//span[contains(text(), 'Продукты и решения')]//..")).click();

        // 3. Перейти в раздел "Инструменты для ИТ-команд"
        $(By.xpath("//span[contains(text(), 'Инструменты для ИТ-команд')]//..")).click();

        // 4. Перейти в раздел "Nexign Nord"
        $(By.xpath("(//a[contains(text(), 'Nexign Nord')])[1]")).click();

        // Проверка успешного перехода
        String expectedTitle = "Система управления базами данных на платформе PostgreSQL";
        assertThat(title(), containsString(expectedTitle));
    }
}
