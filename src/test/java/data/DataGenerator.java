package data;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.ContentType;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private final static String activeStatus = "active";
    private final static String blockedStatus = "blocked";

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(io.restassured.http.ContentType.JSON)
            .setContentType(io.restassured.http.ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static Gson gson = new Gson();
    private static Faker faker = new Faker(new Locale("en"));


    private static void registrationUsers(UserData userData) {
        given()
                .spec(requestSpec)
                .body(gson.toJson(userData))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static UserData generateValidActive() {
        UserData userData = new UserData(faker.name().username(),
                faker.internet().password(true), activeStatus);
        registrationUsers(userData);
        return userData;
    }

    public static UserData generateValidBlocked() {
        UserData userData = new UserData(faker.name().username(),
                faker.internet().password(true), blockedStatus);
        registrationUsers(userData);
        return userData;
    }

    public static UserData generateInvalidLogin() {
        String password = faker.internet().password(true);
        UserData userDataRegistration = new UserData(faker.name().username(),
                password, activeStatus);
        registrationUsers(userDataRegistration);
        return new UserData(faker.name().username(),
                password, activeStatus);
    }

    public static UserData generateInvalidPassword() {
        String login = faker.name().username();
        UserData userDataRegistration = new UserData(login,
                faker.internet().password(true), activeStatus);
        registrationUsers(userDataRegistration);
        return new UserData(login,
                faker.internet().password(true), activeStatus);
    }

}
