package allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу")
    public static void openMainPage() {
        open("https://github.com");
    }

    @Step("Вводим в поисковую строку названия репозитория {repo}")
    public static void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").pressEnter();
    }

    @Step("Кликаем по линку репозитория {repo} в списке найденных ")
    public static void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Нажимаем на таб")
    public static void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем отображение issue с номером {issue}")
    public static void shouldSeeIssuesWithLink(String issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }


}
