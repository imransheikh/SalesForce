Feature: Existing user record refresh and create a new service item with new response and reject with Ipo Super user and upon re-submitting after correction super user will approve
  Verify End to End flow for new response rejection and approval process for Ipo user
  @E2EwithoutAddiRev
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For IPO rejection and auto approval of response scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For IPO rejection and auto approval of response scenario set QC Percentage to "100"
    Then For IPO rejection and auto approval of response scenario Verifying the current logged in user profile
    When For IPO rejection and auto approval of response scenario Search for required Internal User "IPO CSR"
    Then For IPO rejection and auto approval of response scenario Logging in as Internal user and verifying "IPO CSR"
  
  @E2EwithoutAddiRev
  Scenario: Search with Customer and Refresh the customer record
  	When For IPO rejection and auto approval of response scenario Search for customer "A200453283"
    Then For IPO rejection and auto approval of response scenario Refresh the customer data and wait for Refresh successful message
  
  @E2EwithoutAddiRev 
  Scenario: Open the customer record details
  	Then For IPO rejection and auto approval of response scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @E2EwithoutAddiRev	
  Scenario: Create new service item by providing below input data
   Then For IPO rejection and auto approval of response scenario Click on create new service item "WAC1690258857"
   And For IPO rejection and auto approval of response scenario create new service item Provide all new mandatory data
   | Email 						 | Sender Type   | Subject 		| Description      |Form Number | Form Type                                      								  | Issue     		  | Action           | Comments   	 	| Item Origin | DateTime         |
   | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description |I924		|I-924 Application for Regional Center Under the Immigrant Investor Pilot Program | Change of Address | Status Provided  | Creating Ipo SI | Email       | 12/4/2018 9:44 AM|
  
  @E2EwithoutAddiRev
  Scenario: Verify the newly created service item and its status
   Then For IPO rejection and auto approval of response scenario Verify New Service Item number and Details
  
  @E2EwithoutAddiRev
  Scenario: Creating a new response and validating its status
   Then For IPO rejection and auto approval of response scenario Create a new response for new Service Item
   Then For IPO rejection and auto approval of response scenario Verify newly created service response status
  
  @E2EwithoutAddiRev
  Scenario: Logging out from IPO CSR and logging in with IPO Super User
   Then For IPO rejection and auto approval of response scenario Logout from current profile
   When For IPO rejection and auto approval of response scenario Search for required Supervisor User "IPO Super User"
   Then For IPO rejection and auto approval of response scenario Logging in as Supervisor user and verifying "IPO Super User"
  
  @E2EwithoutAddiRev
  Scenario: Reject the Service item response with IPO Super User user and with proper comments and without marking Additional Review Checkbox
   Then For IPO rejection and auto approval of response scenario Select required service item with Supervisor user to reject
   Then For IPO rejection and auto approval of response scenario Reject the selected service request without marking additional review checkbox
   Then For IPO rejection and auto approval of response scenario Verify the rejected service request status "Edits Required"
  
  @E2EwithoutAddiRev
  Scenario: Log out from IPO Super User user profile and log in with IPO CSR
   Then For IPO rejection and auto approval of response scenario Logout from supervisor profile
   When For IPO rejection and auto approval of response scenario Search for required Internal User which created the response "IPO CSR"
   Then For IPO rejection and auto approval of response scenario Logging in as Internal user and verifying User which created the response "IPO CSR"
  
  @E2EwithoutAddiRev
  Scenario: Re-submitting the Service Item response and check the response status changed to approved
   Then For IPO rejection and auto approval of response scenario Edit pre-created response and resubmit
   Then For IPO rejection and auto approval of response scenario Validate resubmitted service response status changed to "Sent"
  
  @E2EwithoutAddiRev
  Scenario: Log out and close the browser
   Then For IPO rejection and auto approval of response scenario Logout from response editor's profile
   Then Stop Report Generation for current scenario For IPO rejection and auto approval of response scenario
   Then Close the browser For IPO rejection and auto approval of response scenario
