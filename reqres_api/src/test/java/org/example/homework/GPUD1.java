package org.example.homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD1 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAlluser() {

        given().log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void getsingleuser() {

        given().log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void createUser() {

        String jsonData = "{\n" +
                "    \"name\": \"kinjal1\",\n" +
                "    \"job\": \"testlead\"\n" +
                "}";
        validatableResponse = given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);

        System.out.println(validatableResponse.extract().asPrettyString());
    }

    @Test
    public void updateuserName() {

        String jsonData = "{\n" +
                "    \"name\": \"shikha\",\n" +
                "    \"job\": \"Tester\"\n" +
                "}";

        given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .then().statusCode(200).body(".name", equalTo("shikha"));
    }

    @Test
    public void deleteUser() {

        validatableResponse = given()
                .baseUri("https://reqres.in/api/users/2")
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat().statusCode(204);
        System.out.println("Response :" + validatableResponse.extract().asPrettyString());
    }
}
