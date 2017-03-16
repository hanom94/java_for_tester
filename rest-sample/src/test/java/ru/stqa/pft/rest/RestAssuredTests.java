package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanom on 15.03.2017.
 */
public class RestAssuredTests {

  @BeforeClass
  public void init(){
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void testCreateIssue() throws IOException {
    //Получаем множество объектов типа Issue (старый набор)
    Set<Issue> oldIssues = getIssues();
    //Создаем новый объект
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    //Вызываем метод createIssue и передаем ему параметр Issue и заносится в переменную issueId
    int issueId = createIssue(newIssue);
    //Получаем множество объектов типа Issue(новый набор)
    Set<Issue> newIssues = getIssues();
    //В старое множество добавляем объект
    oldIssues.add(newIssue.withId(issueId));
    //Сравниваем
    assertEquals(newIssues, oldIssues);
  }

  //Создаем метод Issue
  private Set<Issue> getIssues() throws IOException {
    //Метод для передачи запроса
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    //Получаем Json элемент
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");

    //Все баг-репорты
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
