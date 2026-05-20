package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeVerification = $("[data-test-id=code] input");
    private final SelenideElement buttonVerification = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeVerification.shouldBe(visible);
    }

    public void errorMessageVerificationPage(String expectedText) {
        errorNotification
                .shouldHave(exactText(expectedText))
                .shouldBe(visible);
    }

    public DashboardPage validCodeVerificationPage(String verificationCode) {
        validCode(verificationCode);
        return new DashboardPage();
    }

    public void validCode(String verificationCode) {
        codeVerification.setValue(verificationCode);
        buttonVerification.click();
    }
}
