package com.salesforcetest.test;

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features="features",glue={"com.salesforcetest.mapper"},
plugin = { "pretty", "html:test-report/cucumber-reports", "junit:test-report/cucumber-reports/TestReport.xml" }, 
monochrome = true)						
public class TestRunner 				
{		

}
