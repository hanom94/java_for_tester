package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by hanom on 07.03.2017.
 */
public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;

  //Происходит инициализация, создаеется новый ftp-клиент
  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient();
  }

  //Метод загружает новый файл, при этом старый временно  переименовывает
  /*
  * file - локальный файл который должен быть загружен на удаленную машину
  * target - имя удаленного файла
  * backup - имя резервной копии файла
  */
  public void upload(File file, String target, String backup) throws IOException {

    //Установка соединения с сервером
    ftp.connect(app.getProperty("ftp.host"));

    //Выполняется вход
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));

    //Удаляем предыдущую резервную копию
    ftp.deleteFile(backup);

    //Переименовываем удаленный файл, делаем резервную копию
    ftp.rename(target, backup);

    //Пассивный режим передачи данных
    ftp.enterLocalPassiveMode();

    //Передача локального файла на сервер
    ftp.storeFile(target, new FileInputStream(file));

    //После того, как передача закончена - соединение разрывается
    ftp.disconnect();
  }

  //Метод восстанавливаает исходную конфигурацию тестируемой системы
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));

    //Удаляется файл, который вначале был загружен
    ftp.deleteFile(target);

    //Восстановливается оригинальный файл из резервной копии
    ftp.rename(backup,target);
    ftp.disconnect();
  }

}
