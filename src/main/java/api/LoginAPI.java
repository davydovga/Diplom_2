package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.user.AuthReq;

import static api.BaseAPI.postReq;
import static configuration.Endpoints.USER_LOGIN_ENDPOINT;

public class LoginAPI {
    @Step("Вызов API авторизации пользователя")
    public static Response loginUserAPI(AuthReq body){
        return postReq(body, USER_LOGIN_ENDPOINT);
    }
}
