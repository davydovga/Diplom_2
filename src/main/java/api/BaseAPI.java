package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static configuration.Endpoints.BASE_URI;
import static io.restassured.RestAssured.given;
public class BaseAPI {
    public static Response postReq(Object body,String endpoint){
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response deleteReq(String endpoint, String token){
        return given()
                .auth().oauth2(token)
                .baseUri(BASE_URI)
                .when()
                .delete(endpoint);
    }

    public static Response getReq(String endpoint, String token){
        return given()
                .auth().oauth2(token)
                .baseUri(BASE_URI)
                .when()
                .get(endpoint);
    }

    public static Response patchReq(String endpoint, String token, Object body){
        return given()
                .auth().oauth2(token)
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .patch(endpoint);

    }
}
