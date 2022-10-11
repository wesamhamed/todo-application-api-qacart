package com.qacart.todo.testcases;

import static io.restassured.RestAssured.*;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import org.testng.annotations.Test;

@Feature("Todo Feature")
public class TodoTest {

    @Story("Should Be Able To Add Todo")
    @Test(description = "should Be Able To Add Todo")
    public void shouldBeAbleToAddTodo(){

        Todo todo = TodoSteps.generateTodo();

        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(todo,token);
        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),is(equalTo(todo.getItem())));
        assertThat(returnedTodo.getIsCompleted(),equalTo(todo.getIsCompleted()));
    }
    @Story("Should Not Be Able To Add Todo If Is Completed Missing")
    @Test(description = "should Not Be Able To Add Todo If Is Completed Missing")
    public void shouldNotBeAbleToAddTodoIfIsCompletedMissing(){

        Todo todo = new Todo("Learn Appium");

        String token = UserSteps.getUserToken();

        Response response = TodoApi.addTodo(todo,token);
        Error returnedError = response.body().as(Error.class);

        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(),is(equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED)));
    }
    @Story("Should Be Able To Get A Todo By ID")
    @Test(description = "should Be Able To Get A Todo By ID")
    public void shouldBeAbleToGetATodoByID(){

        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateTodo();
        String todoID = TodoSteps.getTodoID(todo,token);
        Response response = TodoApi.getTodo(token,todoID);
        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnedTodo.getItem(),is(equalTo(todo.getItem())));
        assertThat(returnedTodo.getIsCompleted(),is(equalTo(todo.getIsCompleted())));
    }
    @Story("Should Be Able To Delete Todo By ID")
    @Test(description="should Be Able To Delete Todo By ID")
    public void shouldBeAbleToDeleteTodoByID(){

        String token = UserSteps.getUserToken();
        Todo todo = TodoSteps.generateTodo();
        String todoID =TodoSteps.getTodoID(todo,token);
        Response response = TodoApi.deleteTodoByID(token,todoID);
        Todo returnTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(returnTodo.getItem(),is(equalTo(todo.getItem())));
        assertThat(returnTodo.getIsCompleted(),is(equalTo(todo.getIsCompleted())));
    }
}
