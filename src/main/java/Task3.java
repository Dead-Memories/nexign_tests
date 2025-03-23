import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Russian;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class Task3 {

    @Test
    public void checkSpellingOnMainAndSubPages() throws IOException {

        open("https://nexign.com/ru");

        ElementsCollection links = $$("a[href^='https://nexign.com/ru']");

        Set<String> urls = new HashSet<>();
        urls.add("https://nexign.com/ru"); // главная страница

        urls.addAll(
                links.stream()
                        .map(el -> el.attr("href"))
                        .filter(href -> href != null && !href.contains("#"))
                        .limit(5)
                        .collect(Collectors.toSet())
        );

        System.out.println("\n Ссылки для проверки:");
        urls.forEach(System.out::println);

        JLanguageTool langTool = new JLanguageTool(new Russian());

        for (String url : urls) {
            open(url);
            String text = $("body").getText();

            List<RuleMatch> matches = langTool.check(text);
            System.out.println("\n Проверка страницы: " + url);
            if (matches.isEmpty()) {
                System.out.println("Ошибок не найдено.");
            } else {
                for (RuleMatch match : matches) {
                    String errorFragment = text.substring(match.getFromPos(), match.getToPos());
                    System.out.println("Ошибка: " + errorFragment);
                    System.out.println("Подсказка: " + match.getMessage());
                    System.out.println("Предложения: " + match.getSuggestedReplacements());
                }
            }
            System.out.println("--------------------------------------------------");
        }
    }
}
