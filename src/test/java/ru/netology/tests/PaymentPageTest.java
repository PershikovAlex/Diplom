package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import org.junit.jupiter.api.*;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentPageTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {

        open(url);
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDatabase();
    }

    @Test
    @DisplayName("1.1. Оплата тура валидной картой со статусом 'APPROVED'")
    void testPayAllFieldsValidApprovedCardNumber() {
        var dashboarpagePage = new DashboardPage();
        var payment = dashboarpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getApprovedCard());
        payment.notificationSuccessIsVisible();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("1.2. Оплата тура невалидной картой с статусом DECLINED")
    void testPayAllFieldsValidDeclinedCardNumber() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getDeclinedCard());
        payment.notificationErrorIsVisible();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.1. Оплата тура по несуществующей карте.")
    void testPayAllFieldsValidRandomCardNumber() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getInvalidCardNumberRandom());
        payment.notificationErrorIsVisible();
        assertEquals(null, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.2. Оплата тура картой с истекшим сроком действия(месяц).")
    void testPayAllFieldsValidExpiredMonth() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getLastMonth());
        payment.validationMessageCardExpired();
        assertEquals(null, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.3. Оплата тура картой с истекшим сроком действия(год).")
    void testPayAllFieldsValidExpiredYear() {
        var dashboadrpagePage = new DashboardPage();
        var payment = dashboadrpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getLastYear());
        payment.validationMessageCardExpired();
        assertEquals(null, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("2.4. Оплата тура картой с 15ю символами.")
    void testPay15DigitsCardNumber() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.get15DigitsCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.5. Ввод номера карты с 17ю символами.")
    void testPay17DigitsCardNumber() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.get17DigitsCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.6. Оплата тура, если поле НОМЕР КАРТЫ пустое.")
    void testPayAllFieldsValidCardNumberNull() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getEmptyCardNumber());
        payment.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.7. Ввод номера карты с буквенным символом.")
    void testPayAllFieldsValidLiteralCharCardNumber() {
        var dashboardpagePage = new DashboardPage();
        var payment = dashboardpagePage.openPaymentPage();
        payment.fillFields(DataHelper.getLiteralCharCardNumber());
        payment.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.8. Ввод номера карты со спецсимволом.")
    void testPayAllFieldsValidSpecCharCardNumber() {

    }

    @Test
    @DisplayName("2.9. Ввод в поле МЕСЯЦ 00.")
    void testPayAllFieldsValidZeroMonth() {

    }

    @Test
    @DisplayName("2.10. Оставить поле МЕСЯЦ пустым.")
    void testPayAllFieldsValidEmptyMonth() {

    }

    @Test
    @DisplayName("2.11. Ввод в поле МЕСЯЦ буквенного символа.")
    void testPayAllFieldsValidLiteralCharMonth() {

    }

    @Test
    @DisplayName("2.12. Ввод в поле месяц МЕСЯЦ спецсимвола.")
    void testPayAllFieldsValidSpecCharMonth() {

    }

    @Test
    @DisplayName("2.13. Оставить поле ГОД пустым.")
    void testPayAllFieldsValidEmptyYear() {

    }

    @Test
    @DisplayName("2.14. Ввод в поле ГОД буквенного символа.")
    void testPayAllFieldsValidLiteralCharYear() {

    }

    @Test
    @DisplayName("2.15. Ввод в поле месяц ГОД спецсимвола.")
    void testPayAllFieldsValidSpecCharYear() {

    }

    @Test
    @DisplayName("2.16. Ввод в поле ГОД значения +6 лет от текущего года.")
    void testPayAllFieldsValidPlusSixYear() {

    }

    @Test
    @DisplayName("2.17. Оставить поле ВЛАДЕЛЕЦ пустым.")
    void testPayAllFieldsValidEmptyHolder() {

    }

    @Test
    @DisplayName("2.18. Ввод в поле ВЛАДЕЛЕЦ цифр.")
    void testPayAllFieldsValidDigitsHolder() {

    }

    @Test
    @DisplayName("2.19. Ввод в поле Владелец спецсимвола.")
    void testPayAllFieldsValidSpecCharHolder() {

    }

    @Test
    @DisplayName("2.20. Ввод в поле ВЛАДЕЛЕЦ фамилии и имени без пробела.")
    void testPayAllFieldsValidWithoutSpaceHolder() {

    }

    @Test
    @DisplayName("2.21. Поставить пробел перед вводом значения в поле ВЛАДЕЛЕЦ.")
    void testPayAllFieldsValidSpaceOverHolder() {

    }

    @Test
    @DisplayName("2.22. Поставить пробел после ввода значения в поле ВЛАДЕЛЕЦ.")
    void testPayAllFieldsValidSpaceBehindHolder() {

    }

    @Test
    @DisplayName("2.23. Оставить поле CVC/CVV пустым.")
    void testPayAllFieldsValidEmptyCVV() {

    }

    @Test
    @DisplayName("2.24. Ввод в поле CVC/CVV 1 цифры.")
    void testPayAllFieldsValidOneDigitsCVV() {

    }

    @Test
    @DisplayName("2.25. Ввод в поле CVC/CVV 2х цифр.")
    void testPayAllFieldsValidTwoDigitsCVV() {

    }

    @Test
    @DisplayName("2.26. Ввод в поле CVC/CVV буквенного символа.")
    void testPayAllFieldsValidLiteralCharCVV() {

    }

    @Test
    @DisplayName("2.27. Ввод в поле CVC/CVV спецсимвола.")
    void testPayAllFieldsValidSpecCharCVV() {

    }

    @Test
    @DisplayName("2.28. Оплата тура с пустыми полями.")
    void testPayAllFieldsEmpty() {

    }
}
