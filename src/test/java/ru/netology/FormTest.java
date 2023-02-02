package ru.netology;

import data.UserData;
import data.DataGenerator;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @Test
    void successSignIn() {
        UserData user = DataGenerator.generateValidActive();
        open("http://localhost:9999");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("h2.heading").shouldHave(text("Личный кабинет"));
    }

    @Test
    void blockedSignIn() {
        UserData user = DataGenerator.generateValidBlocked();
        open("http://localhost:9999");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void failLogin() {
        UserData user = DataGenerator.generateInvalidLogin();
        open("http://localhost:9999");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка!"))
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void failPassword() {
        UserData user = DataGenerator.generateInvalidPassword();
        open("http://localhost:9999");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка!"))
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void PasswordAbsent() {
        UserData user = DataGenerator.generateValidActive();
        open("http://localhost:9999");
        clearBrowserCookies();
        clearBrowserLocalStorage();
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=password]").shouldHave(text("Поле обязательно для заполнения"));
    }

}