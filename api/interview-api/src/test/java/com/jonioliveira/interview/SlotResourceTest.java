package com.jonioliveira.interview;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

@QuarkusTest
public class SlotResourceTest {

    @Test
    public void testResourceAddSlotSuccess() {
        String s = "[{\"endDate\": \"2019-08-10 11:00:00\"," +
                "    \"interviewerId\": \"1\"," +
                "    \"startDate\": \"2019-08-10 10:00:00\"}]";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots")
                .then()
                .statusCode(201)
                .body("interviewerId", hasItem(1))
                .body("startDate", hasItem("2019-08-10 10:00:00"))
                .body("endDate", hasItem("2019-08-10 11:00:00"))
                .body("status", hasItem(1));
    }

    @Test
    public void testResourceAddSlotFail() {
        String s = "[{\"endDate\": \"2019-08-10 11:00:00\"," +
                "    \"interviewerId\": \"2\"," +
                "    \"startDate\": \"2019-08-10 10:00:00\"}]";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots")
                .then()
                .statusCode(500);
        
    }

    @Test
    public void testResourceGetByDateSuccess(){
        String s = "{\"date\":\"2019-08-10 10:00:00\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots/date")
                .then()
                .statusCode(200)
                .body("interviewerId", hasItem(1))
                .body("startDate", hasItem("2019-08-10 10:00:00"))
                .body("endDate", hasItem("2019-08-10 11:00:00"))
                .body("status", hasItem(1));
    }

    @Test
    public void testResourceGetByDateFail(){
        String s = "{\"date\":\"2019-12-05 10:00:00\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots/date")
                .then()
                .statusCode(500);
    }

    @Test
    public void testResourceGetByDateAndUserSuccess(){
        String s = "{\"date\":\"2019-08-10 10:00:00\", \"userId\":\"1\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots/dateuser")
                .then()
                .body("interviewerId", hasItem(1))
                .body("startDate", hasItem("2019-08-10 10:00:00"))
                .body("endDate", hasItem("2019-08-10 11:00:00"))
                .body("status", hasItem(1));
    }

    @Test
    public void testResourceGetByDateAndUserFail(){
        String s = "{\"date\":\"2019-12-05 10:00:00\", \"userId\":\"2\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots/dateuser")
                .then()
                .statusCode(500);
    }

    @Test
    public void testResourceScheduleInterviewSuccess(){
        String s = "{\"candidateId\": \"2\", \"slotId\": \"1\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().put("/v1/slots/schedule")
                .then()
                .body("candidateId", equalTo(2))
                .body("status", equalTo(2))
                .statusCode(200);
    }

    @Test
    public void testResourceScheduleInterviewFail(){
        String s = "{\"candidateId\": \"2\", \"slotId\": \"3\"}";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().put("/v1/slots/schedule")
                .then()
                .statusCode(500);
    }

    @Test
    public void testResourceDeleteSuccess(){
        given().contentType(MediaType.APPLICATION_JSON)
                .delete("/v1/slots/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testResourceDeleteFail(){
        given().contentType(MediaType.APPLICATION_JSON)
                .delete("/v1/slots/2")
                .then()
                .statusCode(500);
    }
}