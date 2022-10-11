package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {

    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("_id")
    private String id;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;

    public Todo(Boolean isCompleted,String item){
        this.isCompleted = isCompleted;
        this.item = item;
    }
    public Todo(String item){
        this.item =item;
    }
    public Todo(){

    }
    @JsonGetter("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    @JsonSetter("isCompleted")
    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
    @JsonGetter("_id")
    public String getId() {
        return id;
    }
    @JsonSetter("_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @JsonGetter("__v")
    public String getV() {
        return v;
    }
    @JsonSetter("__v")
    public void setV(String v) {
        this.v = v;
    }

}
