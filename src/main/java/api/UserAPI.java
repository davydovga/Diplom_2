package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.user.CreateUserReq;

import static api.BaseAPI.*;
import static configuration.Endpoints.CREATE_USER_ENDPOINT;
import static configuration.Endpoints.USER_ENDPOINT;

public class UserAPI {
    @Step("Вызов API регистрации пользователя")
    public static Response createUserAPI(CreateUserReq body){
        return postReq(body, CREATE_USER_ENDPOINT);
    }

    @Step("Вызов API удаления тестового пользователя")
    public static Response deleteUserAPI(String token){
        return deleteReq(USER_ENDPOINT, token);
    }

    @Step("Вызов API получение информации о пользователе")
    public static Response getUserInfoAPI(String token){
        return getReq(USER_ENDPOINT, token);
    }

    @Step("Вызов API изменения информации о пользователе")
    public static Response editUserInfoAPI(String token, CreateUserReq body){
        return patchReq(USER_ENDPOINT, token, body);
    }
    @Step("Вызов API изменения информации о пользователе без авторизации")
    public static Response editUserInfoAPIWithoutAuth(CreateUserReq body){
        return patchWithoutAuthReq(USER_ENDPOINT, body);
    }


}
