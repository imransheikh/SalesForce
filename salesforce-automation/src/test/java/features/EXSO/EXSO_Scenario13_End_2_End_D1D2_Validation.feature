Feature: Create a new Service Item with EXSO Manager and Create a New Assignment and Validate the executive approval process with D1 and D2 user
  Verify the executive approval process with D1 and D2 user
  @EXSO_Scenario13
  Scenario: Log into EXSO Org and log in as EXSO Service Item Manager
  	Given Launching the EXSO app and logging in as EXSO Service Item Manager for scenario 13 validation
  
  @EXSO_Scenario13
  Scenario: Create a new Service item and Create new assignemnt and validate the status and grab the SI and Assignment Number
  	Then Logging in as EXSO Service Item Manager and Create a New service item
  	Then Attach New File to the service item
  	Then Create a New Assignment and Validate the Service Item status is changed to CLEARANCE
  
  @EXSO_Scenario13
  Scenario: Log in as Do Advisor User and accept the assignment and the do the execute approval
  	Then Log in as Do Advisor User and accept the assignment
  	Then Do the execute approval with the logged in user
  
  @EXSO_Scenario13
  Scenario: Log in DO D2 user and validate the execute approval and complete the service item and validate the status
   Then Log in DO D2 user and validate the execute approval and complete the service item
   Then Validate the status of D1 and D2 user

  @EXSO_Scenario13
  Scenario: Re-login as DO Advisor user and validate
   Then Log in as DO Advisors User and validate the D1 and D2 user executive approval status
  
  @EXSO_Scenario13
  Scenario: Validate the status after clicking on the restart from the begining button
   Then Log in as DO D2 User and chnage the status from restart from the begining and validate the status is changed
  
  @EXSO_Scenario13
  Scenario: Re-login as DO D1 user and validate the status
   Then Validate D1 user status
   Then Mark the D1 one item complete and validate the status
   
  @EXSO_Scenario13
  Scenario: Re-login as DO Advisor user and validate the status of D1 and D2 user
   Then Log in as Do Advisors user and Verify the status of DO D1 and D2 line items status
  
  @EXSO_Scenario13
  Scenario: Ending the script and close the browser
   Then EXSO Stop Report Generation for scenario EXSO Scenario13 End to End Scenario
   Then EXSO Scenario13 Close the browser Email auto approval process
   