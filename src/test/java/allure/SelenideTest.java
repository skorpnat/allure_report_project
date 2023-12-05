package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest extends BaseTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE = "80";
    @Test
    @DisplayName("Selenide Test with listener")
    public void testIssueSearch() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $(".search-input").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").pressEnter();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).should(Condition.exist);

    }

    @Test
    @DisplayName("Test with Lambda steps")
    public void testLambdaStepsIssueSearch() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ввод в поисковую строку названия репозитория " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
            $("#query-builder-test").pressEnter();
        });
        step("Выбор репозитория из списка найденных", () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });
        step("Нажатие на таб", () -> {
            $("#issues-tab").click();
        });
        step("Проверка отображения issue с номером " + ISSUE , () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });

    }


}
