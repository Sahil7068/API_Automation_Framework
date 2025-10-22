package cucumber.Options;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinitions"}, tags = "@addPlace")
public class TestRunner extends AbstractTestNGCucumberTests {
}
