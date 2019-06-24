package com.jonioliveira.interview;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SlotResourceTest {

    @Test
    public void testResourceEndpoint() {

        LocalDateTime ldtStart = LocalDateTime.now().
                with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(11)
                .withMinute(0)
                .withSecond(0);

        LocalDateTime ldtEnd = LocalDateTime.now().
                with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0)
                .withSecond(0);

        String s = "[{\"endDate\": \""+ aux +"\",\n" +
                "    \"interviewerId\": \"1\",\n" +
                "    \"startDate\": \"2019-08-10 10:00:00\"}]";

        given().contentType(MediaType.APPLICATION_JSON)
                .body(s)
                .when().post("/v1/slots")
                .then()
                .statusCode(201)
                .body(is(""));
    }

}