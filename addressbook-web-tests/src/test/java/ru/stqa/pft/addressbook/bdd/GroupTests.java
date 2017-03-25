package ru.stqa.pft.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by hanom on 25.03.2017.
 */

@CucumberOptions(features = "classpath:bdd", plugin = {"pretty", "html:build/cucuber-report"})
public class GroupTests extends AbstractTestNGCucumberTests {

}
