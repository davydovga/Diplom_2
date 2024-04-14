package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.user.CreateUserReq;
import responses.user.CreateUserBadResponse;
import responses.user.CreateUserResponse;

import static api.UserAPI.*;
import static configuration.ErrorMessages.EDIT_USER_BAD_REQ;
import static org.junit.Assert.*;

public class EditUserTest extends CommonParams{


    CreateUserReq changedUser = dataGenerator.createUser();



    @Test
    @DisplayName("Тест получения информации о пользователе по токену")
    @Description("Тест передает accessToken созданного пользователя и проверяет его корректность")
    public void getUserInfoTest(){
        Response response = getUserInfoAPI(token);

        response.then()
                .assertThat()
                .statusCode(200);

        assertTrue(response.as(CreateUserResponse.class).isSuccess());
        assertEquals("Емейл созданного пользователя не совпадает с полученным", createUserReq.getEmail(),
                response.as(CreateUserResponse.class).getUser().getEmail());
        assertEquals("Имя созданного пользователя не совпадает с полученным", createUserReq.getName(),
                response.as(CreateUserResponse.class).getUser().getName());
    }


    @Test
    @DisplayName("Тест возможности изменть email пользователя")
    @Description("Тест передает в тело запроса токен пользователя и данные другого пользователя, после проверяет что данные в пользователе поменялись")
    public void editUserEmailInfoTest(){
        Response response = editUserInfoAPI(token, changedUser);

        response.then()
                .assertThat()
                .statusCode(200);

        assertEquals(changedUser.getEmail(), response.as(CreateUserResponse.class).getUser().getEmail());
    }

    @Test
    @DisplayName("Тест возможности изменть имя пользователя")
    @Description("Тест передает в тело запроса токен пользователя и данные другого пользователя, после проверяет что данные в пользователе поменялись")
    public void editUserNameInfoTest(){
        Response response = editUserInfoAPI(token, changedUser);

        response.then()
                .assertThat()
                .statusCode(200);

        assertEquals(changedUser.getName(), response.as(CreateUserResponse.class).getUser().getName());
    }

    @Test
    @DisplayName("Негативный тест изменения пользователя без авторизации")
    @Description("Тест проверяет что невозможно изменть данные пользователя без авторизации")
    public void editUserInfoTestWithoutAuth(){
        Response response = editUserInfoAPIWithoutAuth(createUserReq);

        response.then()
                .assertThat()
                .statusCode(401);


        assertFalse(response.as(CreateUserBadResponse.class).isSuccess());
        assertEquals(EDIT_USER_BAD_REQ,response.as(CreateUserBadResponse.class).getMessage());
    }
}
