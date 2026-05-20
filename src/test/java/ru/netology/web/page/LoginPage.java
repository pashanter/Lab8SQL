package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private SelenideElement loginUser = $("[data-test-id = 'login'] input");
    private SelenideElement password = $("[data-test-id = 'password'] input");
    private SelenideElement loginButton = $("[data-test-id = 'action-login']");

    public LoginPage() {
        loginUser.should(visible);
    }

    public void verifyErrorNotification(String expectedText) {
        errorNotification
                .should(Condition.text(expectedText))
                .shouldBe(visible);
    }

    public void login(DataHelper.AuthInfo info) {
        loginUser.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void validBadLogin(DataHelper.AuthInfo info, String expectedText) {
        login(info);
        verifyErrorNotification(expectedText);
        cleanFields();
    }

    public void cleanFields() {
        loginUser.press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE);
        password.press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE);
    }
}
