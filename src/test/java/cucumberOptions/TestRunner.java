package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, plugin = { "pretty",
		"html:target/cucumber-html-report", "json:cucumber.json", "junit:target/cucumber.xml" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
