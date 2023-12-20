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
    void testPayAllFieldsValidApprovedCard() {
        var startPage = new DashboardPage();
        var payment = startPage.openPaymentPage();
        payment.fillFields(DataHelper.getApprovedCard());
        payment.notificationSuccessIsVisible();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
}
