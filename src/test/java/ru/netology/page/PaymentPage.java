package ru.netology.page;

import ru.netology.data.Card;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement heading = $$("h3").find(text("Оплата по карте"));
    private SelenideElement cardNumberField =  $$(".input__inner").findBy(text("Номер карты")).$(".input__control");
    private SelenideElement monthField = $$(".input__inner").findBy(text("Месяц")).$(".input__control");
    private SelenideElement yearField = $$(".input__inner").findBy(text("Год")).$(".input__control");
    private SelenideElement holderField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvvField = $("[placeholder='999']");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private SelenideElement validationMessageField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement invalidFormatMessageField = $(byText("Неверный формат"));
    private SelenideElement invalidCharMessageField = $(byText("Поле содержит недопустимые символы"));
    private SelenideElement invalidExpirationMessageField = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredMessageField = $(byText("Истёк срок действия карты"));

    public PaymentPage() {
        heading.shouldBe(visible);
    }

    public void fillFields(Card card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void clearFields() {
        cardNumberField.doubleClick().sendKeys(Keys.BACK_SPACE);
        monthField.doubleClick().sendKeys(Keys.BACK_SPACE);
        yearField.doubleClick().sendKeys(Keys.BACK_SPACE);
        holderField.doubleClick().sendKeys(Keys.BACK_SPACE);
        cvvField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
    public PaymentPage clear() {
        clearFields();
        return new PaymentPage();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void notificationSuccess() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void notificationError() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void validationMessageEmptyField() {
        validationMessageField.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void validationMessageInvalidFormatField() {
        invalidFormatMessageField.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void validationMessageInvalidChar() {
        invalidCharMessageField.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void validationMessageCardExpiredMonth() {
        invalidExpirationMessageField.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void validationMessageCardExpiredYear() {
        cardExpiredMessageField.shouldBe(visible, Duration.ofSeconds(10));
    }

}