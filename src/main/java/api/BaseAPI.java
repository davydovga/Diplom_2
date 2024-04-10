package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static configuration.Endpoints.BASE_URI;
import static configuration.Endpoints.GET_INGREDIENTS_ENDPOINT;
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

    public static Response postReqForCreateOrder(Object body, String endpoint, String token){
        return given()
                .baseUri(BASE_URI)
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response postReqForCreateOrderWithoutAuth(Object body, String endpoint){
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
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .delete(endpoint);
    }

    public static Response getReq(String endpoint, String token){
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(endpoint);
    }

    public static Response patchReq(String endpoint, String token, Object body){
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .patch(endpoint);

    }

    public static Response patchWithoutAuthReq(String endpoint, Object body){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(body)
                .when()
                .patch(endpoint);

    }

    public static Response getIngredientsReq(){
        return given()
                .baseUri(BASE_URI)
                .when()
                .get(GET_INGREDIENTS_ENDPOINT);
    }

    public static Response getOrdersReq(String endpoint, String token){
        return given()
                .baseUri(BASE_URI)
                .auth().oauth2(token)
                .when()
                .get(endpoint);
    }

    public static Response getOrdersWithoutAuthReq(String endpoint){
        return given()
                .baseUri(BASE_URI)
                .when()
                .get(endpoint);
    }
}
