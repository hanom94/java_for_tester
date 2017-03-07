package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanom on 07.03.2017.
 */
public class HttpSession {
  private CloseableHttpClient httpclient;

  //Запоминается ссылка на ApplicationManager
  private ApplicationManager app;

  //Передается ссылка на ApplicationManager
  public HttpSession(ApplicationManager app){
    this.app = app;
    //Создается новая сессия/новый клиент для работы по http
    //в созданном объекте HttpClients.custom() -> создается стратегия перенаправлений ->
    // -> создается автоматическое перенаправление new LaxRedirectStrategy()
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  //метод для выполнения Логина
  public boolean login(String username, String password) throws IOException {

    //Помошник получает у ApplicationManager ссылку
    //При запросе, внутри этого тела могут передааваться какие-то параметры
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");

    //Формирование набора параметров
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));

    //параметры "упаковываются" и помещаются в зарание созданный запрос setEntity
    post.setEntity(new UrlEncodedFormEntity(params));

    //Отправка и выполнение запроса httpclient.execute(post), response - ответ от сервера
    CloseableHttpResponse response = httpclient.execute(post);

    //Получаем текст в формате xml
    String body = geTextFrom(response);

    //Проверка успешной авторизации пользователя, body - текст страници, contains - содержит такую строку
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  //Метод для преобразования ответа от сервера в текст
  private String geTextFrom(CloseableHttpResponse response) throws IOException{
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  //Метод определяет какой пользователь сейчас "залогинен"
  public boolean isLoginInAs (String username) throws IOException {

    //Выполняем запрос на адрес index.php
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");

    //Выполняем запрос httpclient.execute(get), response - получаем ответ
    CloseableHttpResponse response = httpclient.execute(get);

    //Получаем текст
    String body = geTextFrom(response);

    //Проверям есть ли такой пользователь
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
}
