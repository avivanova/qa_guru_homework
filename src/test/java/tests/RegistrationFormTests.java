package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.selector.ByDeepShadow.cssSelector;
import static java.nio.channels.Selector.open;

public class RegistrationFormTests extends TestBase {

    @Test
    void submitFullFormTest () {
        Selenide.open ("/automation-practice-form");

        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """);

        $("#firstName").val("Александра");
        $("#lastName").val("Иванова");
        $("#userEmail").val("aleks@ivanova.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").val("7123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").val("chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("cat.jpg");
        $("#currentAddress").val("Haryana, Panipat, 5");
        $("#state").click();
        $("#react-select-3-input").val("Haryana").pressEnter();
        $("#city").click();
        $("#react-select-4-input").val("Panipat").pressEnter();
        $("#submit").click();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name Александра Иванова"));
        $(".table-responsive").shouldHave(text("Student Email aleks@ivanova.com"));
        $(".table-responsive").shouldHave(text("Gender Female"));
        $(".table-responsive").shouldHave(text("Mobile 7123456789"));
        $(".table-responsive").shouldHave(text("Date of Birth 15 March,1996"));
        $(".table-responsive").shouldHave(text("Subjects Chemistry"));
        $(".table-responsive").shouldHave(text("Hobbies Reading"));
        $(".table-responsive").shouldHave(text("Picture"));
        $(".table-responsive").shouldHave(text("Address Haryana, Panipat, 5"));
        $(".table-responsive").shouldHave(text("State and City Haryana Panipat"));
        $("#closeLargeModal").click();

    }

    @Test
    void submitRequiredFieldsOnlyTest() {
        Selenide.open("/automation-practice-form");

        $("#firstName").val("Александра");
        $("#lastName").val("Иванова");
        $("#userEmail").val("aleks@ivanova.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").val("7123456789");
        $("#submit").click();
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name Александра Иванова"));
        $(".table-responsive").shouldHave(text("Student Email aleks@ivanova.com"));
        $(".table-responsive").shouldHave(text("Gender Female"));
        $(".table-responsive").shouldHave(text("Mobile 7123456789"));
    }

    @Test
    void submitEmptyFormTest() {
        Selenide.open("/automation-practice-form");

        $("#submit").click();
        $(".modal-title").shouldNotBe(visible);
        $("#firstName:invalid").shouldBe(visible);
    }

    @Test
    void submitInvalidEmailTest() {
        Selenide.open("/automation-practice-form");

        $("#firstName").val("Александра");
        $("#lastName").val("Иванова");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").val("7123456789");
        $("#userEmail").val("://ivanova.com");
        $("#submit").click();
        $(".modal-title").shouldNotBe(visible);
        $("#userEmail:invalid").shouldBe(visible);
    }

    @Test
    void submitTooShortMobileNumberTest() {
        Selenide.open("/automation-practice-form");

        $("#firstName").val("Александра");
        $("#lastName").val("Иванова");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").val("712345678");
        $("#submit").click();
        $(".modal-title").shouldNotBe(visible);
        $("#userNumber:invalid").shouldBe(visible);
    }

    @Test
    void submitMissingRequiredLastNameTest() {
        Selenide.open("/automation-practice-form");

        $("#firstName").val("Александра");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").val("7123456789");
        $("#submit").click();
        $(".modal-title").shouldNotBe(visible);
        $("#lastName:Invalid").shouldBe(visible);
    }


}
