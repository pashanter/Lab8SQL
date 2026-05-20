package ru.netology.web.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

public class VisiblePageTest {

    public LoginPage loginPage;

    @AfterAll
    static void cleanTables() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }


    @Test
    void PositiveTest() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validCodeVerificationPage(verificationCode.getCode());
    }

    @Test
    void inputInvalidPassword3Times() {
        var authInfo = DataHelper.getAuthInfoBadPassword();

        for (int i = 0; i < 3; i++) {
            loginPage.validBadLogin(authInfo, "Ошибка! Неверно указан логин или пароль");

        }
        loginPage.validBadLogin(authInfo,"Ошибка! Пользователь заблокирован");
    }
}



