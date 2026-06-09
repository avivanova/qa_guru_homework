package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBox extends TestBase {

    @Test
    void successfulFillFormTest () {
        open("/text-box");

        $("#userName").val("Aleksandra");
        $("#userEmail").val("aleks@ivanova.com");
        $("#currentAddress").val("Москва, ул.Новая, 1");
        $("#permanentAddress").setValue("Москва, ул.Новая, 2");
        $("#submit").click();

        $("#output #name").shouldHave(text("Aleksandra"));
        $("#output #email").shouldHave(text("aleks@ivanova.com"));
        $("#output #currentAddress").shouldHave(text("Москва, ул.Новая, 1"));
        $("#output #permanentAddress").shouldHave(text("Москва, ул.Новая, 2"));

    }

    @Test
    void submitInvalidEmailTest() {
        open("/text-box");

        $("#userName").val("Aleksandra");
        $("#currentAddress").val("Москва, ул.Новая, 1");
        $("#permanentAddress").setValue("Москва, ул.Новая, 2");
        $("#userEmail").val("aleks@ivanova");
        $("#submit").click();
        $("#output").shouldNotBe(visible);
        $("#userEmail.field-error").shouldBe(visible);
    }
}
