package api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;
import models.UserData;
import io.restassured.response.Response;

public class ApiClient {
    private static final Gson gson = new Gson();
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";

    @Step("Создание пользователя через API")
    public static Response createUser(UserData user) {
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(gson.toJson(user))
                .post("/auth/register");
    }

    @Step("Удаление пользователя через API")
    public static Response deleteUser(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .delete("/auth/user");
    }
}
