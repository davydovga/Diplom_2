package tests;

import configuration.Generators;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import requests.user.CreateUserReq;
import responses.user.CreateUserResponse;

import static api.UserAPI.editUserInfoAPI;
import static api.UserAPI.getUserInfoAPI;
import static org.junit.Assert.*;

public class EditUserTest extends CommonParams{

    CreateUserResponse responseForGetReq = createUserResponse.as(CreateUserResponse.class);
    String token = responseForGetReq.getAccessToken().substring(7);



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
    @DisplayName("")
    @Description("")
    public void editUserInfoTest(){
        System.out.println(createUserReq.getEmail());

        CreateUserReq changedUser = new Generators().createUser();

        System.out.println(changedUser.getEmail());

        Response response = editUserInfoAPI(token, changedUser);

			  System.out.println(response.asString());
//        response.then()
//                .assertThat()
//                .statusCode(200);

        response.prettyPrint();


        assertEquals(createUserReq.getEmail(), changedUser.getEmail());
    }
}
