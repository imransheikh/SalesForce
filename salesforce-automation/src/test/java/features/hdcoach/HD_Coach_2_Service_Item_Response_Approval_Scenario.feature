Feature: Once a service item is created with Queue value as I360 then owner needs to get change to HD Coach 2 and approve it same
  Verify End to End flow for new response approval process with HD Coach 2 user
@HDCoach2ApprovalScenario
  Scenario: Log in with HD ISO VSC user to refresh the application data
  	Given For HD coach approval scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For HD coach approval scenario set QC Percentage to "100"
    Then For HD coach approval scenario Verifying the current logged in user profile
    When For HD coach approval scenario Search for required Internal User "HD ISO VSC"
    Then For HD coach approval scenario Logging in as Internal user and verifying "HD ISO VSC"
  
  @HDCoach2ApprovalScenario
  Scenario: Search with Customer and Refresh the customer record
  	When For HD coach approval scenario Search for customer "A200453283"
    Then For HD coach approval scenario Refresh the customer data and wait for Refresh successful message
  
  @HDCoach2ApprovalScenario
  Scenario: Open the customer record details
  	Then For HD coach approval scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @HDCoach2ApprovalScenario
  Scenario: Create new service item by providing below input data
   Then For HD coach approval scenario Click on create new service item "WAC1690258857"
   And For HD coach approval scenario create new service item Provide all new mandatory data
   | Org Name    | Email 						 | Sender Type   | Subject 		| Description      | Form Type                                      | Category    | Kind                  | Comments   | Item Origin | Queue     				| DateTime         |
   | Test Org12  | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description | I-765 Application for Employment Authorization | Case Status | Inappropriate Contact | Creating SI| Email       | VAWA_HotlineFollowupI360 | 10/4/2018 9:44 AM|
  
  @HDCoach2ApprovalScenario
  Scenario: Verify the newly created service item and its status
   Then For HD coach approval scenario Verify New Service Item number and Details
  
  @HDCoach2ApprovalScenario
  Scenario: Creating a new response and validating its status
   Then For HD coach approval scenario Create a new response for new Service Item with attachment
  
  @HDCoach2ApprovalScenario
  Scenario: Verifying the attached file is present in the response and owner changed to HD Coach 2
   Then For HD coach approval scenario Validating the attachment
  
  @HDCoach2ApprovalScenario
  Scenario: log in as HD Coach 2 and approve the Response and verify the status of the response
   Then For HD coach approval scenario Search for required Internal coach User "HD Coach 2"
   When For HD coach approval scenario Validate the approved service item status changed to "Sent"
   Then Stop Report Generation for current scenario For HD coach approval scenario
   Then Close the browser For HD coach approval scenario