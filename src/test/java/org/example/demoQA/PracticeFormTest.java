package org.example.demoQA;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void inputFullFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //registration Form
        $("#firstName").setValue("Igor");
        $("#lastName").setValue("Tyupanov");
        $("#userEmail").setValue("ityupanov@gmail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9999999999");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1998");
        $(".react-datepicker__day--015").click();

        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/img.PNG");

        $("#currentAddress").setValue("Ryamo, polyma, 1, 123");
        $("#state").click();
        $("#state").$(byText("Rajasthan")).click();
        $("#city").click();
        $("#city").$(byText("Jaiselmer")).click();
        $("#submit").click();

        //submitting form
        $(".modal-dialog").should(appear);
        $(".table-responsive").$(byText("Student Name"))
                .sibling(0).shouldHave(text("Igor Tyupanov"));
        $(".table-responsive").$(byText("Student Email"))
                .sibling(0).shouldHave(text("ityupanov@gmail.ru"));
        $(".table-responsive").$(byText("Gender"))
                .sibling(0).shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile"))
                .sibling(0).shouldHave(text("9999999999"));
        $(".table-responsive").$(byText("Date of Birth"))
                .sibling(0).shouldHave(text("15 December,1998"));
        $(".table-responsive").$(byText("Subjects"))
                .sibling(0).shouldHave(text("Computer Science"));
        $(".table-responsive").$(byText("Hobbies"))
                .sibling(0).shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture"))
                .sibling(0).shouldHave(text("img.PNG"));
        $(".table-responsive").$(byText("Address"))
                .sibling(0).shouldHave(text("Ryamo, polyma, 1, 123"));
        $(".table-responsive").$(byText("State and City"))
                .sibling(0).shouldHave(text("Rajasthan Jaiselmer"));

        $("#closeLargeModal").click();
    }
}
