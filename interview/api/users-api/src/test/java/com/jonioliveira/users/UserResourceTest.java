package com.jonioliveira.users;

import com.jonioliveira.users.domain.model.User;
import com.jonioliveira.users.exception.UserAlreadyExistsException;
import com.jonioliveira.users.exception.UserTypeNotFoundException;
import com.jonioliveira.users.service.UserService;
import com.jonioliveira.users.utils.Mapper;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class UserResourceTest {

    @Inject
    UserService service;

    @Test
    public void testAddUser() {
        String s = "{\"name\":\"user1\", \"type\":\"1\"}";
        with().body(s)
        .contentType(MediaType.APPLICATION_JSON)
          .when().post("/v1/user")
          .then()
             .statusCode(201).body(is("{\"id\":1,\"name\":\"user1\",\"type\":1}"));
    }

    @Test
    public void testAddUserNoType() {
        String s = "{\"name\":\"user2\", \"type\":\"4\"}";
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
                .statusCode(405);
    }

    @Test
    public void testLogin() {
        String s = "{\"name\":\"user3\", \"type\":\"2\"}";
        with().body(s)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user")
                .then()
                .statusCode(201);

        String s2 = "{\"name\":\"user3\"}";
        with().body(s2)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void testLoginFail() {
        String s2 = "{\"name\":\"user4\"}";
        with().body(s2)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/v1/user/login")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetUser() {
        try {
            User user = service.addUser("user6", 1);

            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(Mapper.userToUserResponse(user));

            given().contentType(MediaType.APPLICATION_JSON)
                    .when().get("/v1/user/"+user.getId())
                    .then()
                    .statusCode(200)
                    .body(is(json));

        } catch (UserTypeNotFoundException | UserAlreadyExistsException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testGetUserFail() {
        given().contentType(MediaType.APPLICATION_JSON)
                .when().get("/v1/user/123")
                .then()
                .statusCode(404);
    }
}