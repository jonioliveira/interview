package com.jonioliveira.users;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SwaggerTest {
    @Test
    public void testSwaggerEndpoint() {
        given()
                .when().get("/swagger-ui")
                .then()
                .statusCode(200);
    }

}
