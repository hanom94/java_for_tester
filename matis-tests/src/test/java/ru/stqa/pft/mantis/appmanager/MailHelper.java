package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hanom on 08.03.2017.
 */
public class MailHelper {
  private ApplicationManager app;
  private final Wiser wiser;

  //При инициализации создается объект типа Wiser() - почтовый сервер
  public MailHelper(ApplicationManager app){
    this.app = app;
    wiser = new Wiser();
  }

  //Добавляем метод ожидания - waitForMail(кол-во писем, время ожидания)
  public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {

    //Запоминаем текщее время
    long start = System.currentTimeMillis();

    //Проверяем, что текущее новое время не превышает момент старта + таймаут
    while (System.currentTimeMillis() < start + timeout) {

      //Если  почты пришло достаточно, то ожидание можно прекращать
      if (wiser.getMessages().size() >= count){

        //Возвращает переобразованные реальные объекты в модельные
        //Почтовый список -> превращаем в поток -> применям функцию -> из получившиехся объектов формируем список
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }

      //Если почты не достаточно(мало), то попадаем сюда
      try {
        Thread.sleep(1000);
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  //Меетод переобразования реальных почтовых сообщений в модельные
  public static MailMessage toModelMail(WiserMessage m){
    try {
      MimeMessage mm = m.getMimeMessage();

      //Реальный объект(берем спосок получателей и оставляем только первого из них)
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    }catch (MessagingException e){
      e.printStackTrace();
      return null;
    }catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

  public void start(){
    wiser.start();
    wiser.setPort(25);
  }

  public void stop(){
    wiser.stop();
  }
}
