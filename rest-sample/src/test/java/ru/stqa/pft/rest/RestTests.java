package ru.stqa.pft.rest;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanom on 15.03.2017.
 */
public class RestTests {

  @Test
  public void testCreateIssue() throws IOException {
    //Получаем множество объектов типа Issue (старый набор)
    Set<Issue> oldIssues = getIssues();
    //Создаем новый объект
    Issue newIssue = new Issue();
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
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/ussues.json"))
            .returnContent().asString();
    //Все баг-репорты
    return null;
  }

  private Executor getExecutor(){
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  private int createIssue(Issue newIssue) {
    return 0;
  }
}
