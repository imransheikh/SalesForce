Feature: Existing user record refresh and create a new service item with new response and reject with Supervisor user and upon re-submitting after correction supervisor will approve
  Verify End to End flow for new response rejection and approval process
  @E2EwithoutAddRev
  Scenario: Log in with HD ISO VSC user to refresh the application data
  	Given For rejection and auto approval of response scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For rejection and auto approval of response scenario set QC Percentage to "100"
    Then For rejection and auto approval of response scenario Verifying the current logged in user profile
    When For rejection and auto approval of response scenario Search for required Internal User "HD ISO VSC"
    Then For rejection and auto approval of response scenario Logging in as Internal user and verifying "HD ISO VSC"
  
  @E2EwithoutAddRev
  Scenario: Search with Customer and Refresh the customer record
  	When For rejection and auto approval of response scenario Search for customer "A200453283"
    Then For rejection and auto approval of response scenario Refresh the customer data and wait for Refresh successful message
  
  @E2EwithoutAddRev
  Scenario: Open the customer record details
  	Then For rejection and auto approval of response scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @E2EwithoutAddRev
  Scenario: Create new service item by providing below input data
   Then For rejection and auto approval of response scenario Click on create new service item "WAC1690258857"
   And For rejection and auto approval of response scenario create new service item Provide all new mandatory data
   | Org Name    | Email 						 | Sender Type   | Subject 		| Description      | Form Type                                      | Category    | Kind                  | Comments   | Item Origin | Queue     | DateTime         |
   | Test Org12  | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description | I-765 Application for Employment Authorization | Case Status | Inappropriate Contact | Creating SI| Email       | VAWA_UPFD | 10/4/2018 9:44 AM|
  
  @E2EwithoutAddRev
  Scenario: Verify the newly created service item and its status
   Then For rejection and auto approval of response scenario Verify New Service Item number and Details
  
  @E2EwithoutAddRev
  Scenario: Creating a new response and validating its status
   Then For rejection and auto approval of response scenario Create a new response for new Service Item
   Then For rejection and auto approval of response scenario Verify newly created service response status
  
  @E2EwithoutAddRev
  Scenario: Logging out from HD ISO VSC and logging in with One HD Supervisor
   Then For rejection and auto approval of response scenario Logout from current profile
   When For rejection and auto approval of response scenario Search for required Supervisor User "One HD Supervisor"
   Then For rejection and auto approval of response scenario Logging in as Supervisor user and verifying "One HD Supervisor"
  
  @E2EwithoutAddRev
  Scenario: Reject the Service item response with One HD Supervisor user and with proper comments and without marking Additional Review Checkbox
   Then For rejection and auto approval of response scenario Select required service item with Supervisor user to reject
   Then For rejection and auto approval of response scenario Reject the selected service request without marking additional review checkbox
   Then For rejection and auto approval of response scenario Verify the rejected service request status "Edits Required"
  
  @E2EwithoutAddRev
  Scenario: Log out from One HD Supervisor user profile and log in with HD ISO VSC
   Then For rejection and auto approval of response scenario Logout from supervisor profile
   When For rejection and auto approval of response scenario Search for required Internal User which created the response "HD ISO VSC"
   Then For rejection and auto approval of response scenario Logging in as Internal user and verifying User which created the response "HD ISO VSC"
  
  @E2EwithoutAddRev
  Scenario: Re-submitting the Service Item response and check the response status changed to sent and verify child SI created by replying to mail
   Then For rejection and auto approval of response scenario Edit pre-created response and resubmit
   Then For rejection and auto approval of response scenario Validate resubmitted service response status changed to "Sent"
  
  @E2EwithoutAddRev
  Scenario: Log out and close the browser
   Then For rejection and auto approval of response scenario Logout from response editor's profile
   Then Stop Report Generation for current scenario For rejection and auto approval of response scenario
   Then Close the browser For rejection and auto approval of response scenario
