package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static java.nio.channels.Selector.open;

public class RegistrationFormTests extends TestBase {

    @Test
    void successfulFillFormTest () {
        Selenide.open ("/automation-practice-form");
        $("#firstName").val("Александра");
        $("#lastName").val("Иванова");
        $("#userEmail").val("aleks@ivanova.com");
        $("#gender-radio-2").selectRadio("Female");
        $("#userNumber").val("7123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1996");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").val("chemistry").pressEnter();
        $("#hobbiesWrapper").$("#hobbies-checkbox-2").click();
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

}
