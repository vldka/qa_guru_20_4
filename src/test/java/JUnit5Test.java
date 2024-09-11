import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JUnit5Test {

    @BeforeAll
    static void openPage() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterAll
    static void closeWindow() {
        WebDriverRunner.closeWindow();
    }

    @Test
    void readTestForm() {
        //Действия
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("""
        @ExtendWith({SoftAssertsExtension.class})
        class Tests {
            @Test
            void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");

                $("#first").should(visible).click();
                $("#second").should(visible).click();
            }
        }"""));
    }

}
