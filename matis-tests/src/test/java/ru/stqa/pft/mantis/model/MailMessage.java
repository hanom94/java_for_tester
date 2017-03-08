package ru.stqa.pft.mantis.model;

/**
 * Created by hanom on 08.03.2017.
 */

//Делаем модельный объект
public class MailMessage {
  public String to; //Кому пришло письмо
  public String text; //Текст письма

  public MailMessage(String to, String text){
    this.to = to;
    this.text = text;
  }
}
