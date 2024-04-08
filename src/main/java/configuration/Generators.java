package configuration;

import api.UserAPI;
import com.github.javafaker.Faker;
import requests.user.CreateUserReq;

public class Generators {

    Faker faker = new Faker();

    public CreateUserReq createUser(){
        return new CreateUserReq(
            faker.internet().emailAddress(),
            faker.internet().password(),
            faker.name().username());
    }
}
