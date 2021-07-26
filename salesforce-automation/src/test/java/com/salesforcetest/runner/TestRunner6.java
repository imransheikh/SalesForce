package com.salesforcetest.runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
	
@RunWith(Cucumber.class)
@CucumberOptions(//features = "src/test/java/features/uatg",
	features = "src/test/java/features",
	//glue = { "com.salesforcetest.mapper.uatg" },
	glue = { "com.salesforcetest.mapper" },
	//glue = { "com.salesforcetest.mapper.eaaqc" },
	//plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-report/report.html" },
	tags = {"@DirectApprovalHD"},
	strict = true,
    monochrome = false)
public class TestRunner6 {

}
