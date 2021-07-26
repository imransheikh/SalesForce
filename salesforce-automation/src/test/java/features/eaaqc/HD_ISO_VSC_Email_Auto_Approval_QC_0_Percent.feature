Feature: Verifying auto submission process and creating a service item with sending an email 
  Verifying End to End flow for email auto approval by sending an email
  @EmailHD
  Scenario: Log in and verify the default profile
 Given Email auto approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then Email auto approval process Verifying the current logged in user profile
  @EmailHD
  Scenario: Fetch the email id from email services
  	Then Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "cishdqa"
  @EmailHD
  Scenario: Log into email and send an email with URl fetched from salesforce website
  	Then Email auto approval process Logging into email "https://acumensolutions.onelogin.com/login2/" with user id "udanturthy"
  @EmailHD
  Scenario: Log in with ONE HD SUPERVISOR and Set QC percentage to 5%
    When Email auto approval process Search for required Internal User "One HD Supervisor"
    Then Email auto approval process Logging in as Internal user and verifying "One HD Supervisor"
    Then Email auto approval process set QC Percentage to "5"
  @EmailHD
  Scenario: Fetch the service item number which is created from email and store it and log out
  	Then Email auto approval process Fetch the Service item number which is created through Email
  @EmailHD
  Scenario: Log in with HD ISO VSC and mark all opened items as Duplicate and Assign new Item and open the service item in edit mode
  	Then Email auto approval process Log Into as HD VSC user and mark openend items as duplicate and Assign a new item with user "HD ISO VSC"
  @EmailHD	
  Scenario: Editing service item by providing below input data and saving the item
   And To edit service item Provide all new mandatory data
  | Receipt No 	  |		Contact		| Org Name    | Email 	| Sender Type   | Subject 		| Description      | Form No | Form Type                                      	 | Category    | Kind                    | Comments   | Item Origin | Queue     			   | DateTime         | Response Comments     |
  | WAC1690258857 |	EDVARD EDOUARD  | Test Org14  | Auto 	| ASC 			| Auto 		| Auto 			   | I192 	 | I-192 Advance Permission to Enter as Nonimmigrant | AWA Case    | AWA Concerns Identified | Creating SI| Email       | HD_Correspondence_Queue | 10/4/2018 9:44 AM| New Response Approval |  
  @EmailHD
  Scenario: Validate the saved item
   Then Email auto approval process Validating edited service details saved reflecting in next page
  @EmailHD
  Scenario: Create new response and validate the response status
   Then Email auto approval process Creating new response and Validating the response status
   Then Email auto approval process Replying to the triggered email "https://acumensolutions.onelogin.com/login2/" with user id "udanturthy"
   Then Email auto approval process to verify child service item created and parent service item present in the child service item
   Then Stop Report Generation for current scenario Email auto approval process
   Then Close the browser Email auto approval process