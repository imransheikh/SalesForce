Feature: Verifying auto submission process and creating a service item with sending an SCU Email With SCU user
  Verifying End to End flow for SCU Email auto approval by sending an SCU Email with SCU user
  @EmailIpo
  Scenario: Log in and verify the default profile
  	Given SCU Email auto approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then SCU Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp 
  @EmailIpo  
  Scenario: Fetch the SCU Email id from SCU Email services
  	Then SCU Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "CC"
  @EmailIpo
  Scenario: Log into SCU Email and send an SCU Email with URl fetched from salesforce website
  	Then SCU Email auto approval process Logging into email "https://accounts.google.com/speedbump/samlconfirmaccount?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sarp=1&scc=0&osid=1&TL=AM3QAYZiNvgxQINbArO9gY65HIrpXjbF9XLN6aH7J4RrE9_Cqa2xMZZ1zSf8Q4Uv" with user id "zabid"
  @EmailIpo
  Scenario: Log in with SCU Super User and Set QC percentage to 0%
    When SCU Email auto approval process Search for required Internal User "CCD SCU Analyst 1"
    Then SCU Email auto approval process Logging in as Internal user and verifying "CCD SCU Analyst 1"
    
  @EmailIpo
  Scenario: Fetch the service item number which is created from SCU Email and store it and log out
  	Then SCU Select the dropdown value "CCD SCU Intake Queue"
  @EmailIpo 
  Scenario: Log in with SCU CSR and mark all opened items as Duplicate and Assign new Item and open the service item in edit mode
  	Then SCU Change the Owner and Save
  @EmailIpo	
  Scenario: Editing service item by providing below input data and saving the item
  	Then Open the service item from my service item area
  @EmailIpo
  Scenario: Edit the Required Field
   Then Edit Requiored Field and validate the error messages
   
  Scenario: Validate the response
   Then Create New Response and validate from view response page
   Then Validate the response from Service Item Page
   
  Scenario: Validate status is closed
   Then Validate the service item while making the status as Closed
   
  Scenario: Validate status is Re-Opened
   Then Validate the service item while making the status as Re-Open
  @EmailIpo
  Scenario: Create new response and validate the response status
   Then Validate the service item after making referring party as WH and decesion status is changed to pending
   Then SCU Stop Report Generation for current scenario Email auto approval process
   Then SCU Close the browser Email auto approval process
   