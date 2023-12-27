package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
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
    @DisplayName("1.1. Оплата тура в кредит валидной картой со статусом 'APPROVED'")
    void testCreditAllFieldsValidApprovedCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getApprovedCard());
        credit.notificationSuccess();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("1.2. Оплата тура в кредит невалидной картой с статусом DECLINED")
    void testCreditAllFieldsValidDeclinedCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getDeclinedCard());
        credit.notificationError();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("2.1. Оплата тура в кредит по несуществующей карте.")
    void testCreditAllFieldsValidRandomCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidCardNumberRandom());
        credit.notificationError();
        assertEquals(null, SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("2.2. Оплата тура в кредит картой с истекшим сроком действия(месяц).")
    void testCreditAllFieldsValidExpiredMonth() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getLastMonth());
        credit.validationMessageCardExpiredMonth();
    }

    @Test
    @DisplayName("2.3. Оплата тура в кредит картой с истекшим сроком действия(год).")
    void testCreditAllFieldsValidExpiredYear() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getLastYear());
        credit.validationMessageCardExpiredYear();
    }

    @Test
    @DisplayName("2.4. Оплата тура в кредит картой с 15ю символами.")
    void testCredit15DigitsCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.get15DigitsCardNumber());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.5. Ввод номера карты с 17ю символами.")
    void testCredit17DigitsCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.get17DigitsCardNumber());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.6. Оплата тура в кредит, если поле НОМЕР КАРТЫ пустое.")
    void testCreditAllFieldsValidCardNumberNull() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getEmptyCardNumber());
        credit.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.7. Ввод номера карты с буквенным символом.")
    void testCreditAllFieldsValidLiteralCharCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getLiteralCharCardNumber());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.8. Ввод номера карты со спецсимволом.")
    void testCreditAllFieldsValidSpecCharCardNumber() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getSpecCharCardNumber());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.9. Ввод в поле МЕСЯЦ 00.")
    void testCreditAllFieldsValidZeroMonth() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getZeroMonth());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.10. Оставить поле МЕСЯЦ пустым.")
    void testCreditAllFieldsValidEmptyMonth() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getEmptyMonth());
        credit.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.11. Ввод в поле МЕСЯЦ буквенного символа.")
    void testCreditAllFieldsValidLiteralCharMonth() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getLiteralCharMonth());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.12. Ввод в поле месяц МЕСЯЦ спецсимвола.")
    void testCreditAllFieldsValidSpecCharMonth() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getSpecCharMonth());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.13. Оставить поле ГОД пустым.")
    void testCreditAllFieldsValidEmptyYear() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getEmptyYear());
        credit.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.14. Ввод в поле ГОД буквенного символа.")
    void testCreditAllFieldsValidLiteralCharYear() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getLiteralCharYear());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.15. Ввод в поле месяц ГОД спецсимвола.")
    void testCreditAllFieldsValidSpecCharYear() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getSpecCharYear());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.16. Ввод в поле ГОД значения +6 лет от текущего года.")
    void testCreditAllFieldsValidPlusSixYear() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidYearPlus6());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.17. Оставить поле ВЛАДЕЛЕЦ пустым.")
    void testCreditAllFieldsValidEmptyHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getEmptyHolder());
        credit.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.18. Ввод в поле ВЛАДЕЛЕЦ цифр.")
    void testCreditAllFieldsValidDigitsHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidHolderDigits());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.19. Ввод в поле Владелец спецсимвола.")
    void testCreditAllFieldsValidSpecCharHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidHolderSpecChar());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.20. Ввод в поле ВЛАДЕЛЕЦ фамилии и имени без пробела.")
    void testCreditAllFieldsValidWithoutSpaceHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidHolderOneName());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.21. Поставить пробел перед вводом значения в поле ВЛАДЕЛЕЦ.")
    void testCreditAllFieldsValidSpaceOverHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidHolderSpaceOverHolder());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.22. Поставить пробел после ввода значения в поле ВЛАДЕЛЕЦ.")
    void testCreditAllFieldsValidSpaceBehindHolder() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidHolderSpaceAfterHolder());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.23. Оставить поле CVC/CVV пустым.")
    void testCreditAllFieldsValidEmptyCVV() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getEmptyCVV());
        credit.validationMessageEmptyField();
    }

    @Test
    @DisplayName("2.24. Ввод в поле CVC/CVV 1 цифры.")
    void testCreditAllFieldsValidOneDigitsCVV() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getOneNumberCVV());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.25. Ввод в поле CVC/CVV 2х цифр.")
    void testCreditAllFieldsValidTwoDigitsCVV() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getTwoNumberCVV());
        credit.validationMessageInvalidFormatField();
    }

    @Test
    @DisplayName("2.26. Ввод в поле CVC/CVV буквенного символа.")
    void testCreditAllFieldsValidLiteralCharCVV() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidCVVLiteralChar());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.27. Ввод в поле CVC/CVV спецсимвола.")
    void testCreditAllFieldsValidSpecCharCVV() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.fillFields(DataHelper.getInvalidCVVSpecChar());
        credit.validationMessageInvalidChar();
    }

    @Test
    @DisplayName("2.28. Оплата тура в кредит с пустыми полями.")
    void testCreditAllFieldsEmpty() {
        var dashboardPage = new DashboardPage();
        var credit = dashboardPage.openCreditPage();
        credit.clearFields();
        credit.clickContinueButton();
        credit.validationMessageInvalidFormatField();
        credit.validationMessageEmptyField();
    }
}