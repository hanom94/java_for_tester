package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hanom on 14.03.2017.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app){

    this.app = app;
  }

  //Извлечение данных по проектам
  public Set<Project> getProgects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p)->new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public Set<Issue> getIssues(Project pr) throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issues = mc.mc_project_get_issues(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(pr.getId())
            , BigInteger.valueOf(1), BigInteger.valueOf(10));
    //преобразование полученных данных в объекты project
    return Arrays.asList(issues).stream()
            .map((i) -> new Issue().withId(i.getId().intValue()).withSummary(i.getSummary())
                    .withStatus(i.getStatus().getName()).withDescription(i.getDescription()))
            .collect(Collectors.toSet());
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root",
            BigInteger.valueOf(issue.getProject().getId()));
    //Метод для передачи удаленного индерфейса
    IssueData issueData = new IssueData();
    //Заполняем объект типа IssueData
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getId().intValue())
            .withName(createdIssueData.getProject().getName()));
  }
}
