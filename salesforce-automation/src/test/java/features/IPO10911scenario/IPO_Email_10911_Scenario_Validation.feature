Feature: Verifying auto submission process and creating a service item with sending an IPO Email With Ipo user to validate IPO_10911 scenario
  Verifying IPO_10911 scenario
  @EmailIpo_10911
  Scenario: Log in and verify the default profile
  	Given IPO_10911 Email auto approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then IPO_10911 Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp 
  @EmailIpo_10911  
  Scenario: Fetch the IPO Email id from IPO Email services
  	Then IPO_10911 Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "ipoemailtocaseqa"
  @EmailIpo_10911
  Scenario: Log into IPO Email and send an IPO Email with URl fetched from salesforce website
  	Then IPO_10911 Email auto approval process Logging into email "https://acumensolutions-com.clearlogin.com/login" with user id "zabid"
  @EmailIpo_10911
  Scenario: Log in with IPO Super User and Set QC percentage to 0%
    When IPO_10911 Email auto approval process Search for required Internal User "IPO Super User"
    Then IPO_10911 Email auto approval process Logging in as Internal user and verifying "IPO Super User"
    Then IPO_10911 Email auto approval process set QC Percentage to "0"
  @EmailIpo_10911
  Scenario: Fetch the service item number which is created from IPO Email and store it and log out
  	Then IPO_10911 Email auto approval process Fetch the Service item number which is created through Email
  @EmailIpo_10911 
  Scenario: Log in with IPO CSR and mark all opened items as Duplicate and Assign new Item and open the service item in edit mode
  	Then IPO_10911 Email auto approval process Log Into as IPO Normal user and mark openend items as duplicate and Assign a new item with user "IPO CSR"
  @EmailIpo_10911	
  Scenario: Editing service item by providing below input data and saving the item
   And IPO_10911 To edit service item Provide all new mandatory data
  | Receipt No 	  |		Contact		| Email | Sender Type   | Subject 	| Description      | Form No | Form Type                                      	 								| Issue       		| Action          | Comments   | Item Origin | DateTime           | Response Comments     |
  | WAC1690258857 |	EDVARD EDOUARD  | Auto 	| ASC 			| Auto 		| Auto 			   | I924 	 | I-924 Application for Regional Center Under the Immigrant Investor Pilot Program | Change of Address | Status Provided | Creating SI| Email       | Auto Date Time Set | New Response Approval |  
  @EmailIpo_10911
  Scenario: Create new response and validate the response status
   Then IPO_10911 create new service item response with multiple contacts and validating email attachment is appearing in the Load Attachment section
   @EmailIpo_10911
  Scenario: Validate CC BCC email in the outbound email page
   Then IPO_10911 Validate cc BCC email is appearing in the out bound mail section and check the response status is changed to Sent 
   Then IPO_10911 Stop Report Generation for current scenario Email auto approval process
   Then IPO_10911 Close the browser Email auto approval process
   