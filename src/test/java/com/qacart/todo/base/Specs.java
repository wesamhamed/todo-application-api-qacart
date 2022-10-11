package com.qacart.todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specs {

    public static String getEnv(){
        String env = System.getProperty("env","PRODUCTION");
        String baseURL;
        switch(env){
            case "PRODUCTION":
                baseURL ="https://qacart-todo.herokuapp.com";
                break;
            case "LOCAL":
                baseURL="https://qacart-todo.herokuapp.com";
                break;
            default:
                throw new RuntimeException("Environment is not supported");
        }
        return baseURL;
    }
    public static RequestSpecification getRequestSpecification() {
        return given()
                .baseUri(getEnv())
                .contentType(ContentType.JSON)
                .log().all();
    }
}
