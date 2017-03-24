package ru.stqa.pft.addressbook.rf;

import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

/**
 * Created by hanom on 24.03.2017.
 */
public class AddressbookKeywords {

  public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

  private ApplicationManager app;

  public void initApplicationManager() throws IOException {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    app.init();
  }

  public void stopApplicationManager(){
    app.stop();
    app = null;
  }

  public int getGroupCount(){
    app.goTo().groupPage();
    return app.groupContact().GroupCount();
  }

  public void createGroup(String name, String header, String footer){
    app.goTo().groupPage();
    app.groupContact().createGroup(new GroupData().withName(name).withHeader(header).withFooter(footer));
  }
}

