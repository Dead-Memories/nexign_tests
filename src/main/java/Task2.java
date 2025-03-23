import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class Task2 {

    @BeforeAll
    public static void setup() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void countNexignMentions() {
        open("https://nexign.com/ru");

        String pageText = $("body").getText();

        Pattern pattern = Pattern.compile("\\bNexign\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(pageText);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        System.out.println("Количество упоминаний слова 'Nexign': " + count);
    }
}
