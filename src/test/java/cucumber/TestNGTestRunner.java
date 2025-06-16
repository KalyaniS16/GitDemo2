package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Run our feature files present in mentioned package, stepDefinition is present in glue, print the results in readable format and generate the report of HTML plugin
// AbstractTestNGCucumberTests is important to run the cucumber file using testng code

@CucumberOptions(features="src/test/java/cucumber", glue="RSAcademy.stepDefinition", monochrome=true, plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
