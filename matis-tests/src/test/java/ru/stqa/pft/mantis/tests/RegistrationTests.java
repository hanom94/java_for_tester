package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by hanom on 07.03.2017.
 */
public class RegistrationTests extends TestBase{

  //Создаем уникальные индентификаторы для регистрация новых пользователей
  long now = System.currentTimeMillis();


  //Запускаем почтовый сервер
  //@BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegostration() throws IOException, MessagingException {

    //Делаем переменную для почты пользователя
    String email = String.format("user1%s@localhost.localdomain", now);

    //Делаем переменную для имени пользователя
    String user = String.format("user%s", now);

    //Делаем переменную для пароля пользователя
    String password = "password";

    //Перед регистрацией пользователя, создаем его на почтовом сервисе
    app.james().createUser(user, password);

    //Начинаем регистрацию
    app.registration().start(user, email);

    //Ожидание почты и присваем это значение mailMessage
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);

    //Получение почты с внешнего почтового сервиса
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);

    //Делаем вспомогательный метод
    //Сравниваем полученную ссылку в локальную переменную
    String confirmationLink = findConfirmationLink(mailMessages, email);

    //Завершаем регистрацию
    app.registration().finish(confirmationLink, password);

    //Проверяем возможнсть входа нового пользователя после регистрации
    assertTrue(app.newSession().login(user, password));
  }

  //Впомогательный метод для письмо = пользователь
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {

    //Среди всех писем находим нужное, в которое в качестве параметра предается предикат(булевское значение)
    //Отбираем нужную почту - по нужному email
    //Делаем объект типа mailMessage
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();

    //Для получения ссылки из письма используем регулярное выражение
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();

    //Применяем регулярное выражение к тексту
    return regex.getText(mailMessage.text);
  }

  //Останаливаем почтовый сервер
  //@AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
