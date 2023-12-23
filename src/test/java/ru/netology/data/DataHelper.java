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
    public static Card getInvalidCardNumberRandom() {
        return new Card(getRandomCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card get15DigitsCardNumber() {
        return new Card("4444 4444 4444 444",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }
    public static Card get17DigitsCardNumber() {
        return new Card("1234 1234 1234 1234 1",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card getEmptyCardNumber() {
        return new Card("",getValidMonth(),getValidYear(),getValidHolder(),getValidCvv());
    }

    public static Card getLiteralCharCardNumber() {
        return new Card("4444 4444 4444 444a", getValidMonth(), getValidYear(), getValidHolder(), getValidCvv());
    }

    public static Card getSpecCharCardNumber() {
        return new Card("4444 4444 4444 444@", getValidMonth(), getValidYear(), getValidHolder(), getValidCvv());
    }

    //поле "Владелец"

    public static Card getEmptyHolder() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "", getValidCvv());
    }
    public static Card getInvalidHolderOneName() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "Andrew", getValidCvv());
    }
    public static Card getInvalidHolderDigits() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "Andrew1 Brown", getValidCvv());
    }
    public static Card getInvalidHolderSpecChar() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), "@Andrew", getValidCvv());
    }

    //поле "Год"
    public static Card getEmptyYear() {
        return new Card(approvedCard(), getValidMonth(), "", getValidHolder(), getValidCvv());
    }
    public static Card getLastYear() {
        return new Card(approvedCard(), getValidMonth(), getPastYear(), getValidHolder(), getValidCvv());
    }
    public static Card getInvalidYearPlus6() {
        return new Card(approvedCard(), getValidMonth(), getFutureYear(), getValidHolder(), getValidCvv());
    }

    public static Card getLiteralCharYear() {
        return new Card(approvedCard(), getValidMonth(), "2a", getValidHolder(), getValidCvv());
    }

    public static Card getSpecCharYear() {
        return new Card(approvedCard(), getValidMonth(), "2@", getValidHolder(), getValidCvv());
    }

    // поле "Месяц"
    public static Card getEmptyMonth() {
        return new Card(approvedCard(), "", getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getLastMonth() {
        return new Card(approvedCard(), getPastMonth(), getValidYear(), getValidHolder(), getValidCvv());
    }
    public static Card getZeroMonth() {
        return new Card(approvedCard(), "00", getValidYear(), getValidHolder(), getValidCvv());
    }

    public static Card getLiteralCharMonth() {
        return new Card(approvedCard(), "1a", getValidYear(), getValidHolder(), getValidCvv());
    }

    public static Card getSpecCharMonth() {
        return new Card(approvedCard(), "1@", getValidYear(), getValidHolder(), getValidCvv());
    }


    //поле "CVC/CVV"
    public static Card getEmptyCVV() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "");
    }
    public static Card getOneNumberCVV() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "1");
    }
    public static Card getTwoNumberCVV() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "12");
    }
    public static Card getInvalidCVVLiteralChar() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "12a");
    }

    public static Card getInvalidCVVSpecChar() {
        return new Card(approvedCard(), getValidMonth(), getValidYear(), getValidHolder(), "12@");
    }

    //генераторы данных

    public static String getRandomCardNumber() {
        return faker.business().creditCardNumber();
    }
    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }
    public static String getPastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }
    public static String getValidYear() {
        String validYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return validYear;
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
        return faker.numerify("###");
    }
}
