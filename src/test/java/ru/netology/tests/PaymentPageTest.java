package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import org.junit.jupiter.api.*;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentPageTest {
    public static String url = System.getProperty("sut.url");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openPage() {

        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDatabase();
    }

    @Test
    @DisplayName("1.1. Оплата тура валидной картой со статусом 'APPROVED'")
    void testPayAllFieldsValidApprovedCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getApprovedCard());
        payment.notificationSuccess();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("1.2. Оплата тура невалидной картой с статусом DECLINED")
    void testPayAllFieldsValidDeclinedCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getDeclinedCard());
        payment.notificationError();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.1. Оплата тура по несуществующей карте.")
    void testPayAllFieldsValidRandomCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidCardNumberRandom());
        payment.notificationError();
        assertEquals(null, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.2. Оплата тура картой с истекшим сроком действия(месяц).")
    void testPayAllFieldsValidExpiredMonth() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getLastMonth());
        payment.validationMessageInvalidExpiration();
    }

    @Test
    @DisplayName("2.3. Оплата тура картой с истекшим сроком действия(год).")
    void testPayAllFieldsValidExpiredYear() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getLastYear());
        payment.validationMessageCardExpired();
    }

    @Test
    @DisplayName("2.4. Оплата тура картой с 15ю символами.")
    void testPay15DigitsCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.get15DigitsCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.5. Ввод номера карты с 17ю символами.")
    void testPay17DigitsCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.get17DigitsCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.6. Оплата тура, если поле НОМЕР КАРТЫ пустое.")
    void testPayAllFieldsValidCardNumberNull() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyCardNumber());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.7. Ввод номера карты с буквенным символом.")
    void testPayAllFieldsValidLiteralCharCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getLiteralCharCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.8. Ввод номера карты со спецсимволом.")
    void testPayAllFieldsValidSpecCharCardNumber() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getSpecCharCardNumber());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.9. Ввод в поле МЕСЯЦ 00.")
    void testPayAllFieldsValidZeroMonth() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getZeroMonth());
        payment.validationMessageInvalidExpiration();
    }

    @Test
    @DisplayName("2.10. Оставить поле МЕСЯЦ пустым.")
    void testPayAllFieldsValidEmptyMonth() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyMonth());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.11. Ввод в поле МЕСЯЦ буквенного символа.")
    void testPayAllFieldsValidLiteralCharMonth() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getLiteralCharMonth());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.12. Ввод в поле месяц МЕСЯЦ спецсимвола.")
    void testPayAllFieldsValidSpecCharMonth() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getSpecCharMonth());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.13. Оставить поле ГОД пустым.")
    void testPayAllFieldsValidEmptyYear() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyYear());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.14. Ввод в поле ГОД буквенного символа.")
    void testPayAllFieldsValidLiteralCharYear() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getLiteralCharYear());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.15. Ввод в поле месяц ГОД спецсимвола.")
    void testPayAllFieldsValidSpecCharYear() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getSpecCharYear());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.16. Ввод в поле ГОД значения +6 лет от текущего года.")
    void testPayAllFieldsValidPlusSixYear() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidYearPlus6());
        payment.validationMessageInvalidExpiration();
    }

    @Test
    @DisplayName("2.17. Оставить поле ВЛАДЕЛЕЦ пустым.")
    void testPayAllFieldsValidEmptyHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyHolder());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.18. Ввод в поле ВЛАДЕЛЕЦ цифр.")
    void testPayAllFieldsValidDigitsHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidHolderDigits());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.19. Ввод в поле Владелец спецсимвола.")
    void testPayAllFieldsValidSpecCharHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidHolderSpecChar());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.20. Ввод в поле ВЛАДЕЛЕЦ фамилии и имени без пробела.")
    void testPayAllFieldsValidWithoutSpaceHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidHolderOneName());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.21. Поставить пробел перед вводом значения в поле ВЛАДЕЛЕЦ.")
    void testPayAllFieldsValidSpaceOverHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidHolderSpaceOverHolder());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.22. Поставить пробел после ввода значения в поле ВЛАДЕЛЕЦ.")
    void testPayAllFieldsValidSpaceBehindHolder() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidHolderSpaceAfterHolder());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.23. Оставить поле CVC/CVV пустым.")
    void testPayAllFieldsValidEmptyCVV() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyCVV());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.24. Ввод в поле CVC/CVV 1 цифры.")
    void testPayAllFieldsValidOneDigitsCVV() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getOneNumberCVV());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.25. Ввод в поле CVC/CVV 2х цифр.")
    void testPayAllFieldsValidTwoDigitsCVV() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getTwoNumberCVV());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.26. Ввод в поле CVC/CVV буквенного символа.")
    void testPayAllFieldsValidLiteralCharCVV() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidCVVLiteralChar());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.27. Ввод в поле CVC/CVV спецсимвола.")
    void testPayAllFieldsValidSpecCharCVV() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidCVVSpecChar());
        payment.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.28. Оплата тура с пустыми полями.")
    void testPayAllFieldsEmpty() {
        var dashboardPage = new DashboardPage();
        var payment = dashboardPage.openPaymentPage();
        payment.clearFields();
        payment.clickContinueButton();
        payment.validationMessageInvalidFormatField();
        payment.validationMessageEmptyField();
    }
}
