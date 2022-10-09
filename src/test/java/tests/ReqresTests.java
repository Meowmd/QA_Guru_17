package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresTests {

    @Test
    @DisplayName("Check user list")
    void checkUserList() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }


    @Test
    @DisplayName("Check user email")
    void checkEmailUser() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[1].email", is("lindsay.ferguson@reqres.in"));
    }


    @Test
    @DisplayName("Create new user")
    void createNewUser() {
        String body = "{ \"name\": \"kitty\", \"job\": \"cat\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("kitty"))
                .body("job", is("cat"));
    }


    @Test
    @DisplayName("Update user info")
    void updateUserInfo() {
        String body = "{ \"name\": \"kitty\", \"job\": \"master cat\"\n}";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("kitty"))
                .body("job", is("master cat"));
    }

    @Test
    @DisplayName("Check update user info")
    void checkUpdateUserInfo() {
        String body = "{ \"name\": \"kitty\", \"job\": \"master cat\"\n}";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(body)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("kitty"))
                .body("job", is("master cat"));
    }
}