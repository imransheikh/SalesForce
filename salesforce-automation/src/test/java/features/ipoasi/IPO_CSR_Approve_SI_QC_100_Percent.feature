Feature: Existing user record refresh and create a new service item with new response and Approve the service response with super user
  Verify the approval process with super user for a service response
  @DirectApp
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo approval process of response scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For Ipo approval process of response scenario set QC Percentage to "100"
    Then For Ipo approval process of response scenario Verifying the current logged in user profile
    When For Ipo approval process of response scenario Search for required Internal User "IPO CSR"
    Then For Ipo approval process of response scenario Logging in as Internal user and verifying "IPO CSR"
  
  @DirectApp
  Scenario: Search with Customer and Refresh the customer record
  	When For Ipo approval process of response scenario Search for customer "A200453283"
    Then For Ipo approval process of response scenario Refresh the customer data and wait for Refresh successful message
  
  @DirectApp
  Scenario: Open the customer record details
  	Then For Ipo approval process of response scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @DirectApp	
  Scenario: Create new service item by providing below input data
   Then For Ipo approval process of response scenario Click on create new service item "WAC1690258857"
   And For Ipo approval process of response scenario create new service item Provide all new mandatory data
   | Email 						 | Sender Type   | Subject 		| Description      |Form Number | Form Type                                      								  | Issue     		  | Action           | Comments   	 	| Item Origin | DateTime         |
   | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description |I924		|I-924 Application for Regional Center Under the Immigrant Investor Pilot Program | Change of Address | Status Provided  | Creating Ipo SI | Email       | 12/4/2018 9:44 AM|
  
  @DirectApp
  Scenario: Verify the newly created service item and its status
   Then For Ipo approval process of response scenario Verify New Service Item number and Details
  
  @DirectApp
  Scenario: Creating a new response and validating its status
   Then For Ipo approval process of a response scenario Create a new response with attachment for new Service Item and Verify newly created service response status and attachment
  
  @DirectApp
  Scenario: Logging out from IPO CSR and logging in with IPO Super User
   Then For Ipo approval process of response scenario Logout from current profile
   When For Ipo approval process of response scenario Search for required Supervisor User "IPO Super User"
   Then For Ipo approval process of response scenario Logging in as Supervisor user and verifying "IPO Super User"
  
  @DirectApp
  Scenario: Re-submitting the Service Item response and check the response status changed to Sent
   Then For Ipo approval process of response scenario Select required service item with Super user to Approve
   Then For Ipo approval process of response scenario Approve the selected service request
   Then For Ipo approval process of response scenario Verify the approved service request status "Sent"
  
  @DirectApp
  Scenario: Ending the script and close the browser
   Then Stop Report Generation for current scenario For Ipo approval process of response scenario
   Then Close the browser For Ipo approval process of response scenario