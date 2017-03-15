package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by hanom on 14.03.2017.
 */
public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProgects();
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }
  }

  @Test
  public void testGetIssues() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProgects();
    Set<Issue> issues = app.soap().getIssues(projects.iterator().next());
    System.out.println(issues.size());
    for (Issue issue : issues) {
      System.out.println(issue.getId());
      System.out.println(issue.getStatus().toString());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProgects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void getIss() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(1);
    System.out.println("Issue#1 was fixed - enjoy the test :)");
  }
}
