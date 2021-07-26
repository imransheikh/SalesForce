Feature: Existing user record refresh and create a new service item with new response and Approve the service response with super user
  Verify the approval process with super user for a service response
  @ISO12669TakeCtrl
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo ISO12669 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For Ipo ISO12669 scenario set QC Percentage to "100"
    Then For Ipo ISO12669 scenario Verifying the current logged in user profile
    When For Ipo ISO12669 scenario Search for required Internal User "IPO CSR"
    Then For Ipo ISO12669 scenario Logging in as Internal user and verifying "IPO CSR"
  
  @ISO12669TakeCtrl
  Scenario: Search with Customer and Refresh the customer record
  	When For Ipo ISO12669 scenario Search for customer "A200453283"
    Then For Ipo ISO12669 scenario Refresh the customer data and wait for Refresh successful message
  
  @ISO12669TakeCtrl
  Scenario: Open the customer record details
  	Then For Ipo ISO12669 scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @ISO12669TakeCtrl
  Scenario: Create new service item by providing below input data
   Then For Ipo ISO12669 scenario Click on create new service item "WAC1690258857"
   And For Ipo ISO12669 scenario create new service item Provide all new mandatory data
   | Email 						 | Sender Type   | Subject 		| Description      |Form Number | Form Type                                      								  | Issue     		  | Action           | Comments   	 	| Item Origin | DateTime         |
   | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description |I924		|I-924 Application for Regional Center Under the Immigrant Investor Pilot Program | Change of Address | Status Provided  | Creating Ipo SI | Email       | 12/4/2018 9:44 AM|
  
  @ISO12669TakeCtrl
  Scenario: Verify the newly created service item and its status
   Then For Ipo ISO12669 scenario Verify New Service Item number and Details
  
  @ISO12669TakeCtrl
  Scenario: Change the owner in order to verify the Take Ownership button
   Then For ISO12669 scenario Set the Owner as IPO SI Q user for the newly created Service Item
  
  @ISO12669TakeCtrl
  Scenario: Click on the Take Ownership button and verify the owner changed to IPO CSR
   Then For ISO12669 scenario Click on take control button
   
   @ISO12669TakeCtrl
  Scenario: Click on Close Service item button and verify SI status is closed
  Then For ISO12696 scenario Click on Close Service item button and verify SI status is closed
  
  @ISO12669TakeCtrl
  Scenario: Ending the script and close the browser
   Then Stop Report Generation for current scenario For Iso12669 scenario
   Then Close the browser For Iso12669 scenario