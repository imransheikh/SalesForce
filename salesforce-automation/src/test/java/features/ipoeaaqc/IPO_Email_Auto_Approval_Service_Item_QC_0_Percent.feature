Feature: Verifying auto submission process and creating a service item with sending an IPO Email With Ipo user
  Verifying End to End flow for IPO Email auto approval by sending an IPO Email with Ipo user
  @EmailIpo
  Scenario: Log in and verify the default profile
	Given IPO Email auto approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then IPO Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp 
  @EmailIpo  
  Scenario: Fetch the IPO Email id from IPO Email services
  	Then IPO Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "ipoemailtocaseqa"
  @EmailIpo
  Scenario: Log into IPO Email and send an IPO Email with URl fetched from salesforce website
  	Then IPO Email auto approval process Logging into email "https://acumensolutions.onelogin.com/login2/" with user id "udanturthy"
  @EmailIpo
  Scenario: Log in with IPO Super User and Set QC percentage to 0%
    When IPO Email auto approval process Search for required Internal User "IPO Super User"
    Then IPO Email auto approval process Logging in as Internal user and verifying "IPO Super User"
    Then IPO Email auto approval process set QC Percentage to "0"
  @EmailIpo
  Scenario: Fetch the service item number which is created from IPO Email and store it and log out
  	Then IPO Email auto approval process Fetch the Service item number which is created through Email
  @EmailIpo 
  Scenario: Log in with IPO CSR and mark all opened items as Duplicate and Assign new Item and open the service item in edit mode
  	Then IPO Email auto approval process Log Into as IPO Normal user and mark openend items as duplicate and Assign a new item with user "IPO CSR"
  @EmailIpo	
  Scenario: Editing service item by providing below input data and saving the item
   And IPO To edit service item Provide all new mandatory data
  | Receipt No 	  |		Contact		| Email | Sender Type   | Subject 	| Description      | Form No | Form Type                                      	 								| Issue       		| Action          | Comments   | Item Origin | DateTime           | Response Comments     |
  | WAC1690258857 |	EDVARD EDOUARD  | Auto 	| ASC 			| Auto 		| Auto 			   | I924 	 | I-924 Application for Regional Center Under the Immigrant Investor Pilot Program | Change of Address | Status Provided | Creating SI| Email       | Auto Date Time Set | New Response Approval |  
  @EmailIpo
  Scenario: Validate the saved item
   Then IPO Email auto approval process Validating edited service details saved reflecting in next page
  @EmailIpo
  Scenario: Create new response and validate the response status
   Then IPO Email auto approval process Creating new response and Validating the response status
   Then IPO Stop Report Generation for current scenario Email auto approval process
   Then IPO Close the browser Email auto approval process
   