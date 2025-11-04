package automation.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for generating data dynamically
 */
public class FakerUtil
{
    private static final Faker faker = new Faker();

    public static String randomUsername()
    {
        return faker.name().username().replaceAll("\\W", "");
    }

    public static String randomPassword()
    {
        return faker.internet().password(8, 16, true, true, true);
    }

    public static String randomFirstName()
    {
        return faker.name().firstName();
    }

    public static String randomMiddleName()
    {
        return faker.name().nameWithMiddle().split(" ")[1];
    }

    public static String randomLastName()
    {
        return faker.name().lastName();
    }

    public static String randomDateOfBirth()
    {
        Date dob = faker.date().birthday();
        LocalDate localDob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        return localDob.format(formatter);
    }

    //I have added it here even if its not from Faker just to know data comes from same refence point
    public static String randomGender()
    {
        return ThreadLocalRandom.current().nextBoolean() ? "Male" : "Female";
    }

    public static String recentJoinedDate()
    {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        int maxDay = today.getDayOfMonth() - 1;
        int day = ThreadLocalRandom.current().nextInt(1, maxDay + 1);

        LocalDate joinedDate = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        return joinedDate.format(formatter);
    }

    private FakerUtil()
    {
    }
}