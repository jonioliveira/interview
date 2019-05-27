package com.jonioliveira.users;

import com.jonioliveira.users.domain.models.User;
import com.jonioliveira.users.exceptions.UserNotFoundException;
import com.jonioliveira.users.exceptions.UserTypeNotFoundException;
import com.jonioliveira.users.services.UserService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    @Inject
    UserService service;

    @Test
    public void testAddUser() {
        String s = "{\"name\":\"John\", \"type\":\"1\"}";
        with().body(s)
        .contentType(MediaType.APPLICATION_JSON)
          .when().post("/v1/user")
          .then()
             .statusCode(201).body(is("{\"id\":1,\"name\":\"john\",\"type\":{\"id\":1,\"name\":\"interview\"}}"));
    }

    @Test
    public void testAddUserNoType() {
        String s = "{\"name\":\"John\", \"type\":\"4\"}";
        with().body(s)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user")
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddUserFail() {
         given().contentType(MediaType.APPLICATION_JSON)
                .post("/v1/user")
                .then()
                .statusCode(500);
    }

    @Test
    public void testLogin() {
        String s = "{\"name\":\"ava\", \"type\":\"2\"}";
        with().body(s)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user")
                .then()
                .statusCode(201);

        String s2 = "{\"name\":\"ava\"}";
        with().body(s2)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void testLoginFail() {
        String s = "{\"name\":\"ava\", \"type\":\"2\"}";
        with().body(s)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user")
                .then()
                .statusCode(201);

        String s2 = "{\"name\":\"jhon\"}";
        with().body(s2)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user/login")
                .then()
                .statusCode(404);
    }

    @Test
    public void userServiceAddUser(){
        try {
            User user = service.addUser("ava", 2);
            Assertions.assertEquals(user.getName(), "ava");
            Assertions.assertEquals(user.getType().getId(), 2);
        } catch (UserTypeNotFoundException e) {
            Assertions.fail();
        }
    }

    @Test
    public void userServiceLogin(){
        try {
            User user = service.login("ava");
            Assertions.assertEquals(user.getName(), "ava");
            Assertions.assertEquals(user.getType().getId(), 2);
        } catch (UserNotFoundException e) {
            Assertions.fail();
        }
    }
}