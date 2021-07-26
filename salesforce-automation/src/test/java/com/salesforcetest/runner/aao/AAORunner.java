package com.salesforcetest.runner.aao;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


	

	
		
	@RunWith(Cucumber.class)
	@CucumberOptions(//features = "src/test/java/features/uatg",
		features = "src/test/java/features.aao",
		//glue = { "com.salesforcetest.mapper.uatg" },
		glue = { "com.salesforcetest.stepdefinition.aao" },
		//glue = { "com.salesforcetest.mapper.eaaqc" },
		//plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-report/report.html" },
		tags = {" @AAOEndToEnd"},
		
		strict = true,
	    monochrome = false)
	public class AAORunner { 
		// , @RegForIpo
	}


