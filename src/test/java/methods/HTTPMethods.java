package methods;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import pojo.Pojo;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class HTTPMethods {
    public static String createdUserId;
    public static final String baseURI = System.getProperty("url");

    public static ValidatableResponse getAll(String endpoint, Integer statusCodeExpected) {
        RestAssured.baseURI = baseURI;
        ValidatableResponse response = given().
                header("Content-Type", ContentType.JSON).
                when().
                log().all()
                .get(endpoint).
                then()
                .assertThat().statusCode(statusCodeExpected)
                .log().all();
        return response;
    }

    public static ValidatableResponse getById(Pojo body, String endpoint, Integer statusCodeExpected) {
        RestAssured.baseURI = baseURI;
        ValidatableResponse response = given()
                .log().all().
                header("Content-Type", ContentType.JSON).
                body(body).
                when().
                log().all()
                .post(endpoint).
                then()
                .assertThat().statusCode(statusCodeExpected)
                .log().all();
        return response;
    }

    public static ValidatableResponse updateUser(Pojo body, String endpoint, String editor, String id, Integer statusCodeExpected) {
        RestAssured.baseURI = baseURI;
        ValidatableResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .log().all()
                .body(body)
                .when()
                .log().all()
                .patch(endpoint + "/" + editor + "/" + id)
                .then()
                .assertThat().statusCode(statusCodeExpected)
                .log().all();
        return response;
    }


    public static ValidatableResponse deleteUser(Pojo body, String endpoint, String editor, Integer statusCodeExpected) {
        RestAssured.baseURI = baseURI;
        ValidatableResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .log().all()
                .body(body)
                .when()
                .log().all()
                .delete(endpoint + editor)
                .then()
                .assertThat().statusCode(statusCodeExpected)
                .log().all();
        return response;
    }

    public static ValidatableResponse createUser(Pojo body, String endpoint, String role, Integer statusCodeExpected) {
        RestAssured.baseURI = baseURI;
        ValidatableResponse response = given().
                header("Content-Type", ContentType.JSON).
                queryParams(new Gson().fromJson(new JSONObject(body).toString(), Map.class))
                .log().all()
                .when().
                log().all()
                .get(endpoint + role).
                then()
                .assertThat().statusCode(statusCodeExpected)
                .log().all();
        Object doc = (Configuration.defaultConfiguration().jsonProvider().parse(response.extract().asString()));
        createdUserId = JsonPath.read(doc, "$.id").toString();
        return response;
    }
}
