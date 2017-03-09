package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MantisUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created by hanom on 09.03.2017.
 */
public class ResestPassForUserTests extends TestBase {

  //Запускаем сервер почты
  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  //Реализовываем тест сброса пароля
  @Test
  public void testResetPassForUser() throws IOException, MessagingException {

    //Входим в аккаунт под учетной записью Администратора
    app.admin().administratorLogin(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));

    //Выбераем пользователя
    app.admin().selectUser();

    //Помещаем его в переменную UserMantis
    MantisUser userMantis = app.admin().getUser();

    //Сбрасываем пользователю пароль
    app.admin().resetPassword();

    //Ждем в переменную mailMessage почту
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);

    //Объявляем переменную с текущим временем
    long now = System.currentTimeMillis();

    //Генерируем новый пароль для пользователя - пароль+текущее время
    String newPassword = "password" + now;

    //В переменную confirmationLink берем ссылку из письма для пароля
    String confirmationLink = findConfirmationLink(mailMessages, userMantis.getEmail());
    app.registration().finish(confirmationLink, newPassword);

    //Проверяем, сможет ли пользователь ввойти с новым паролем
    assertTrue(app.newSession().login(userMantis.getUsername(), newPassword));
  }

  //Метод для "вытягивания" ссылки из песьма для пароля
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //Останавливаем сервис почты
  @AfterMethod
  public void stopMailServer(){
    app.mail().stop();
  }
}
