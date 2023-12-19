package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }
    private static Faker faker = new Faker(new Locale("en"));
    private static String approvedCard() {

        return "4444444444444441";
    }
    private static String declinedCard() {
        return "4444444444444442";
    }

    //positive
    public static Card getApprovedCard() {
        return new Card(approvedCard(),getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card getDeclinedCard() {
        return new Card(declinedCard(),getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    // поле "Номер карты"
    public static Card getNonExistCard() {
        return new Card(getRandomCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card getInvalidCardNumber() {
        return new Card("4444 4444 4444 44",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }
    public static Card getNonExistCardAllZero() {
        return new Card("0000 0000 0000 0000",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card getEmptyFieldCardNumber() {
        return new Card("",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    //поле "Владелец"

    public static Card getEmptyHolderCard() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "", getValidCvv());
    }
    public static Card getInvalidHolderOneNameCard() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "Andrew", getValidCvv());
    }
    public static Card getInvalidHolderNumbersCard() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "111111", getValidCvv());
    }
    public static Card getInvalidHolderSymbolsCard() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "@@@@@@", getValidCvv());
    }

    //поле "Год"
    public static Card getEmptyYear() {
        return new Card(approvedCard(), getValidMonth(), "", getValidHolder(), getValidCvv());
    }
    public static Card getLastYear() {
        return new Card(approvedCard(), getValidMonth(), getPastYear(), getValidHolder(), getValidCvv());
    }
    public static Card getNotComingYear() {
        return new Card(approvedCard(), getValidMonth(), getFutureYear(), getValidHolder(), getValidCvv());
    }

    // поле "Месяц"
    public static Card getInvalidPastMonth() {
        return new Card(approvedCard(), "11", getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getEmptyMonth() {
        return new Card(approvedCard(), "", getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getZeroMonthNowYear() {
        return new Card(approvedCard(), "00", getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getZeroMonthNextYear() {
        return new Card(approvedCard(), "00", getNextYear(), getValidHolder(), getValidCvv());
    }
    public static Card getMonthOver12() {
        return new Card(approvedCard(), "13", getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getOneMonth() {
        return new Card(approvedCard(), "7", getValidYear(), getValidHolder(), getValidCvv());
    }

    //поле "CVC/CVV"
    public static Card getEmptyCVC() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "");
    }
    public static Card getOneNumberCVC() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "1");
    }
    public static Card getTwoNumberCVC() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "12");
    }
    public static Card getZeroNumberCVC() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "000");
    }

    //генераторы данных

    public static String getRandomCardNumber() {
        return faker.business().creditCardNumber();
    }
    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }
    public static String getValidYear() {
        String validYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return validYear;
    }
    public static String getNextYear() {
        String nextYear = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return nextYear;
    }
    public static String getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return pastYear;
    }
    public static String getFutureYear() {
        String futureYear = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return futureYear;
    }
    public static String getValidHolder() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCvv() {
        return faker.number().digits(3);
    }
}
