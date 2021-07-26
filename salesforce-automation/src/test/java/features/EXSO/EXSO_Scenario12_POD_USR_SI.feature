Feature: Create a new Service Item with POD user and Validate all the expected functionality and feature is available
  Verify POD user Service Item with all expected feature
  @EXSO_Scenario2
  Scenario: Log into EXSO Org and log in as AAO POD user
  	Given Launching the EXSO app and logging in as admin for AAO Service Item scenario2 validation
  
  @EXSO_Scenario2
  Scenario: Create a new Service item with Pod user and validate all the expected feature
  	Then Logging in as AAO POD user and Create a New service item
  	Then Validate the newly created POD user SI and fetch the SI number
  
  @EXSO_Scenario2
  Scenario: Submit the newly created Service Item for approval and log out
  	Then Submit the newly created SI for approval
  
  @EXSO_Scenario2
  Scenario: Log in as EXSO Manager and validate the POD user created SI with all the expected buttons should be available as normal SI
   Then Log in as EXSO Manager and validate the POD user created SI

  @EXSO_Scenario2
  Scenario: Change the SI status to Clearance Review and Close and Archive and validate the Si type
   Then Change the SI status to Clearance Review and Close and Archive and validate the Si type
  
  @EXSO_Scenario2
  Scenario: Ending the script and close the browser
   Then EXSO Stop Report Generation for current scenario EXSO Assignment Scenario12 End to End Scenario1
   Then EXSO Scenario12 Close the browser Email auto approval process