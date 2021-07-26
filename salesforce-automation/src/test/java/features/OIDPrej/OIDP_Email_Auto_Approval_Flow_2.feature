Feature: Verifying OIDP Rejection End To End flow 2 for a sevice item which is created through gmail in Salesforce lightning view and auto approval
  Verifying End to End flow for OIDP Email Auto Approval with different sets of functionality like HOLD, RELATED SERVICE ITEM, LINK SI, CLONE MEREGE,  DRAFT RESPONSE
  @EmailOIDPflow2
  Scenario: Log in and verify the default profile
  	Given OIDP Email Auto Approval Flow 2 Registered User is logged in with "mohammad.z.abid@uscis.dhs.gov.uatg"
  @EmailOIDPflow2
  Scenario: Log in with OIDP SUPERVISOR and Set QC percentage to 5%
    When OIDP Email Auto Approval Flow 2 Search for required Internal User "OIDP Supervisor"
    Then OIDP Email Auto Approval Flow 2 Logging in as Internal user and verifying "OIDP Supervisor"
    Then OIDP Email Auto Approval Flow 2 process set QC Percentage to "5"
  @EmailOIDPflow2
  Scenario: Fetch the email id from email services
  	Then OIDP Email Auto Approval Flow 2 Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "starsoidpqa"
  @EmailOIDPflow2
  Scenario: Log into email and send an email with URl fetched from salesforce website
  	Then OIDP Email Auto Approval Flow 2 Logging into email "https://acumensolutions.onelogin.com/login2/" with user id "udanturthy"
  @EmailOIDPflow2
  Scenario: Fetch the service item number which is created from email and store it and log out
  	Then OIDP Email Auto Approval Flow 2 Log Into as OIDP Analyst user and capture new service item "OIDP Analyst"
  @EmailOIDPflow2
  Scenario: Opening the ervice item and validating attachment and email related section details are present
  	Then OIDP Email Auto Approval Flow 2 Opening and Validating newly created Service Item
  @EmailOIDPflow2
  Scenario: Open the newly created service item in Edit Mode and edit as per input data and save it
  	And OIDP Email Auto Approval Flow 2 Edit the new service item with expected test data
  	| Sender Type 	  |		Form Type		| Filling Type    | Category 						| Kind   								| Comments 		|
  	| Applicant   	  |	G1041  				| Adoptions  	  | LFA - Lockbox Filing Assistance | Card Issued/Not Received| Testing	|
  @EmailOIDPflow2
  Scenario: Creating a new response and saving the response as draft to verify response save functionality
  	Then OIDP Email Auto Approval Flow 2 Creating a new response and Saving it as draft
  @EmailOIDPflow2
  Scenario: Creating a new hold to verify HOLD functionality
  	Then OIDP Email Auto Approval Flow 2 Create a HOLD TYPE as "Transaction in Process" and comments as "Testing Hold Functionality"
  @EmailOIDPflow2
  Scenario: Verify user can't mark a service item as Completed when a HOLD is ACTIVE
  	Then OIDP Email Auto Approval Flow 2 Validate the error while marking COMPLETED status of Active HOLD associated to Service Item
  @EmailOIDPflow2
  Scenario: Verify user is able to mark Service Item as COMPLETED after removing the HOLD  
  	Then OIDP Email Auto Approval Flow 2 Removing the HOLD and Marking the Service iTem as COMPLETED
  @EmailOIDPflow2
  Scenario: Creating and Verification a new Related service item and verify the child item
  	Then OIDP Email Auto Approval Flow 2 Creating and Verification a new Related service item with ORIGIN as "Email"
  @EmailOIDPflow2
  Scenario: Creating and verifying new service link
  	Then OIDP Email Auto Approval Flow 2 Creating a new Service LINK with contact name as "Zaim Abid" and role as "AILA Liaison"
  @EmailOIDPflow2
  Scenario: Creating a clone of parent service item and merging the cloned SI to parent SI and verifying expected cloned functionality
  	Then OIDP Email Auto Approval Flow 2 Create a CLONE SI and MERGE it to Parent Service ITEM
  @EmailOIDPflow2
  Scenario: After merging the clone, clone should have saved response of parent and user should be able to submit draft saved response 
  	Then OIDP Email Auto Approval Flow 2 SUBMITTING the daft version response and check response status is changed to SENT
  	Then Stop Report Generation for current scenario For OIDP Email Auto Approval Flow 2 scenario
  	Then Close the browser For OIDP Email Auto Approval Flow 2 scenario
  
  