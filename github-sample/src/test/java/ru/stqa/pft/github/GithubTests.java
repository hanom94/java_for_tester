package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by hanom on 17.03.2017.
 */
public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("95c64d43e3a0fc4d0b5f383c796a50da4fa40a3e");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("hanom94", "java_for_tester")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
