package tests;

import framework.MyListener;
import framework.Roles;
import io.qameta.allure.Step;
import methods.HTTPMethods;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojo.PlayerId;
import pojo.User;

import static framework.PropertyReader.*;
import static methods.HTTPMethods.createdUserId;

@Listeners(MyListener.class)
public class PositiveTests {
    PlayerId playerId = new PlayerId(createdUserId);

    @Step
    @Test(priority = 1)
    public void createUser() {
        HTTPMethods.createUser(new User(
                getTestData("postAge", getUserDefaultValues("age")),
                getTestData("postGender", getUserDefaultValues("gender")),
                getTestData("postLogin", getUserDefaultValues("login")),
                getTestData("postPassword", getUserDefaultValues("password")),
                getTestData("postRole", getUserDefaultValues("role")),
                getTestData("postScreenName", getUserDefaultValues("screenName"))
        ), getEndpoint("createUser"), Roles.supervisor.name(), 200);
    }

    @Step
    @Test(priority = 2)
    public void getById() {
        HTTPMethods.getById(playerId, getEndpoint("getById"), 200);
    }

    @Step
    @Test(priority = 3)
    public void getAllPlayers() {
        HTTPMethods.getAll(getEndpoint("getAllPlayers"), 200);
    }

    @Step
    @Test(priority = 4)
    public void updatePlayer() {
        HTTPMethods.updateUser(new User(
                getTestData("patchAge", getUserDefaultValues("age")),
                getTestData("patchGender", getUserDefaultValues("gender")),
                getTestData("patchLogin", getUserDefaultValues("login")),
                getTestData("patchPassword", getUserDefaultValues("password")),
                getTestData("patchRole", getUserDefaultValues("role")),
                getTestData("patchScreenName", getUserDefaultValues("screenName"))
        ), getEndpoint("updatePlayer"), Roles.supervisor.name(), playerId.getId(), 200);
    }

    @Step
    @Test(priority = 5)
    public void deleteById() {
        HTTPMethods.deleteUser(playerId, getEndpoint("deleteById"), Roles.supervisor.name(), 200);
    }

    @Step
    @Test(priority = 6)
    public void getByIdToVerifyDelete() {
        HTTPMethods.getById(playerId, getEndpoint("getById"), 404);
    }
}
