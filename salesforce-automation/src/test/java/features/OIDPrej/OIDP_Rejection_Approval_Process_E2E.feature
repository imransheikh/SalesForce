Feature: Verifying OIDP Rejection End To End flow for a sevice item which is created through gmail in Salesforce lightning view
  Verifying End to End flow for OIDP rejection approval flow
  @EmailOIDP
  Scenario: Log in and verify the default profile
  	Given OIDP Email Rejection/Approval process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @EmailOIDP
  Scenario: Log in with OIDP SUPERVISOR and Set QC percentage to 100%
    When OIDP Email Rejection/Approval process Search for required Internal User "OIDP Supervisor"
    Then OIDP Email Rejection/Approval process Logging in as Internal user and verifying "OIDP Supervisor"
    Then OIDP Email rejection/approval process set QC Percentage to "100"
  @EmailOIDP
  Scenario: Fetch the email id from email services
  	Then OIDP Email Rejection/Approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row "starsoidpqa"
  @EmailOIDP
  Scenario: Log into email and send an email with URl fetched from salesforce website
  	Then OIDP Email Rejection/Approval process Logging into email "https://acumensolutions.onelogin.com/login2/" with user id "udanturthy"
  @EmailOIDP
  Scenario: Fetch the service item number which is created from email and store it and log out
  	Then OIDP Email Rejection/Approval process Log Into as OIDP Analyst user and capture new service item "OIDP Analyst"
  @EmailOIDP
  Scenario: Creating new service response
  	Then OIDP Email Rejection/Approval process Creating new response and Validating the response status
  @EmailOIDP
  Scenario: Reject the service response with OIDP Supervisor user
   Then OIDP Email Rejection/Approval process reject the service response with Supervisor user
  @EmailOIDP
  Scenario: Edit the rejected response and resubmit it with proper comments
   Then OIDP Email Rejection/Approval process Resubmit the service response with analyst user
  @EmailOIDP
  Scenario: Approve the resubmitted service response
   Then OIDP Email Rejection/Approval process Approve the service response with Supervisor user
  @EmailOIDP
  Scenario: Reply to service item related mail and verify the status
   Then OIDP Email Rejection/Approval process Reply from email and verify the status of service item
   Then Stop Report Generation for current scenario For OIDP rejection and approval of response scenario
   Then Close the browser For OIDP rejection and approval of response scenario