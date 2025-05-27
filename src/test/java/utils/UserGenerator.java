package utils;

import models.UserData;
import net.datafaker.Faker;

public class UserGenerator {
    private static final Faker faker = new Faker();

    public static UserData generateRandomUser() {
        return new UserData(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password(6, 8)
        );
    }

    public static UserData generateUserWithIncorrectPass() {
        return new UserData(
                faker.name().fullName(),
                faker.internet().emailAddress(),
                faker.internet().password(2, 5)
        );
    }
}