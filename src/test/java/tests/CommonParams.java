package tests;

import configuration.Generators;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import requests.user.CreateUserReq;
import responses.user.CreateUserResponse;

import static api.UserAPI.createUserAPI;
import static api.UserAPI.deleteUserAPI;

public class CommonParams {

    static Generators dataGenerator = new Generators();

    static CreateUserReq createUserReq = dataGenerator.createUser();

    Response createUserResponse = createUserAPI(createUserReq);

    @After
    @DisplayName("Метод удаления тестовых даных")
    @Description("Удаляет созданные для тестов данные")
    public void deleteTestUsers(){
        CreateUserResponse userResponse = createUserResponse.as(CreateUserResponse.class);
        deleteUserAPI(userResponse.getAccessToken().substring(7));
    }

}
